package br.com.alura.bookstore.bean;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.alura.bookstore.daos.BookDao;
import br.com.alura.bookstore.model.Book;

@Model
public class ListBookAdminBean {
	
	@Inject
	private BookDao bookDao;
	
	private List<Book> books = new ArrayList<Book>();
	
	public List<Book> getBooks() {
		this.books = bookDao.listAll();
		return books;
	}

}
