package br.com.alura.bookstore.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "system_roles")
public class SystemRole {
	
	@Id
	@Getter @Setter
	private String name;

	@Deprecated
	public SystemRole() {}
	
	public SystemRole(String name) {
		this.name = name;
	}
	
	
	
	
}
