package com.generation.farmacia.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_produtos")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_produto", discriminatorType = DiscriminatorType.STRING)
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "tipoProduto")
@JsonSubTypes({
    @JsonSubTypes.Type(value = Cosmetico.class, name = "COSMÉTICO"),
    @JsonSubTypes.Type(value = Medicamento.class, name = "MEDICAMENTO")
})
public abstract class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O atributo 'nome' deve ser preechido.")
	@Size(min = 1, max = 255, message = "O atributo 'nome' deve conter entre 1 e 255 caracteres.")
	private String nome;

	@NotBlank(message = "O atributo 'descricao' deve ser preenchido.")
	@Size(min = 1, max = 1000, message = "O atributo 'descricao' deve conter entre 1 e 1000 caracteres.")
	private String descricao;

	@NotNull(message = "O atributo 'valor' deve ser preenchido.")
	@Positive(message = "O atributo 'valor' deve ser positivo.")
	private BigDecimal valor;
	
	@Size(max = 5000, message = "O link com a imagem deve ter no máximo 5000 caracteres")
	private String imagem;

	@ManyToOne
	@JsonIgnoreProperties("produtos")
	private Categoria categoria;
	
	@OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
    private List<PedidoProduto> pedidoProdutos;
	
	@OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<CarrinhoProduto> carrinhoProdutos;
	
	
}


	