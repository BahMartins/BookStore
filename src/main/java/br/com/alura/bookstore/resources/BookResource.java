package br.com.alura.bookstore.resources;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.providers.jaxb.Wrapped;

import br.com.alura.bookstore.daos.BookDao;
import br.com.alura.bookstore.model.Book;


@Path("books")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class BookResource {
	
	@Inject
	private BookDao bookDao;
	
	@GET
	@Path("releases")
	@Wrapped(element = "books")
	public List<Book> lastReleases() {
		return bookDao.lastReleases();
	}
}
