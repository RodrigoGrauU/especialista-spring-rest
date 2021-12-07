package com.algaworks.algafood.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Data;
import lombok.EqualsAndHashCode;


@JsonRootName("gastronomia")
@Data //anotação contém @Getter, @Setter, @EqualsAndHashcode, ...
@EqualsAndHashCode(onlyExplicitlyIncluded = true) //define que o equals e o hashcode considerado será apenas o que estiver com a anotação específica (ver Id)
@Entity
public class Cozinha {
	
//	@NotNull(groups = Groups.CozinhaId.class)
	@EqualsAndHashCode.Include //necessário para ser considerado na comparação utilizando do equals e hashcode
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	//@JsonIgnore
	//@JsonProperty("titulo")
	@Column(nullable = false)
	private String nome;

	@JsonIgnore
	@OneToMany(mappedBy = "cozinha")
	private List<Restaurante> restaurantes = new ArrayList<>();
}