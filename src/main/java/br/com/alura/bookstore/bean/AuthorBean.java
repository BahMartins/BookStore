package br.com.alura.bookstore.bean;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.alura.bookstore.daos.AuthorDao;
import br.com.alura.bookstore.model.Author;
import lombok.Getter;
import lombok.Setter;

@Model
public class AuthorBean {
	
	@Getter @Setter
	private Author author = new Author();

	@Inject
	private AuthorDao authorDao;
	
	@Inject
	private FacesContext context;
	
	@Transactional
	public String save() {
		authorDao.save(author);
		
		
		context.addMessage(null, new FacesMessage("Author successfully registered!"));
		return "/authors/authorsForm?faces-redirect=true";
		
	}
	
	
}
