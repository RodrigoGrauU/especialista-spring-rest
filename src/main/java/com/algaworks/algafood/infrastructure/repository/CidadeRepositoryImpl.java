package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;

public class CidadeRepositoryImpl  implements CidadeRepository {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Cidade> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cidade buscar(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cidade salvar(Cidade cidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remover(Cidade cidade) {
		// TODO Auto-generated method stub
		
	}

}
