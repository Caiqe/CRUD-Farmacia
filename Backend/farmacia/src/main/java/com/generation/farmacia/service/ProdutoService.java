package com.generation.farmacia.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.farmacia.model.Produto;
import com.generation.farmacia.repository.ProdutoRepository;

@Service
public class ProdutoService {

	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public List<Produto> checaValidade(){
		
		LocalDate dataAtual = LocalDate.now(); 
		LocalDate dataLimite = dataAtual.plusMonths(1);
		
		List<Produto> produtosVencendo = produtoRepository.findByValidadeBefore(dataLimite);
		
		return produtosVencendo;
		
	}
}
