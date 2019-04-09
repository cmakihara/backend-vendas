package br.com.maki.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.maki.api.domain.Produto;
import br.com.maki.api.repositories.ProdutoRepository;
import br.com.maki.api.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	/**
	 * 
	 * @param id
	 * @return 
	 * @throws ObjectNotFoundException 	 
	 */
	public Produto find(Integer id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		return produto.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
	}
	
	/**
	 * 
	 * @return Lista de todos os produtos.
	 */
	public List<Produto> findAll() {
		return produtoRepository.findAll();
	}
	
	/**
	 * 
	 * @param produto
	 * @return
	 */
	public Produto insert(Produto produto) {
		produto.setId(null);
		return produtoRepository.save(produto);
	}
	
	/**
	 * 
	 * @param produto
	 * @return
	 * @throws ObjectNotFoundException
	 */
	public Produto update(Produto produto) {
		Produto newProduto = find(produto.getId());
		updateData(newProduto, produto);
		return produtoRepository.save(newProduto);
	}
	
	public void delete(Integer id) {
		find(id);
		produtoRepository.deleteById(id);
		
	}
	
	/**
	 * 
	 * @param newProduto
	 * @param produto
	 */
	private void updateData(Produto newProduto, Produto produto) {
		newProduto.setNome(produto.getNome());
		
	}
	
}
