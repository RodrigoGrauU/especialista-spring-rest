package com.algaworks.algafood.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.model.Permissao;

public interface GrupoRepository extends JpaRepository<Grupo, Long> {

	@Query("SELECT p FROM Grupo g JOIN g.permissoes p where g.id = :grupoId and p.id = :permissaoId")
	Optional<Permissao> findByPermissao(@Param("grupoId") Long grupoId, @Param("permissaoId") Long permissaoId);

}
