package br.com.maki.api.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.maki.api.domain.Produto;
import br.com.maki.api.services.ProdutoService;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> find(@PathVariable Integer id) {
		Produto produto = produtoService.find(id);
		return ResponseEntity.ok().body(produto);
	}
	
	@GetMapping
	public ResponseEntity<List<Produto>> findAll(){
		List<Produto> listaProdutos = produtoService.findAll();
		return ResponseEntity.ok().body(listaProdutos);
	}
	
	@PostMapping
	public ResponseEntity<Produto> insert(@Valid @RequestBody Produto produto) {
		produtoService.insert(produto);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Produto> update(@Valid @RequestBody Produto produto, @PathVariable Integer id) {
		produto.setId(id);
		produtoService.update(produto);
		return ResponseEntity.noContent().build();
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		
		produtoService.delete(id);
		return ResponseEntity.noContent().build();
	}
	

}
