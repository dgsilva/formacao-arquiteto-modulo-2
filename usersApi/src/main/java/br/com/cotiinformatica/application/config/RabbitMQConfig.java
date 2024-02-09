package br.com.cotiinformatica.application.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	/*
	 * Lendo uma valor de configuração 
	 * contido no arquivo /application.properties
	 */
	@Value("${queue.name}")
	String queueName;
	
	@Bean
	public Queue queue() {
		return new Queue(queueName);
	}
}
