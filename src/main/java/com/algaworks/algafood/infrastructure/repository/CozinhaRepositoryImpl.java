package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

@Component
public class CozinhaRepositoryImpl implements CozinhaRepository {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
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
	@Override
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
	@Override
	public Cozinha salvar(Cozinha cozinha) {
		return manager.merge(cozinha);
	}
	
	@Transactional
	@Override
	public void remover(Cozinha cozinha) {
		cozinha = buscar(cozinha.getId());
		manager.remove(cozinha);
	}

}
