package br.com.cotiinformatica.domain.dtos;

import lombok.Data;

@Data
public class ClienteDto {

	private String nome;
	private String cpf;
	private String email;
}
