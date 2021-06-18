package br.com.alura.bookstore.model;

import lombok.Getter;
import lombok.Setter;

public class ItemCar {
	
	@Getter @Setter
	private Book book;

	@Getter @Setter
	private Integer quantity;

	public ItemCar() {}
	
	public ItemCar(Book book2) {
		this.book = book2;
		this.quantity = 1;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((book == null) ? 0 : book.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemCar other = (ItemCar) obj;
		if (book == null) {
			if (other.book != null)
				return false;
		} else if (!book.equals(other.book))
			return false;
		return true;
	}

	
	
	
}
