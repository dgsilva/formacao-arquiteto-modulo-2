package br.com.cotiinformatica.domain.dtos;

import java.util.List;

import lombok.Data;

@Data
public class PedidoDto {

	private Double valor;
	private String descricao;
	private ClienteDto cliente;
	private PagamentoDto pagamento;
	private List<ItemPedidoDto> itensPedido;
}
