package com.algaworks.algafood.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.domain.model.Cozinha;

/**
 * Classe para resgatar os objetos do tipo Cozinha do banco de dados
 * @author grauu
 *
 */

@Component
public class CadastroCozinha {

	@PersistenceContext
	private EntityManager manager;
	
	public List<Cozinha> listar() {
		/**
		 * Utilizada JPQL para a consulta
		 */
		return manager.createQuery("from Cozinha", Cozinha.class).getResultList();
	}
}
