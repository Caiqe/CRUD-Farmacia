package com.generation.farmacia.model;

import lombok.Data;

@Data
public class UsuarioLogin {
	
	private Long id;
	private String nome;
	private String usuario;
	private String senha;
	private String token;
	
}
