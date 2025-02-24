package com.generation.farmacia.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.farmacia.model.Carrinho;
import com.generation.farmacia.model.CarrinhoProduto;
import com.generation.farmacia.model.Produto;
import com.generation.farmacia.repository.CarrinhoProdutoRepository;
import com.generation.farmacia.repository.CarrinhoRepository;
import com.generation.farmacia.repository.ProdutoRepository;

import jakarta.transaction.Transactional;

@Service
public class CarrinhoService {

	@Autowired
	private CarrinhoRepository carrinhoRepository;

	@Autowired
	private CarrinhoProdutoRepository carrinhoProdutoRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	public List<Produto> listarProdutos(Long carrinhoId) {
		Carrinho carrinho = carrinhoRepository.findById(carrinhoId)
				.orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));

		return carrinhoProdutoRepository.findByCarrinho(carrinho).stream().map(CarrinhoProduto::getProduto)
				.collect(Collectors.toList());
	}

	public Produto adicionarProduto(Long carrinhoId, Long produtoId) {
		CarrinhoProduto carrinhoProduto = new CarrinhoProduto();

		carrinhoProduto.setProduto(produtoRepository.findById(produtoId).get());
		carrinhoProduto.setCarrinho(carrinhoRepository.findById(carrinhoId).get());
		carrinhoProdutoRepository.save(carrinhoProduto);

		return produtoRepository.findById(produtoId).get();

	}

	@Transactional
	public boolean removerProduto(Long carrinhoId, Long produtoId) {
		Carrinho carrinho = carrinhoRepository.findById(carrinhoId)
				.orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));

		Produto produto = produtoRepository.findById(produtoId)
				.orElseThrow(() -> new RuntimeException("Produto não encontrado"));

		Optional<CarrinhoProduto> carrinhoProdutoOpt = carrinhoProdutoRepository.findByCarrinhoAndProduto(carrinho,
				produto);

		if (carrinhoProdutoOpt.isPresent()) {
			carrinhoProdutoRepository.delete(carrinhoProdutoOpt.get());
			return true;
		}

		return false;
	}
	
	@Transactional
	public boolean limparCarrinho(Long carrinhoId) {
	    Carrinho carrinho = carrinhoRepository.findById(carrinhoId)
	            .orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));

	    List<CarrinhoProduto> itens = carrinhoProdutoRepository.findByCarrinho(carrinho);

	    if (!itens.isEmpty()) {
	        carrinhoProdutoRepository.deleteAll(itens); // Remove todos os produtos do carrinho
	        return true;
	    }

	    return false; // O carrinho já estava vazio
	}


}
