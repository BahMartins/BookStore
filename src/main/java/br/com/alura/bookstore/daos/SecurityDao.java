package br.com.alura.bookstore.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.alura.bookstore.model.SystemUser;

public class SecurityDao {
	
	@PersistenceContext
	private EntityManager em;

	public SystemUser findByEmail(String email) {
		return em.createQuery("SELECT su FROM SystemUser su WHERE su.email = :email", SystemUser.class)
				.setParameter("email", email)
				.getSingleResult();
	}
	
	

}
