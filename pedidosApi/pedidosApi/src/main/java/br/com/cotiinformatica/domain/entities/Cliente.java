package br.com.cotiinformatica.domain.entities;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table
public class Cliente {

	@Id
	@Column
	private UUID id;

	@Column(length = 100, nullable = false)
	private String nome;

	@Column(length = 15, nullable = false)
	private String cpf;

	@Column(length = 50, nullable = false)
	private String email;

	@OneToMany(mappedBy = "cliente")
	private List<Pedido> pedido;
}
