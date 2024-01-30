package br.com.cotiinformatica.api.services.interfaces;

import java.util.List;
import java.util.UUID;

import br.com.cotiinformatica.api.commands.StockCreateCommand;
import br.com.cotiinformatica.api.dtos.StockDto;

public interface StockService {

	StockDto create(StockCreateCommand command);
	
	List<StockDto> findAll();
	StockDto findById(UUID id);
}

