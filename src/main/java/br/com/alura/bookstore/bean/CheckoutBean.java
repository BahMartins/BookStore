package br.com.alura.bookstore.bean;

import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import br.com.alura.bookstore.model.Buy;
import br.com.alura.bookstore.model.ShoppingCar;
import br.com.alura.bookstore.model.User;
import lombok.Getter;
import lombok.Setter;


@Model
public class CheckoutBean {
	
	@Getter @Setter
	private User user = new User();
	
	@Inject
	private ShoppingCar shoppingCar;
	
	@Inject
	private FacesContext facesContext;

	@Transactional
	public void fineshed() {
		Buy buy = new Buy();
		buy.setUser(user);
		shoppingCar.fineshed(buy);
		
		String contextName = facesContext.getExternalContext().getRequestContextPath();
		HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
		
		response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
		response.setHeader("Location", contextName + "/services/payment?uuid=" + buy.getUuid());
		
	}

}
