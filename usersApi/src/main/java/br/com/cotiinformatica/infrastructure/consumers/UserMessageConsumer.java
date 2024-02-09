package br.com.cotiinformatica.infrastructure.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.cotiinformatica.application.dtos.UserMessageDto;
import br.com.cotiinformatica.infrastructure.components.MailSenderComponent;

@Service
public class UserMessageConsumer {

	@Autowired
	MailSenderComponent mailSenderComponent;
	
	@Autowired
	ObjectMapper objectMapper;
	
	/*
	 * Método para ler os itens da fila
	 */
	@RabbitListener(queues = { "${queue.name}" })
	public void receive(@Payload String message) {

		try {
			
			//deserializar a mensagem gravada na fila
			UserMessageDto dto = objectMapper.readValue(message, UserMessageDto.class);
			
			//enviar por email
			mailSenderComponent.sendMessage(dto.getEmailTo(), dto.getSubject(), dto.getBody());			
		}
		catch(Exception e) {
			e.printStackTrace();
		}		
	}	
}
