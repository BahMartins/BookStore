package br.com.alura.bookstore.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

public class Payment {
	
	@Getter @Setter
	private BigDecimal value;

	public Payment(BigDecimal value) {
		this.value = value;
	}
	
	
	
}
