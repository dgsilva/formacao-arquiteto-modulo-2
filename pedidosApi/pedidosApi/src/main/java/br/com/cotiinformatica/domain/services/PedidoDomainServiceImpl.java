package br.com.cotiinformatica.domain.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotiinformatica.domain.dtos.ClienteDto;
import br.com.cotiinformatica.domain.dtos.ItemPedidoDto;
import br.com.cotiinformatica.domain.dtos.PagamentoDto;
import br.com.cotiinformatica.domain.dtos.PedidoDto;
import br.com.cotiinformatica.domain.entities.Cliente;
import br.com.cotiinformatica.domain.entities.ItemPedido;
import br.com.cotiinformatica.domain.entities.Pagamento;
import br.com.cotiinformatica.domain.entities.Pedido;
import br.com.cotiinformatica.domain.interfaces.IPedidoDomainService;
import br.com.cotiinformatica.infrastructure.repositories.IClienteRepository;
import br.com.cotiinformatica.infrastructure.repositories.IItemPedidoRepository;
import br.com.cotiinformatica.infrastructure.repositories.IPagamentoRepository;
import br.com.cotiinformatica.infrastructure.repositories.IPedidoRepository;

@Service
public class PedidoDomainServiceImpl implements IPedidoDomainService {

	@Autowired IClienteRepository clienteRepository;
	@Autowired IPedidoRepository pedidoRepository;
	@Autowired IPagamentoRepository pagamentoRepository;
	@Autowired IItemPedidoRepository itemPedidoRepository;
	
	@Override
	public UUID criar(PedidoDto dto) throws Exception {

		Cliente cliente = salvarCliente(dto.getCliente());	
		Pedido pedido = salvarPedido(dto, cliente);
		salvarPagamento(dto.getPagamento(), pedido);
		salvarItensPedido(dto.getItensPedido(), pedido);
		
		return pedido.getId();
	}
	
	private Pedido salvarPedido(PedidoDto dto, Cliente cliente) {
		
		Pedido pedido = new Pedido();
		pedido.setId(UUID.randomUUID());
		pedido.setValor(BigDecimal.valueOf(dto.getValor()));
		pedido.setDataPedido(new Date());
		pedido.setDescricao(dto.getDescricao());
		pedido.setStatus("Aguardando pagamento");
		pedido.setCliente(cliente);
		
		pedidoRepository.save(pedido);
		return pedido;
	}
	
	private Cliente salvarCliente(ClienteDto dto) {
		
		Cliente cliente = new Cliente();
		cliente.setId(UUID.randomUUID());
		cliente.setNome(dto.getNome());
		cliente.setCpf(dto.getCpf());
		cliente.setEmail(dto.getEmail());
		
		clienteRepository.save(cliente);
		return cliente;
	}
	
	private Pagamento salvarPagamento(PagamentoDto dto, Pedido pedido) {
		
		Pagamento pagamento = new Pagamento();
		pagamento.setId(UUID.randomUUID());
		pagamento.setNumeroCartao(dto.getNumeroCartao());
		pagamento.setTitularCartao(dto.getTitularCartao());
		pagamento.setDataValidadeCartao(dto.getDataValidadeCartao());
		pagamento.setValorCobranca(BigDecimal.valueOf(dto.getValorCobranca()));
		pagamento.setCvvCartao(dto.getCvvCartao());
		pagamento.setQuantidadeParcelas(dto.getQuantidadeParcelas());
		pagamento.setPedido(pedido);
		
		pagamentoRepository.save(pagamento);
		return pagamento;
	}
	
	private List<ItemPedido> salvarItensPedido(List<ItemPedidoDto> dto, Pedido pedido) {
		
		List<ItemPedido> itensPedido = new ArrayList<ItemPedido>();
		for(ItemPedidoDto itemPedidoDto : dto) {
			ItemPedido itemPedido = new ItemPedido();
			itemPedido.setId(UUID.randomUUID());
			itemPedido.setNome(itemPedidoDto.getNome());
			itemPedido.setQuantidade(itemPedidoDto.getQuantidade());
			itemPedido.setValor(BigDecimal.valueOf(itemPedidoDto.getValor()));
			itemPedido.setPedido(pedido);
			
			itemPedidoRepository.save(itemPedido);
		}
		
		return itensPedido;
	}
}
