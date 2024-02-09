package br.com.cotiinformatica.application.dtos;

import java.time.Instant;
import java.util.UUID;

import lombok.Data;

@Data
public class UserCreateResponseDto {

	private UUID id;
	private String name;
	private String email;
	private Instant createdAt;
}
