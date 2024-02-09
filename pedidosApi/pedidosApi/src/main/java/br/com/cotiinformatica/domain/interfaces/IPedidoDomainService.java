package br.com.cotiinformatica.domain.interfaces;

import java.util.UUID;

import br.com.cotiinformatica.domain.dtos.PedidoDto;

public interface IPedidoDomainService {

	UUID criar(PedidoDto dto) throws Exception;
}
