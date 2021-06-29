package br.com.alura.bookstore.security;

import java.security.Principal;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import br.com.alura.bookstore.daos.SecurityDao;
import br.com.alura.bookstore.model.SystemUser;

@Model
public class CurrentUser {
	
	@Inject
	private HttpServletRequest request;

	@Inject
	private SecurityDao securityDao;
	
	private SystemUser systemUser;
	
	@PostConstruct
	public void loadSystemUser() {
		Principal userPrincipal = request.getUserPrincipal();
		
		if(userPrincipal != null) {
			String email = request.getUserPrincipal().getName();
			systemUser = securityDao.findByEmail(email);
		}
	}
	
	public SystemUser get() {
		return systemUser;
	}
	
	public boolean hasRole(String role) {
		return request.isUserInRole(role);
	}
	
	public String logout() {
		request.getSession().invalidate();
		
		return "/admin/books/list.xhtml?faces-redirect=true";
	}
	
	
}
