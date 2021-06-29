package br.com.alura.bookstore.websockets;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import br.com.alura.bookstore.model.Promo;

@ServerEndpoint(value = "/channel/promo")
public class PromosEndpoint {

	@Inject
	private UsersSession usersSession;
	
	
	@OnOpen
	public void onMessage(Session session) {
		usersSession.add(session);
	}
	
	
	public void send(Promo promo) {
		List<Session> sessions = usersSession.getUsers();
		
		for (Session session : sessions) {
			if(session.isOpen()) {
				try {
					session.getBasicRemote().sendText(promo.toJson());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@OnClose
	public void onClose(Session session, CloseReason closeReason) {
		usersSession.remove(session);
		System.out.println(closeReason);
		
	}
	
	
	
}
