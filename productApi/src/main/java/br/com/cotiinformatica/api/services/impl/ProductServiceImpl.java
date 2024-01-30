package br.com.cotiinformatica.api.services.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotiinformatica.api.commands.ProductCreateCommand;
import br.com.cotiinformatica.api.commands.ProductDeleteCommand;
import br.com.cotiinformatica.api.commands.ProductUpdateCommand;
import br.com.cotiinformatica.api.dtos.ProductDto;
import br.com.cotiinformatica.api.dtos.StockDto;
import br.com.cotiinformatica.api.entities.Product;
import br.com.cotiinformatica.api.entities.Stock;
import br.com.cotiinformatica.api.repositories.mongodb.ProductDtoRepository;
import br.com.cotiinformatica.api.repositories.postgresql.ProductRepository;
import br.com.cotiinformatica.api.repositories.postgresql.StockRepository;
import br.com.cotiinformatica.api.services.interfaces.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ProductDtoRepository productDtoRepository;
	
	@Autowired
	StockRepository stockRepository;
	
	@Override
	public ProductDto create(ProductCreateCommand command) {
		Stock stock = stockRepository.find(command.getStockId());
		if(stock == null)
			throw new IllegalArgumentException("Estoque não encontrado.");
		
		Product product = new Product();
		product.setId(UUID.randomUUID());
		product.setName(command.getName());
		product.setPrice(new BigDecimal(command.getPrice()));
		product.setQuantity(command.getQuantity());
		product.setStock(stock);
		
		productRepository.save(product);
		
		ProductDto productDto = new ProductDto();
		productDto.setStock(new StockDto());
		
		productDto.setId(product.getId());
		productDto.setName(product.getName());
		productDto.setPrice(product.getPrice().doubleValue());
		productDto.setQuantity(product.getQuantity());
		productDto.setTotal(product.getQuantity() * product.getPrice().doubleValue());
		productDto.getStock().setId(stock.getId());
		productDto.getStock().setName(stock.getName());	
		
		productDtoRepository.save(productDto);
		
		return productDto;
	}


	@Override
	public ProductDto update(ProductUpdateCommand command) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductDto delete(ProductDeleteCommand command) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductDto> findByStock(UUID stockId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductDto findById(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
