package br.com.cotiinformatica.application.dtos;

import lombok.Data;

@Data
public class UserMessageDto {

	private String emailTo;
	private String subject;
	private String body;
}
