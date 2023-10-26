package tech.jamersondev.microservicoavaliadorcredito.messageQueues;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import tech.jamersondev.microservicoavaliadorcredito.domain.SolicitaoEmissaoCartao;

@Component
public class EnviarEmissaoCartaoPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queueEmissaoCartoes;

    public EnviarEmissaoCartaoPublisher(RabbitTemplate rabbitTemplate, Queue queueEmissaoCartoes) {
        this.rabbitTemplate = rabbitTemplate;
        this.queueEmissaoCartoes = queueEmissaoCartoes;
    }

    public void solicitarCartao(SolicitaoEmissaoCartao dadosSolicitaoEmissaoCartao) throws JsonProcessingException {
        String json = this.converteInJson(dadosSolicitaoEmissaoCartao);
        rabbitTemplate.convertAndSend(queueEmissaoCartoes.getName(), json);
    }

    private String converteInJson(SolicitaoEmissaoCartao dadosSolicitaoEmissaoCartao) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(dadosSolicitaoEmissaoCartao);
    }

}
