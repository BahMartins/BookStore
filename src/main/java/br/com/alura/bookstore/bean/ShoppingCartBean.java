package br.com.alura.bookstore.bean;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.alura.bookstore.daos.BookDao;
import br.com.alura.bookstore.model.Book;
import br.com.alura.bookstore.model.ItemCar;
import br.com.alura.bookstore.model.ShoppingCar;

@Model
public class ShoppingCartBean {
	
	@Inject
	private BookDao bookDao;
	
	@Inject
	private ShoppingCar car;
	
	public String add(Integer id) {
		Book book = bookDao.findById(id);
		ItemCar item = new ItemCar(book);
		car.add(item);
		
		return "carrinho?faces-redirect=true";
		
	}
	
	public List<ItemCar> getItems(){
		return car.getItems();
	}
	
	public void remove(ItemCar item) {
		car.remove(item);
	}

}
