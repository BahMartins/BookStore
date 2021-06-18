package br.com.alura.bookstore.model;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "buys")
public class Buy {
	
	@Id
	@Getter @Setter
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	
	@Getter @Setter
	@ManyToOne(cascade = CascadeType.PERSIST)
	private User user;
	
	@Getter @Setter
	private String items;
	
	@Getter @Setter
	private String uuid;
	
	@Getter @Setter
	private BigDecimal total;
	
	@PrePersist
	public void createUUID() {
		this.uuid = UUID.randomUUID().toString();
	}


}
