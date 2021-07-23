package com.algaworks.algafood.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;



@Data //anotação contém @Getter, @Setter, @EqualsAndHashcode, ...
@EqualsAndHashCode(onlyExplicitlyIncluded = true) //define que o equals e o hashcode considerado será apenas o que estiver com a anotação específica (ver Id)
@Entity
public class Cozinha {
	
	@EqualsAndHashCode.Include //necessário para ser considerado na comparação utilizando do equals e hashcode
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
}
