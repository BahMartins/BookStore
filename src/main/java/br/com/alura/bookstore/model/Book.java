package br.com.alura.bookstore.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "books")
public class Book {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter
	private Integer id;
	
	@NotBlank
	@Getter @Setter
	private String title;
	
	@Lob
	@NotBlank
	@Length(min = 10)
	@Getter @Setter
	private String description;
	
	@NotNull
	@DecimalMin("0.0")
	@Getter @Setter
	private BigDecimal price;
	
	@NotNull
	@Min(10)
	@Getter @Setter
	private Integer numberPages;
	
	@Getter @Setter
	private String coverPath;
	
	@Temporal(TemporalType.DATE)
	@Getter @Setter
	private Calendar publicationDate;
	
	@NotNull
	@Size(min = 1, message = "The book needs at least one author!") 
	@ManyToMany 
	@Getter @Setter
	private List<Author> authors = new ArrayList<Author>();

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", description=" + description + ", price=" + price
				+ ", numberPages=" + numberPages + ", authors=" + authors + "]";
	}
}
