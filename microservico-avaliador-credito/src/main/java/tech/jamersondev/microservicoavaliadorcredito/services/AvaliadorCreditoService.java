package tech.jamersondev.microservicoavaliadorcredito.services;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tech.jamersondev.microservicoavaliadorcredito.HandlerExceptions.ClienteDadosNotFoundException;
import tech.jamersondev.microservicoavaliadorcredito.HandlerExceptions.ErrorMicroServiceExceptions;
import tech.jamersondev.microservicoavaliadorcredito.HandlerExceptions.ErrorSolicitacaoCartaoException;
import tech.jamersondev.microservicoavaliadorcredito.domain.DTO.*;
import tech.jamersondev.microservicoavaliadorcredito.domain.SituacaoCliente;
import tech.jamersondev.microservicoavaliadorcredito.domain.SolicitaoEmissaoCartao;
import tech.jamersondev.microservicoavaliadorcredito.infra.clients.CartoesResourceClient;
import tech.jamersondev.microservicoavaliadorcredito.infra.clients.ClienteResourceClient;
import tech.jamersondev.microservicoavaliadorcredito.messageQueues.EnviarEmissaoCartaoPublisher;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AvaliadorCreditoService {

    private final ClienteResourceClient clienteResourceClient;

    private final CartoesResourceClient cartoesResourceClient;

    private final EnviarEmissaoCartaoPublisher enviarEmissaoCartaoPublisher;


    public AvaliadorCreditoService(ClienteResourceClient clienteResourceClient, CartoesResourceClient cartoesResourceClient, EnviarEmissaoCartaoPublisher enviarEmissaoCartaoPublisher) {
        this.clienteResourceClient = clienteResourceClient;
        this.cartoesResourceClient = cartoesResourceClient;
        this.enviarEmissaoCartaoPublisher = enviarEmissaoCartaoPublisher;
    }

    public SituacaoCliente obterSituacao(String cpf) throws ClienteDadosNotFoundException, ErrorMicroServiceExceptions {
        // TODO: 08/10/2023  obter os dados do cliente do outro microserviço e obter os cartões do microserviço de cartão
        try {
        ResponseEntity<DadosClienteDTO> dadosClientResponse = clienteResourceClient.getClient(cpf);
        ResponseEntity<List<CartoesClienteDTO>> dadosCartaoResponse = cartoesResourceClient.getCartoesByCpf(cpf);
        return SituacaoCliente.builder()
                .dadosCliente(dadosClientResponse.getBody())
                .cartoesClientes(dadosCartaoResponse.getBody())
                .build();
        }catch (FeignException.FeignClientException e){
           int status = e.status();
            if(HttpStatus.NOT_FOUND.value() == status){
                throw new ClienteDadosNotFoundException();
            }
            throw new ErrorMicroServiceExceptions(e.getMessage(), status);
        }
    }

    public ResponseAvaliacaoCliente responseAvaliacaoCliente(String cpf, Long renda)
            throws ClienteDadosNotFoundException, ErrorMicroServiceExceptions{


        try {
            ResponseEntity<DadosClienteDTO> dadosClientResponse = clienteResourceClient.getClient(cpf);
            ResponseEntity<List<DadosCartaoClienteDTO>> cartoesByRenda = cartoesResourceClient.getCartoesByRenda(renda);
            List<DadosCartaoClienteDTO> cartoes = cartoesByRenda.getBody();
            var listaCartoesAprovados = cartoes.stream().map(c -> {

                DadosClienteDTO dadosClient = dadosClientResponse.getBody();
                BigDecimal limiteBasico = c.getLimiteBasico();
                BigDecimal rendaBigDecimal = BigDecimal.valueOf(renda);
                BigDecimal idadeClientBD = BigDecimal.valueOf(dadosClient.getIdadeClient());

                var fator = idadeClientBD.divide(BigDecimal.valueOf(10));
                BigDecimal limiteAprovadoClient = fator.multiply(limiteBasico);

                CartaoAprovadoDTO cartaoAprovado = new CartaoAprovadoDTO();
                cartaoAprovado.setCartao(c.getNome());
                cartaoAprovado.setBandeira(c.getBandeiraCartao());
                cartaoAprovado.setLimiteAprovado(limiteAprovadoClient);
                return cartaoAprovado;
            }).collect(Collectors.toList());
            return new ResponseAvaliacaoCliente(listaCartoesAprovados);
        }catch (FeignException.FeignClientException e){
            int status = e.status();
            if(HttpStatus.NOT_FOUND.value() == status){
                throw new ClienteDadosNotFoundException();
            }
            throw new ErrorMicroServiceExceptions(e.getMessage(), status);
        }

    }

    public ProtocoloSolicitacaoCartaoDTO solicitarEmissaoCartao(SolicitaoEmissaoCartao dadosSolicitacaoEmissao) throws ErrorSolicitacaoCartaoException {
        try {
           enviarEmissaoCartaoPublisher.solicitarCartao(dadosSolicitacaoEmissao);
           String protocolo = UUID.randomUUID().toString();
           return new ProtocoloSolicitacaoCartaoDTO(protocolo);
        }catch (Exception e){
            throw new ErrorSolicitacaoCartaoException(e.getMessage());
        }
    }

}
