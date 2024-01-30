package br.com.cotiinformatica.api.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.api.commands.StockCreateCommand;
import br.com.cotiinformatica.api.dtos.StockDto;
import br.com.cotiinformatica.api.services.interfaces.StockService;

@RestController
@RequestMapping(value = "/api/stocks")
public class StocksController {
	
	@Autowired StockService stockService;

	@PostMapping
	public StockDto post(@RequestBody StockCreateCommand command) {
		return stockService.create(command);
	}

	@GetMapping
	public List<StockDto> getAll() {
		return stockService.findAll();
	}

	@GetMapping("{id}")
	public StockDto GetById(@PathVariable("id") UUID id) {
		return stockService.findById(id);
	}

	
}
