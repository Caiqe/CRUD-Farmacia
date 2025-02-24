package com.generation.farmacia.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("SUPLEMENTO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Suplemento extends Produto{

	
	@NotBlank(message = "O atributo 'tipo' deve ser preenchido.")
	private String tipo; // Exemplo: Proteína, Vitamina, Ômega 3

	@NotNull(message = "O atributo 'peso/quantidade' deve ser preenchido.")
	private Double quantidade; // Exemplo: 500g, 60 cápsulas
}
