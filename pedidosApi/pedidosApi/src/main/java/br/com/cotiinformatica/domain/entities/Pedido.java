package br.com.cotiinformatica.domain.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table
public class Pedido {

	@Id
	@Column
	private UUID id;

	@Column(precision = 10, scale = 2, nullable = false)
	private BigDecimal valor;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date dataPedido;

	@Column(length = 250, nullable = false)
	private String descricao;

	@Column(length = 25, nullable = false)
	private String status;

	@Column(length = 40)
	private String reciboPagamento;

	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false)
	private Cliente cliente;

	@OneToOne(mappedBy = "pedido")
	private Pagamento pagamento;

	@OneToMany(mappedBy = "pedido")
	private List<ItemPedido> itensPedido;
}
