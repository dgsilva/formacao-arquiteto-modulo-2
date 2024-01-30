package br.com.cotiinformatica.api.dtos;

import java.util.List;
import java.util.UUID;

import org.springframework.data.mongodb.core.mapping.Document;

import br.com.cotiinformatica.api.entities.Product;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Document(collection = "stocks")
public class StockDto {

	@Id
	private UUID id;
	private String name;
	private List<Product> products;
}

