package com.generation.farmacia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.farmacia.model.Produto;
import com.generation.farmacia.repository.CarrinhoRepository;
import com.generation.farmacia.repository.ProdutoRepository;
import com.generation.farmacia.service.CarrinhoService;

@RestController
@RequestMapping("/carrinho")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CarrinhoController {

	@Autowired
	private CarrinhoService carrinhoService;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CarrinhoRepository carrinhoRepository;

	@GetMapping("/{carrinhoId}")
	public ResponseEntity<List<Produto>> listarProdutos(@PathVariable Long carrinhoId) {
		List<Produto> produtos = carrinhoService.listarProdutos(carrinhoId);
		return ResponseEntity.ok(produtos);
	}

	@PutMapping("/{carrinhoId}/{produtoId}")
	public ResponseEntity<Produto> adicionarProduto(@PathVariable Long carrinhoId, @PathVariable Long produtoId) {
		if (produtoRepository.findById(produtoId).isPresent()) {
			if (carrinhoRepository.findById(carrinhoId).isPresent()) {
				carrinhoService.adicionarProduto(carrinhoId, produtoId);
				return ResponseEntity.ok(produtoRepository.findById(produtoId).get());
			}
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@DeleteMapping("/{carrinhoId}/{produtoId}")
	public ResponseEntity<Void> removerProduto(@PathVariable Long carrinhoId, @PathVariable Long produtoId) {
	    boolean removido = carrinhoService.removerProduto(carrinhoId, produtoId);

	    if (removido) {
	        return ResponseEntity.noContent().build();  // Retorna 204 (No Content)
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // Retorna 404 se o produto não estiver no carrinho
	    }
	}
	
	@DeleteMapping("/{carrinhoId}")
	public ResponseEntity<Void> limparCarrinho(@PathVariable Long carrinhoId) {
	    boolean limpou = carrinhoService.limparCarrinho(carrinhoId);

	    if (limpou) {
	        return ResponseEntity.noContent().build(); // 204 - Carrinho limpo com sucesso
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 - Carrinho não encontrado
	    }
	}



}
