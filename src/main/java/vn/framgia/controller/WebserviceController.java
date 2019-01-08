package vn.framgia.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import com.fasterxml.jackson.databind.ObjectMapper;

import vn.framgia.bean.MessageInfo;
import vn.framgia.helper.ROLES;

@ServerEndpoint(value = "/chat")
public class WebserviceController {

	private static Set<Session> userSessions = Collections.synchronizedSet(new HashSet<>());
	private static Set<Session> adminSessions = Collections.synchronizedSet(new HashSet<>());
	private ObjectMapper mapper = new ObjectMapper();

	@OnOpen
	public void handleOpen(Session session) {
		if (getRoleUserFromSession(session).equals(ROLES.ADMIN.toString())) {
			adminSessions.add(session);
		}
		if (getRoleUserFromSession(session).equals(ROLES.USER.toString())) {
			userSessions.add(session);
		}
	}

	@OnMessage
	public void handleMessage(String message, Session session, EndpointConfig config) throws IOException {
		String userName = getUserNameFromSession(session);
		if (getRoleUserFromSession(session).equals(ROLES.ADMIN.toString())) {
			MessageInfo messageInfo = mapper.readValue(message, MessageInfo.class);
			Session userToSession = getSessionUserByUserName(messageInfo.getFrom());
			if (userToSession != null) {
				userToSession.getBasicRemote().sendText(userName + ": " + messageInfo.getContent());
			}
		}
		if (getRoleUserFromSession(session).equals(ROLES.USER.toString())) {
			for (Session adminSession : adminSessions) {
				MessageInfo messageInfo = new MessageInfo();
				messageInfo.setFrom(userName);
				messageInfo.setContent(message);
				String mess = mapper.writeValueAsString(messageInfo);
				adminSession.getBasicRemote().sendText(mess);
			}
		}
	}

	@OnClose
	public void handleClose(Session session) {
		if (getRoleUserFromSession(session).equals(ROLES.ADMIN.toString())) {
			adminSessions.remove(session);
		}
		if (getRoleUserFromSession(session).equals(ROLES.USER.toString())) {
			userSessions.remove(session);
		}
	}

	@OnError
	public void handleError(Throwable t) {
		t.printStackTrace();
	}

	private String getRoleUserFromSession(Session session) {
		if (session == null)
			return null;
		AbstractAuthenticationToken user = (AbstractAuthenticationToken) session.getUserPrincipal();
		return user.getAuthorities().iterator().next().getAuthority();
	}

	private String getUserNameFromSession(Session session) {
		if (session == null)
			return null;
		AbstractAuthenticationToken user = (AbstractAuthenticationToken) session.getUserPrincipal();
		return user.getName();
	}

	private Session getSessionUserByUserName(String userName) {
		for (Session userSession : userSessions) {
			if (userName.equals(getUserNameFromSession(userSession))) {
				return userSession;
			}
		}
		return null;
	}

}
