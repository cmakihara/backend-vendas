package br.com.maki.api.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pedido")
public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	//TODO Check
	@OneToMany(mappedBy="id.pedido",cascade=CascadeType.ALL, orphanRemoval=true)	
	private Set<ItemPedido> itens = new HashSet<>();
	
	public double getValorTotal() {
		double soma= 0;
		for (ItemPedido itemPedido : itens) {
			soma = soma + itemPedido.getSubTotal();
		}
		return soma;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Set<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
	}

	public Pedido() {
	}
	
	public Pedido(Integer id) {
		super();
		this.id = id;
	}
	
	
	
}