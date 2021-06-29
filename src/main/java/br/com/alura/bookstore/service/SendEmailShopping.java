package br.com.alura.bookstore.service;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import br.com.alura.bookstore.daos.BuyDao;
import br.com.alura.bookstore.infra.MailSender;
import br.com.alura.bookstore.model.Buy;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/jms/topics/ShoppingBuyTopic"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "java.jms.Topic")
})
public class SendEmailShopping implements MessageListener {
	
	@Inject
	private MailSender mailSender;
	
	@Inject
	private BuyDao buyDao;
	
	@Override
	public void onMessage(Message message) {
		
		try {
			TextMessage textMessage = (TextMessage) message;
			
			Buy buy = buyDao.findByUuid(textMessage.getText());
			
			String messageBody = "Your purchase was successful and your order number is: " + buy.getUuid();
			mailSender.send("shopping@bookstore.com", buy.getUser().getEmail(), "Purchase made successfully", messageBody);
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}

	}

}
