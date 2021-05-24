package br.com.alura.bookstore.daos;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import br.com.alura.bookstore.model.Book;

@Stateful
public class BookDao {
	
	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	private EntityManager em;
	
	public void save(Book book) {
		em.persist(book);
	}

	public List<Book> listAll() {
		String jpql = "SELECT DISTINCT(b) FROM Book b "
				+ "JOIN FETCH b.authors";
		return em.createQuery(jpql, Book.class).getResultList();
	}

	public List<Book> lastReleases() {
		String jpql = "SELECT b FROM Book b ORDER BY b.id DESC";
		return em.createQuery(jpql, Book.class)
				.setMaxResults(5)
				.getResultList();
	}

	public List<Book> moreBooks() {
		String jpql = "SELECT b FROM Book b ORDER BY b.id DESC";
		return em.createQuery(jpql, Book.class)
				.setFirstResult(5)
				.getResultList();
	}

	public Book findById(Integer id) {
		// return em.find(Book.class, id);
		String jpql = "SELECT b FROM Book b JOIN FETCH b.authors "
			+ "WHERE b.id = :id";
		return em.createQuery(jpql, Book.class).setParameter("id", id).getSingleResult();
	}

}
