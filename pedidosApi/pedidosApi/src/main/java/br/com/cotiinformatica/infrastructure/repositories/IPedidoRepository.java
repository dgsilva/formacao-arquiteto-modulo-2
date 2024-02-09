package br.com.cotiinformatica.infrastructure.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cotiinformatica.domain.entities.Pedido;

public interface IPedidoRepository extends JpaRepository<Pedido, UUID> {

}
