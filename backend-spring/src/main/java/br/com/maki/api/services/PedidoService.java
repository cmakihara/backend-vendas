package br.com.maki.api.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.maki.api.domain.ItemPedido;
import br.com.maki.api.domain.Pedido;
import br.com.maki.api.repositories.ItemPedidoRepository;
import br.com.maki.api.repositories.PedidoRepository;
import br.com.maki.api.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public Pedido find(Integer id) {
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		return pedido.orElseThrow(() -> new ObjectNotFoundException(
				"Objecto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
	
	public List<Pedido> findAll() {
		return pedidoRepository.findAll();
	}
	
	@Transactional
	public Pedido insert(Pedido pedido) {
		pedido.setId(null);
		pedido = pedidoRepository.save(pedido);
		for (ItemPedido ip : pedido.getItens()) {
			ip.setProduto(produtoService.find(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(pedido);
		}
	//	itemPedidoRepository.saveAll(pedido.getItens());
		return pedido;
	}
	
	public void delete(Integer id) {
		find(id);
		pedidoRepository.deleteById(id);
	}
	
}
