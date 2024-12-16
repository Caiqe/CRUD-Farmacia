package com.generation.farmacia.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_produtos")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank(message = "O atributo 'nome' deve ser preechido.")
	@Size(min = 1, max = 255, message = "O atributo 'nome' deve conter entre 1 e 255 caracteres.")
	private String nome;

	@NotBlank(message = "O atributo 'descricao' deve ser preenchido.")
	@Size(min = 1, max = 1000, message = "O atributo 'descricao' deve conter entre 1 e 1000 caracteres.")
	private String descricao;

	@NotNull(message = "O atributo 'valor' deve ser preenchido.")
	@Positive (message = "O atributo 'valor' deve ser positivo.")
	private BigDecimal valor;
	
	@FutureOrPresent(message = "o atributo 'validade' deve ser preenchido com uma data igual ou superior a data atual.")
	@NotNull(message =  "O atributo 'validade' deve ser preenchido.")
	private LocalDate validade;

	public LocalDate getValidade() {
		return validade;
	}

	public void setValidade(LocalDate validade) {
		this.validade = validade;
	}

	@ManyToOne
	@JsonIgnoreProperties("produtos")
	private Categoria categoria;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
