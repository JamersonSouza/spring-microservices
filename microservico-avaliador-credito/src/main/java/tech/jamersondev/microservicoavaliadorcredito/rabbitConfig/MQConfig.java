package tech.jamersondev.microservicoavaliadorcredito.rabbitConfig;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    @Value("${mq.queues.emissao-cartao}")
    private String emissaoCartoesFila;

    @Bean
    public Queue solicitarCartao(){
        return new Queue(emissaoCartoesFila, true);
    }

}
