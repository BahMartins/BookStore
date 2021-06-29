package br.com.alura.bookstore.model;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

import lombok.Getter;
import lombok.Setter;


public class Promo {

	@Getter @Setter
	private String title;
	
	@Getter @Setter
	private Book book = new Book();

	public String toJson() {
		JsonObjectBuilder promo = Json.createObjectBuilder();
		promo.add("title", title)
			 .add("bookId", book.getId());
		
		return promo.build().toString();
	}
	
	
	
	
}
