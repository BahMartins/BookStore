package br.com.alura.bookstore.daos;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import br.com.alura.bookstore.model.Buy;

public class BuyDao implements Serializable{

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void save(Buy buy) {
		em.persist(buy);
	}

	public Buy findByUuid(String uuid) {
		return em.createQuery("SELECT b FROM Buy b WHERE b.uuid = :uuid", Buy.class)
				.setParameter("uuid", uuid).getSingleResult();
	}
	
	
}
