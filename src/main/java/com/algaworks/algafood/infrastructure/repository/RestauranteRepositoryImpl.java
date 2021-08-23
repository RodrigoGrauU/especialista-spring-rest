package com.algaworks.algafood.infrastructure.repository;

import static com.algaworks.algafood.infrastructure.repository.spec.RestauranteSpec.comFreteGratis;
import static com.algaworks.algafood.infrastructure.repository.spec.RestauranteSpec.comNomeSemelhante;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Predicate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {
	
	@PersistenceContext
	EntityManager manager;
	
	@Autowired @Lazy
	RestauranteRepository restauranteRepository;
	
	@Override
	public List<Restaurante> find(String nome, 
			BigDecimal taxaInicial, BigDecimal taxaFinal) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);
		Root<Restaurante> root = criteria.from(Restaurante.class);
		
		var predicates = new ArrayList<Predicate>();
		
		//construíndo o Predicate para a consulta
		if(StringUtils.hasText(nome)) {
			predicates.add(builder.like(root.get("nome"), "%" + nome + "%"));
		}
		
		if(taxaInicial != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaInicial));
		} 

		if(taxaFinal != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get("taxaFrete"), taxaFinal));
		}
		
		//aceita Predicate... retrictions. Faz o AND de cada predicate passado como parâmetro
		//Para consulta dinâmica, na estratégia adotada, é necessário transformar a List para Array
		criteria.where(predicates.toArray(new Predicate[0]));
		
		TypedQuery<Restaurante> query = manager.createQuery(criteria);
		return query.getResultList();
		}

		@Override
		public List<Restaurante> findComFreteGratis(String nome) {
			//utilização de métodos estáticos da classe RestauranteSpec
			return restauranteRepository.findAll(comFreteGratis().and(comNomeSemelhante(nome)));
	}
}
