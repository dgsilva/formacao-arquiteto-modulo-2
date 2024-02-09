package br.com.cotiinformatica.domain.dtos;

import lombok.Data;

@Data
public class ItemPedidoDto {

	private String nome;
	private Double valor;
	private Integer quantidade;
}
