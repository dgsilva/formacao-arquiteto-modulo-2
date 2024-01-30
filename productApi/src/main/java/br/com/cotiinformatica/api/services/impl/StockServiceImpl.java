package br.com.cotiinformatica.api.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotiinformatica.api.commands.StockCreateCommand;
import br.com.cotiinformatica.api.dtos.StockDto;
import br.com.cotiinformatica.api.entities.Stock;
import br.com.cotiinformatica.api.repositories.mongodb.StockDtoRepository;
import br.com.cotiinformatica.api.repositories.postgresql.StockRepository;
import br.com.cotiinformatica.api.services.interfaces.StockService;

@Service
public class StockServiceImpl implements StockService {

	@Autowired
	StockRepository stockRepository; // PostGreSQL
	@Autowired
	StockDtoRepository stockDtoRepository; // MongoDB

	@Override
	public StockDto create(StockCreateCommand command) {

		// Trata nulos e vazios usando StringUtils
        String stockName = StringUtils.defaultString(command.getName()).toUpperCase();

        // Verifica se o estoque já existe no PostgreSQL
        if (stockRepository.find(stockName) != null) {
            throw new IllegalArgumentException("Já existe um estoque cadastrado com o nome informado.");
        }
        
        
		Stock stock = new Stock();
		stock.setId(UUID.randomUUID());
		stock.setName(command.getName().toUpperCase());

		stockRepository.save(stock); // gravando no PostGreSQL

		StockDto stockDto = new StockDto();
		stockDto.setId(stock.getId());
		stockDto.setName(stock.getName());

		stockDtoRepository.save(stockDto); // gravando no MongoDB

		return stockDto;
	}

	@Override
	public List<StockDto> findAll() {
		return stockDtoRepository.findAll();
	}

	@Override
	public StockDto findById(UUID id) {
		Optional<StockDto> stock = stockDtoRepository.findById(id);
		if (stock.isPresent())
			return stock.get();
		else
			return null;
	}
}
