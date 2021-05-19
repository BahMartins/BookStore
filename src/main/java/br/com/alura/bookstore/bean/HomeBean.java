package br.com.alura.bookstore.bean;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.alura.bookstore.daos.BookDao;
import br.com.alura.bookstore.model.Book;

@Model
public class HomeBean {
	
	@Inject
	private BookDao bookDao;
	
	public List<Book> lastReleases(){
		return bookDao.lastReleases();
	}

	public List<Book> moreBooks(){
		return bookDao.moreBooks();
	}
}
