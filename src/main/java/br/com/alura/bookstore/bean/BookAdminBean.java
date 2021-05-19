package br.com.alura.bookstore.bean;

import java.io.IOException;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import javax.transaction.Transactional;

import br.com.alura.bookstore.daos.AuthorDao;
import br.com.alura.bookstore.daos.BookDao;
import br.com.alura.bookstore.infra.FileSaver;
import br.com.alura.bookstore.model.Author;
import br.com.alura.bookstore.model.Book;
import lombok.Getter;
import lombok.Setter;


@Named
@RequestScoped
public class BookAdminBean {
	
	@Getter @Setter
	private Book book = new Book();
		
	@Inject
	private BookDao bookDao;
	
	@Inject
	private AuthorDao authorDao;

	@Inject
	private FacesContext context;
	
	@Getter @Setter
	private Part bookCover;

	
	@Transactional
	public String save() throws IOException {
		
		bookDao.save(book);
		
		FileSaver fileSaver = new FileSaver();
		book.setCoverPath(fileSaver.writePath(bookCover, "books"));
		
		
		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, new FacesMessage("Book successfully registered!"));
		
		return "/books/list?faces-redirect=true";
	}

	
	public List<Author> getAuthors(){
		return authorDao.listAll();
	}

	public String newAuthor() {
		return "/authors/authorsForm?faces-redirect=true";
	}
}
