package tech.jamersondev.microservicoavaliadorcredito;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableRabbit
public class MicroservicoAvaliadorCreditoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicoAvaliadorCreditoApplication.class, args);
	}

}
