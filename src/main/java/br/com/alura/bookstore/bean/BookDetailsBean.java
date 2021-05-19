package br.com.alura.bookstore.bean;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.alura.bookstore.daos.BookDao;
import br.com.alura.bookstore.model.Book;
import lombok.Getter;
import lombok.Setter;

@Model
public class BookDetailsBean {
	
	@Inject
	private BookDao bookDao;
	
	@Getter @Setter
	private Book book;
	
	@Getter @Setter
	private Integer id;
	
	public void details() {
		this.book =bookDao.findById(id);
	}
	
	
	
}
