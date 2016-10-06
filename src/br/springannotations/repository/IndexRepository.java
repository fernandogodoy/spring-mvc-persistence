package br.springannotations.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.springannotations.model.Index;

@Repository
@Transactional
public class IndexRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public void salvar(Index objeto) {
		entityManager.persist(objeto);
	}

	public Index find(Long id) {
		return entityManager.find(Index.class, id);
	}
}
