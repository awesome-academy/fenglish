package vn.framgia.config;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import vn.framgia.helper.ROLES;

public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private static final Logger logger = Logger.getLogger(MyAuthenticationSuccessHandler.class);

	@Override
	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Authentication authentication) {
		HttpSession session = httpServletRequest.getSession();
		boolean hasAdminRole = authentication.getAuthorities().stream()
				.anyMatch(r -> r.getAuthority().equals(ROLES.ADMIN.toString()));
		session.setAttribute("userName", authentication.getName());
		session.setAttribute("isAdmin", hasAdminRole);
		try {
			if (hasAdminRole) {
				httpServletResponse.sendRedirect("admin");
			} else {
				httpServletResponse.sendRedirect("home");
			}
		} catch (IOException e) {
			logger.error("Error in Authenication Handler: " + e.getMessage());
		}
	}
}