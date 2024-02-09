package br.com.cotiinformatica.application.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.domain.dtos.ApiResultDto;
import br.com.cotiinformatica.domain.dtos.PedidoDto;
import br.com.cotiinformatica.domain.interfaces.IPedidoDomainService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidosController {

	@Autowired IPedidoDomainService pedidoDomainService;
	
	@PostMapping("criar")
	public ResponseEntity<ApiResultDto> post(@RequestBody PedidoDto dto) {
		
		ApiResultDto result = new ApiResultDto();
		
		try {
			UUID id = pedidoDomainService.criar(dto);
			
			result.setStatus(HttpStatus.CREATED);
			result.setMessage("Pedido criado com sucesso: " + id);
		}
		catch(Exception e) {
			
			result.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			result.setMessage("Erro: " + e.getMessage());
		}
		
		return ResponseEntity
				.status(result.getStatus().value())
				.body(result);
	}
}
