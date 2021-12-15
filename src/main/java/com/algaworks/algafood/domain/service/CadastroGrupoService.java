package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.GrupoNaoEncontradoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.repository.GrupoRepository;

@Service
public class CadastroGrupoService {

	private static final String MSG_GRUPO_EM_USO = "O grupo que possui %d não pode ser removido pois possui registros associados a ele na base";
	
	@Autowired
	private GrupoRepository repository;
	
	public List<Grupo> listar() {
		return repository.findAll();
	}

	@Transactional
	public Grupo adicionar(Grupo grupo) {
		return repository.save(grupo);
	}
	
	@Transactional
	public Grupo alterar(Grupo grupo) {
		return repository.save(grupo);
	}

	public Grupo buscarOuFalhar(Long grupoId) {
		return repository.findById(grupoId)
				.orElseThrow(() -> new GrupoNaoEncontradoException(String.format("Não foi encontrado grupo com id %d", grupoId)));
	}

	@Transactional
	public void remover(Long grupoId) {
		try {
			repository.deleteById(grupoId);
			repository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new NegocioException(String.format(MSG_GRUPO_EM_USO, grupoId));
		} catch (EmptyResultDataAccessException e) {
			throw new GrupoNaoEncontradoException(grupoId);
		}
	}

}
