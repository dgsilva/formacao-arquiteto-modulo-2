package br.com.cotiinformatica.api.repositories.mongodb;

import java.util.UUID;


import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.cotiinformatica.api.dtos.StockDto;
public interface StockDtoRepository extends MongoRepository<StockDto, UUID> {

}
