package com.generation.farmacia.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.farmacia.model.Carrinho;
import com.generation.farmacia.model.CarrinhoProduto;
import com.generation.farmacia.model.Produto;

@Repository
public interface CarrinhoProdutoRepository extends JpaRepository<CarrinhoProduto, Long>{

	List<CarrinhoProduto> findByCarrinho(Carrinho carrinho);
	Optional<CarrinhoProduto> findByCarrinhoAndProduto(Carrinho carrinho, Produto produto);

	
}
