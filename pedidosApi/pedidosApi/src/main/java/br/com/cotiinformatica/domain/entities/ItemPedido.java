package br.com.cotiinformatica.domain.entities;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table
public class ItemPedido {

	@Id
	@Column
	private UUID id;

	@Column(length = 150, nullable = false)
	private String nome;

	@Column(precision = 10, scale = 2, nullable = false)
	private BigDecimal valor;

	@Column(nullable = false)
	private Integer quantidade;

	@ManyToOne
	@JoinColumn(name = "pedido_id", nullable = false)
	private Pedido pedido;
}
