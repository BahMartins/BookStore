package br.com.alura.bookstore.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonArrayBuilder;

import br.com.alura.bookstore.daos.BuyDao;


@Named
@SessionScoped
public class ShoppingCar implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private BuyDao buyDao;
		
	private Set<ItemCar> items = new HashSet<>();

	public void add(ItemCar item) {
		items.add(item);
	}

	public List<ItemCar> getItems() {
		return new ArrayList<ItemCar>(items);
	}

	public BigDecimal getTotal(ItemCar item) {
		return item.getBook().getPrice().multiply(new BigDecimal(item.getQuantity()));
	}
	
	public BigDecimal getTotal() {
		BigDecimal total = BigDecimal.ZERO;
		for (ItemCar itemCar : items) {
			total = total.add(itemCar.getBook().getPrice().multiply(new BigDecimal(itemCar.getQuantity())));
		}
		return total;
	}

	public void remove(ItemCar item) {
		this.items.remove(item);
	}
	
	public Integer getQuantityTotal() {
		return items.stream().mapToInt(item -> item.getQuantity()).sum();
	}

	public void fineshed(Buy buy) {
		buy.setItems(this.toJson());
		buy.setTotal(getTotal());
		buyDao.save(buy); 
	}


	private String toJson() {
		JsonArrayBuilder builder = Json.createArrayBuilder();
		for (ItemCar item : items) {
			builder.add(Json.createObjectBuilder()
						.add("title", item.getBook().getTitle())
						.add("price", item.getBook().getPrice())
						.add("quantity", item.getQuantity())
						.add("total", getTotal(item))
					);
		}
		
		String json = builder.build().toString();
		System.out.println(json);
		return json;
	}
}
