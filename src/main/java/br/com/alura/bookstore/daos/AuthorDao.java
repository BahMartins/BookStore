package br.com.alura.bookstore.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.alura.bookstore.model.Author;

public class AuthorDao {
	
	@PersistenceContext
	private EntityManager em;
	
	public List<Author> listAll(){
		return em.createQuery("SELECT a FROM Author a", Author.class).getResultList();
	}

	public void save(Author author) {
		em.persist(author);
	}
}
