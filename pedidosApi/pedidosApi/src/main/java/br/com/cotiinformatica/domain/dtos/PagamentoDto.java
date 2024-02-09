package br.com.cotiinformatica.domain.dtos;

import lombok.Data;

@Data
public class PagamentoDto {

	private String numeroCartao;
	private String titularCartao;
	private String dataValidadeCartao;
	private String cvvCartao;
	private Double valorCobranca;
	private Integer quantidadeParcelas;
}
