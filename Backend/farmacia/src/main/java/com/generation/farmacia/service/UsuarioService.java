package com.generation.farmacia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.farmacia.model.Carrinho;
import com.generation.farmacia.model.Usuario;
import com.generation.farmacia.repository.CarrinhoRepository;
import com.generation.farmacia.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private CarrinhoRepository carrinhoRepository;
	
	public Usuario criarUsuario(Usuario usuario) {
		usuario = usuarioRepository.save(usuario);
		
		Carrinho carrinho = new Carrinho();
		carrinho.setId(usuario.getId());
		carrinho.setUsuario(usuario);
		carrinhoRepository.save(carrinho);
		
		return usuario;
	}

}
