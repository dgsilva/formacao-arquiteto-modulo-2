package br.com.cotiinformatica.domain.dtos;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ApiResultDto {

	private HttpStatus status;
	private String message;
}
