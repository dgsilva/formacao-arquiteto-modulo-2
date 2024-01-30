package br.com.cotiinformatica.api.entities;

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
@Table(name = "tbl_product")
public class Product {

	@Id
	@Column(name = "id")
	private UUID id;
	
	@Column(name = "name", length = 100, nullable = false)
	private String name;
	
	@Column(name = "price", precision = 10, scale = 2, nullable = false)
	private BigDecimal price;
	
	@Column(name = "quantity", nullable = false)
	private Integer quantity;
	
	@ManyToOne
	@JoinColumn(name = "stock_id", nullable = false)
	private Stock stock;	
}
