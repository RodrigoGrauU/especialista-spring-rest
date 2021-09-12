package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.data.annotation.CreatedDate;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	private BigDecimal subtotal;
	
	private BigDecimal taxaFrete;
	
	private BigDecimal valorTotal;
	
	@CreatedDate
	private LocalDateTime dataCriacao;
	
	private LocalDateTime dataConfirmacao;
	
	private LocalDateTime dataCancelamento;
	
	private LocalDateTime dataEntrega;
	
	@Embedded
	private Endereco enderecoEntrega;
	
	@ManyToOne
	private FormaPagamento formaPagamento;
	
	@ManyToOne
	private Restaurante restaurante;
	
	private StatusPedido status;
	
	@OneToMany(mappedBy = "pedido")
	private List<ItemPedido> pedidos;
	
	@ManyToOne
	private Usuario cliente;
	
}
