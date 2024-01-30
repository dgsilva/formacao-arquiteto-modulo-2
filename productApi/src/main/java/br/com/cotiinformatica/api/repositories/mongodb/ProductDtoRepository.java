package br.com.cotiinformatica.api.repositories.mongodb;

import java.util.UUID;


import org.springframework.data.mongodb.repository.MongoRepository;
import br.com.cotiinformatica.api.dtos.ProductDto;

public interface ProductDtoRepository extends MongoRepository<ProductDto, UUID> {

}
