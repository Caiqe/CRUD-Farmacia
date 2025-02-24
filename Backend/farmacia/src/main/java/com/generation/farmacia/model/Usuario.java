package com.generation.farmacia.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name ="tb_usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "O Atributo nome é obrigatório")
	@Size (min = 3, max = 255, message = "O Atributo nome pode conter no máximo 255 caracteres e mo mínimo 3")
	private String nome;
	
	
	@NotBlank(message = "O Atributo usuario é obrigatório")
	@Email (message = "O Atributo ususario deve ser um email válido")
	private String usuario;
	
	@NotBlank(message = "Atributo senha é obrigatório")
	@Size(min=8, message = "O Atributo senha deve conter no mínimo 8 caracteres")
	private String senha;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("usuario")
	private List<Pedido> Pedido;
	
	@OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
	private Carrinho carrinho;
}
