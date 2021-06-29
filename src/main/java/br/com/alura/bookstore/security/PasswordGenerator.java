package br.com.alura.bookstore.security;

import java.security.MessageDigest;

import org.jboss.security.Base64Encoder;

public class PasswordGenerator {
	
	public static void main(String[] args) {
		System.out.println(new PasswordGenerator().generate("123"));
	}
	
	
	public String generate(String password) {
		try {
			byte[] digest = MessageDigest.getInstance("sha-256").digest(password.getBytes());
			
			return Base64Encoder.encode(digest);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
