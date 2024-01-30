package br.com.cotiinformatica.api.services.interfaces;

import java.util.List;
import java.util.UUID;

import br.com.cotiinformatica.api.commands.ProductCreateCommand;
import br.com.cotiinformatica.api.commands.ProductDeleteCommand;
import br.com.cotiinformatica.api.commands.ProductUpdateCommand;
import br.com.cotiinformatica.api.dtos.ProductDto;

public interface ProductService {

	ProductDto create(ProductCreateCommand command);
	ProductDto update(ProductUpdateCommand command);
	ProductDto delete(ProductDeleteCommand command);
	
	List<ProductDto> findByStock(UUID stockId);
	ProductDto findById(UUID id);
}
