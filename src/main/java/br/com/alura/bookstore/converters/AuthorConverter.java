package br.com.alura.bookstore.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.alura.bookstore.model.Author;

@FacesConverter("authorConverter")
public class AuthorConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		if(value == null || value.trim().isEmpty()) {
			return null;
		}
		
		Author author = new Author();
		author.setId(Integer.valueOf(value));
		
		return author;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if(value == null) {
			return null;
		}
		
		Author author = (Author) value;
		
		return author.getId().toString();
	}
	
	

}
