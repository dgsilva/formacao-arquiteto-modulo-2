package br.com.cotiinformatica.api.commands;

import java.util.UUID;

import lombok.Data;

@Data
public class ProductUpdateCommand {

	private UUID id;
	private String name;
	private Double price;
	private Integer quantity;
	private UUID stockId;
}
