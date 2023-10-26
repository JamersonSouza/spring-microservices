package tech.jamersondev.microservicoavaliadorcredito.resources;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jamersondev.microservicoavaliadorcredito.HandlerExceptions.ClienteDadosNotFoundException;
import tech.jamersondev.microservicoavaliadorcredito.HandlerExceptions.ErrorMicroServiceExceptions;
import tech.jamersondev.microservicoavaliadorcredito.HandlerExceptions.ErrorSolicitacaoCartaoException;
import tech.jamersondev.microservicoavaliadorcredito.domain.DTO.DadosAvaliacaoDTO;
import tech.jamersondev.microservicoavaliadorcredito.domain.DTO.ProtocoloSolicitacaoCartaoDTO;
import tech.jamersondev.microservicoavaliadorcredito.domain.DTO.ResponseAvaliacaoCliente;
import tech.jamersondev.microservicoavaliadorcredito.domain.SituacaoCliente;
import tech.jamersondev.microservicoavaliadorcredito.domain.SolicitaoEmissaoCartao;
import tech.jamersondev.microservicoavaliadorcredito.services.AvaliadorCreditoService;

@RestController
@RequestMapping("/avaliador")
@Slf4j
public class AvaliadorResource {

    private final AvaliadorCreditoService avaliadorCreditoService;

    public AvaliadorResource(AvaliadorCreditoService avaliadorCreditoService) {
        this.avaliadorCreditoService = avaliadorCreditoService;
    }

    @GetMapping("/test")
    public String testCartao(){
        log.info("Testando avaliador");
        return "Teste avaliador get";
    }

    @GetMapping(params = "cpf")
    public ResponseEntity consultaSituacaoClient(@RequestParam("cpf") String cpf){
        try {
            SituacaoCliente situacaoCliente = this.avaliadorCreditoService.obterSituacao(cpf);
            return ResponseEntity.ok(situacaoCliente);
        } catch (ClienteDadosNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ErrorMicroServiceExceptions e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }

    }

    @PostMapping
    public ResponseEntity checkAvaliacao(@RequestBody DadosAvaliacaoDTO dadosAvaliacaoDTO){

        try {
            ResponseAvaliacaoCliente responseAvaliacaoCliente = this.avaliadorCreditoService.responseAvaliacaoCliente(dadosAvaliacaoDTO.getCpf(), dadosAvaliacaoDTO.getRenda());
            return ResponseEntity.ok(responseAvaliacaoCliente);
        } catch (ClienteDadosNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ErrorMicroServiceExceptions e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }

    }

    @PostMapping("/solicitar-cartao")
    public ResponseEntity solicitarCartao(@RequestBody SolicitaoEmissaoCartao dadosSolicitaoEmissaoCartao){
        try {
            ProtocoloSolicitacaoCartaoDTO protocoloSolicitacaoCartaoDTO = avaliadorCreditoService.solicitarEmissaoCartao(dadosSolicitaoEmissaoCartao);
            return ResponseEntity.ok(protocoloSolicitacaoCartaoDTO);
        }catch (ErrorSolicitacaoCartaoException e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

    }


}
