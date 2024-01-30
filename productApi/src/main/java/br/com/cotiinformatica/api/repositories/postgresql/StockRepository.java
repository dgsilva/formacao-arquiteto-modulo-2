package br.com.cotiinformatica.api.repositories.postgresql;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.cotiinformatica.api.entities.Stock;

public interface StockRepository extends JpaRepository<Stock, UUID> {

	@Query("SELECT s FROM Stock s WHERE s.id = :id")
	Stock find(@Param("id") UUID id);

	@Query("SELECT s FROM Stock s WHERE s.name = :name")
	Stock find(@Param("name") String name);
}
