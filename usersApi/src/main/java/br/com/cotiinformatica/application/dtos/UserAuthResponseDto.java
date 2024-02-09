package br.com.cotiinformatica.application.dtos;

import java.util.Date;
import java.util.UUID;

import lombok.Data;

@Data
public class UserAuthResponseDto {

	private UUID id;
	private String name;
	private String email;
	private String accessToken;
	private Date expiration;

}
