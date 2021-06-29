package br.com.alura.bookstore.bean;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.alura.bookstore.model.Promo;
import br.com.alura.bookstore.websockets.PromosEndpoint;
import lombok.Getter;
import lombok.Setter;

@Model
public class AdminPromosBean {
	
	@Getter @Setter
	private Promo promo = new Promo();
	
	@Inject
	private PromosEndpoint promos;
	
	public void save() {
		promos.send(promo);
		
	}

}
