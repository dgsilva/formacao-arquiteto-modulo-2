package br.com.cotiinformatica.api.dtos;

import java.util.UUID;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.Data;

@Data
@Document(collection = "products")
public class ProductDto {

	@Id
	private UUID id;
	private String name;
	private Double price;
	private Integer quantity;
	private Double total;
	private StockDto stock;
	
}
