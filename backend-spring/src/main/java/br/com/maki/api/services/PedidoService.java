package br.com.maki.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.maki.api.domain.Pedido;
import br.com.maki.api.repositories.PedidoRepository;
import br.com.maki.api.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido find(Integer id) {
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		return pedido.orElseThrow(() -> new ObjectNotFoundException(
				"Objecto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
	
	public List<Pedido> findAll() {
		return pedidoRepository.findAll();
	}
	
	public Pedido insert(Pedido pedido) {
		pedido.setId(null);
		return pedidoRepository.save(pedido);
	}
	
	public void delete(Integer id) {
		find(id);
		pedidoRepository.deleteById(id);
	}
	
}
