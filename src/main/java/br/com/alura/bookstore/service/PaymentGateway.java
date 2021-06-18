package br.com.alura.bookstore.service;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;

import br.com.alura.bookstore.model.Payment;

public class PaymentGateway implements Serializable{

	private static final long serialVersionUID = 1L;

	public String payment(BigDecimal total) {
		
		Payment payment = new Payment(total);

		String target = "http://book-payment.herokuapp.com/payment";
		Entity<Payment> json= Entity.json(payment);
		
		Client client = ClientBuilder.newClient();
		return client.target(target).request().post(json, String.class);
	}
	
	
}
