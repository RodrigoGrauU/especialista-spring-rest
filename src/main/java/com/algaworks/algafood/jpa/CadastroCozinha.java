package com.algaworks.algafood.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
	
	/*
	 * Realiza a busca por id
	 */
	@Transactional
	public Cozinha buscar(Long id) {
		return manager.find(Cozinha.class, id);
	}
	
	/**
	 * Persiste as informações do objeto no banco de dados e retorna o objeto com as informações persistida (se tiver
	 * um id gerado, essa informação é retornada também)
	 * 
	 * Caso o objeto passado como parâmetro já tiver um id salvo no banco, então esse objeto terá suas informações 
	 * atualizadas no banco, conforme o id.
	 * @param cozinha
	 * @return
	 */
	@Transactional
	public Cozinha salvar(Cozinha cozinha) {
		return manager.merge(cozinha);
	}
}
