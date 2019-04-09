package br.com.maki.api;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.maki.api.domain.ItemPedido;
import br.com.maki.api.domain.Pedido;
import br.com.maki.api.domain.Produto;
import br.com.maki.api.repositories.ItemPedidoRepository;
import br.com.maki.api.repositories.PedidoRepository;
import br.com.maki.api.repositories.ProdutoRepository;

@SpringBootApplication
public class ApiApplication implements CommandLineRunner {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;  
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public static void main(String[] args) {
		
		SpringApplication.run(ApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Produto prod1 = new Produto(null, "Computador", 2000.00);
		Produto prod2 = new Produto(null, "Impressora", 800.00);
		Produto prod3 = new Produto(null, "Mouse", 80.00);
		
		produtoRepository.saveAll(Arrays.asList(prod1,prod2,prod3));
		
		Pedido pedido1 = new Pedido(null);
		Pedido pedido2 = new Pedido(null);
		//Pedido pedido3 = new Pedido(null);
		
		pedidoRepository.saveAll(Arrays.asList(pedido1, pedido2));
		
		ItemPedido ip1 = new ItemPedido(pedido1, prod1,  1, 2000.00);
		ItemPedido ip2 = new ItemPedido(pedido1, prod3,  2, 80.00);
		ItemPedido ip3 = new ItemPedido(pedido2, prod2,  1, 800.00);
		
		pedido1.getItens().addAll(Arrays.asList(ip1, ip2));
		pedido2.getItens().addAll(Arrays.asList(ip3));
		
		prod1.getItens().addAll(Arrays.asList(ip1));
		prod2.getItens().addAll(Arrays.asList(ip3));
		prod3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
	}

}
