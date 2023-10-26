package tech.jamersondev.microservicecartoes.messageQueues;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import tech.jamersondev.microservicecartoes.domain.Cartao;
import tech.jamersondev.microservicecartoes.domain.ClientCartao;
import tech.jamersondev.microservicecartoes.domain.SolicitaoEmissaoCartao;
import tech.jamersondev.microservicecartoes.repositorys.CartaoRepository;
import tech.jamersondev.microservicecartoes.repositorys.ClientCartaoRepository;

@Component
@Slf4j
public class EmissaoCartaoSubscribe {

    private final CartaoRepository cartaoRepository;
    private final ClientCartaoRepository clientCartaoRepository;

    public EmissaoCartaoSubscribe(CartaoRepository cartaoRepository, ClientCartaoRepository clientCartaoRepository) {
        this.cartaoRepository = cartaoRepository;
        this.clientCartaoRepository = clientCartaoRepository;
    }

    @RabbitListener(queues = "${mq.queues.emissao-cartao}")
    public void recebeSolicitaoEmissaoCartoes(@Payload String payload){
        try {
            ObjectMapper mapper = new ObjectMapper();
            SolicitaoEmissaoCartao solicitaoEmissaoCartao = mapper.readValue(payload, SolicitaoEmissaoCartao.class);
            Cartao cartao = cartaoRepository.findById(solicitaoEmissaoCartao.getCartaoIdentifier()).orElseThrow();
            ClientCartao clientCartao = new ClientCartao();
            clientCartao.CadastrarNovoCartao(solicitaoEmissaoCartao.getCartaoIdentifier(),
                    solicitaoEmissaoCartao.getCpf(), cartao, solicitaoEmissaoCartao.getLimiteLiberado());
            clientCartaoRepository.save(clientCartao);
        }catch (Exception e){
            log.error("Error ao receber a solicitação de emissão de cartão: {}", e.getMessage());
        }
    }

}
