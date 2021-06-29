package br.com.alura.bookstore.conf;

import javax.ejb.Singleton;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSDestinationDefinitions;

@JMSDestinationDefinitions({
	@JMSDestinationDefinition(name="java:/jms/topics/ShoppingBuyTopic", interfaceName = "java.jms.Topic", destinationName = "java:/jms/topics/ShoppingBuyTopic")
})
@Singleton
public class ConfigureJMSDestination {

}
