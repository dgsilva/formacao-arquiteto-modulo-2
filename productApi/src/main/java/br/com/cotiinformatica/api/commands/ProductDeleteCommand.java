package br.com.cotiinformatica.api.commands;

import java.util.UUID;

import lombok.Data;

@Data
public class ProductDeleteCommand {

	private UUID id;
}
