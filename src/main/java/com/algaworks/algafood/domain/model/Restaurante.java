package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.algaworks.algafood.core.validation.ValorZeroIncluiDescricao;

//import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

@ValorZeroIncluiDescricao(valorField = "taxaFrete", descricaoField = "nome", descricaoObrigatoria = "Frete Grátis")
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
//	@NotBlank
	@Column(nullable = false)
	private String nome;
	
	@CreationTimestamp
	@Column(nullable = false,
	columnDefinition = "datetime") //necessário para que não se cria o length para milissegundos (==0)
	private OffsetDateTime dataCadastro;
	
	@UpdateTimestamp
	@Column(nullable = false, 
	columnDefinition = "datetime") 
	private OffsetDateTime dataAtualizacao;
	
//	@DecimalMin("0")
//	@NotNull
//	@PositiveOrZero
//	@TaxaFrete
//	@Multiplo(numero = 5)
	@Column(name = "taxa_frete", nullable = false)
	private BigDecimal taxaFrete;

//	@Valid
//	@ConvertGroup(from = Default.class, to = Groups.CozinhaId.class)
//	@NotNull
	//@JsonIgnoreProperties("hibernateLazyInitializer")
	//@JsonIgnore
	@JoinColumn(name = "cozinha_id", nullable = false)
//	@ManyToOne(fetch = FetchType.LAZY)
	@ManyToOne
	private Cozinha cozinha;
	
	@Embedded //indica que a propriedade é uma parte da entidade restaurante
	private Endereco endereco;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "restaurante_forma_pagamento", //nome da table que faz a associação
		joinColumns = @JoinColumn(name = "restaurante_id"), //nome da coluna referente a restaurante
		inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id")) //nome da coluna referente a forma de pagamento
	private Set<FormaPagamento> formasPagamento = new HashSet<>();
	
	@OneToMany(mappedBy = "restaurante")
	List<Produto> produtos = new ArrayList<>();
	
	private Boolean ativo = Boolean.TRUE;
	
	private Boolean aberto = Boolean.FALSE;
	
	@ManyToMany
	@JoinTable(name = "restaurante_usuario_responsavel",
	        joinColumns = @JoinColumn(name = "restaurante_id"),
	        inverseJoinColumns = @JoinColumn(name = "usuario_id"))
	private Set<Usuario> responsaveis = new HashSet<>();        

	
	public void ativar() {
		setAtivo(true);
	}
	
	public void inativar() {
		setAtivo(false);
	}
	
	public boolean associarFormaPagamento(FormaPagamento formaPagamento) {
		return formasPagamento.add(formaPagamento);
	}
	
	public boolean desassociarFormaPagamento(FormaPagamento formaPagamento) {
		return formasPagamento.remove(formaPagamento);
	}

	public void fechar() {
		setAberto(false);
	}

	public void abrir() {
		setAberto(true);
	}
	
	public boolean removerResponsavel(Usuario usuario) {
	    return getResponsaveis().remove(usuario);
	}

	public boolean adicionarResponsavel(Usuario usuario) {
	    return getResponsaveis().add(usuario);
	}
	
	public boolean aceitaFormaPagamento(FormaPagamento formaPagamento) {
	    return getFormasPagamento().contains(formaPagamento);
	}

	public boolean naoAceitaFormaPagamento(FormaPagamento formaPagamento) {
	    return !aceitaFormaPagamento(formaPagamento);
	}

}