package br.com.cotiinformatica.api.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.api.commands.ProductCreateCommand;
import br.com.cotiinformatica.api.dtos.ProductDto;
import br.com.cotiinformatica.api.services.interfaces.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductsController {

	@Autowired
	ProductService productService;
	
	@PostMapping
	public ProductDto post(@RequestBody ProductCreateCommand command) {
		return productService.create(command);
	}
	
	@PutMapping
	public void put() {
		
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") UUID id) {
		
	}
	
	@GetMapping("{stockId}")
	public void getByStock(@PathVariable("id") UUID stockId) {
		
	}
}
