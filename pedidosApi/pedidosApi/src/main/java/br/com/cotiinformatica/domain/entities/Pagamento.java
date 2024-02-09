package br.com.cotiinformatica.domain.entities;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table
public class Pagamento {

	@Id
	@Column
	private UUID id;

	@Column(length = 25, nullable = false)
	private String numeroCartao;

	@Column(length = 25, nullable = false)
	private String titularCartao;

	@Column(nullable = false)
	private String dataValidadeCartao;

	@Column(length = 3, nullable = false)
	private String cvvCartao;

	@Column(precision = 10, scale = 2, nullable = false)
	private BigDecimal valorCobranca;

	@Column(nullable = false)
	private Integer quantidadeParcelas;

	@OneToOne
	@JoinColumn(name = "pedido_id", nullable = false, unique = true)
	private Pedido pedido;
}
