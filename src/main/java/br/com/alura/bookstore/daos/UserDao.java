package br.com.alura.bookstore.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.alura.bookstore.model.User;

public class UserDao {
	
	@PersistenceContext
	private EntityManager em;

	public void save(User user) {
		em.persist(user);
	}
	
	
	
}
