package br.com.alura.bookstore.service;

import java.net.URI;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import br.com.alura.bookstore.daos.BuyDao;
import br.com.alura.bookstore.model.Buy;

@Path("/payment")
public class PaymentService {

	@Inject
	private BuyDao buyDao;
	
	@Inject
	private PaymentGateway paymentGateway;
	
	@Context
	private ServletContext context;
	
	private static ExecutorService executor = Executors.newFixedThreadPool(50);
	
	@POST
	public void pay(@Suspended final AsyncResponse ar, @QueryParam("uuid") String uuid) {

		Buy buy = buyDao.findByUuid(uuid);
		String contextPath = context.getContextPath();
		
		executor.submit(() -> {
			try {
				String payment = paymentGateway.payment(buy.getTotal());
				System.out.println(payment);
				
				URI responseUri = UriBuilder.fromPath("http://localhost:8080" + contextPath + "/index.xhtml").queryParam("msg", "Successful purchase").build();
				Response response = Response.seeOther(responseUri).build();
				ar.resume(response);
			} catch (Exception e) {
				ar.resume(new WebApplicationException(e));
			}
		});
	}

	
}