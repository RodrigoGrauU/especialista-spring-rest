package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.algaworks.algafood.core.validation.Groups;
import com.algaworks.algafood.core.validation.Multiplo;
import com.fasterxml.jackson.annotation.JsonIgnore;

//import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurante {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	@NotNull
//	@NotEmpty
	@NotBlank
	@Column(nullable = false)
	private String nome;
	
	@JsonIgnore
	@CreationTimestamp
	@Column(nullable = false,
	columnDefinition = "datetime") //necessário para que não se cria o length para milissegundos (==0)
	private LocalDateTime dataCadastro;
	
	@JsonIgnore
	@UpdateTimestamp
	@Column(nullable = false, 
	columnDefinition = "datetime") 
	private LocalDateTime dataAtualizacao;
	
//	@DecimalMin("0")
	@NotNull
	@PositiveOrZero
//	@TaxaFrete
	@Multiplo(numero = 5)
	@Column(name = "taxa_frete", nullable = false)
	private BigDecimal taxaFrete;

	@Valid
	@ConvertGroup(from = Default.class, to = Groups.CozinhaId.class)
	@NotNull
	//@JsonIgnoreProperties("hibernateLazyInitializer")
	//@JsonIgnore
	@JoinColumn(name = "cozinha_id", nullable = false)
//	@ManyToOne(fetch = FetchType.LAZY)
	@ManyToOne
	private Cozinha cozinha;
	
	@JsonIgnore
	@Embedded //indica que a propriedade é uma parte da entidade restaurante
	private Endereco endereco;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "restaurante_forma_pagamento", //nome da table que faz a associação
		joinColumns = @JoinColumn(name = "restaurante_id"), //nome da coluna referente a restaurante
		inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id")) //nome da coluna referente a forma de pagamento
	private List<FormaPagamento> formasPagamento = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "restaurante")
	List<Produto> produtos = new ArrayList<>();
	
}