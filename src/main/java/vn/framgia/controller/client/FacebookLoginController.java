package vn.framgia.controller.client;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.restfb.types.User;

import vn.framgia.controller.BaseController;
import vn.framgia.facebook.RestFB;
import vn.framgia.helper.ROLES;
import vn.framgia.helper.STATUS;

@Controller
@RequestMapping("/facebook")
public class FacebookLoginController extends BaseController {

	private static final String status = "status";

	@Autowired
	private RestFB fb;

	@PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Map<String, String> login(HttpServletRequest request, Authentication authentication) {
		Map<String, String> response = new HashedMap<String, String>();
		HttpSession session = request.getSession();
		
		if (authentication != null) {
			response.put(status, STATUS.CONNECTED.toString());
			return response;
		}

		String accessToken = request.getParameter("access_token");
		User user = fb.getUserInfo(accessToken);

		if (userService.findUserByFacebookId(user.getId()) == null) {
			userService.createNewFBSocial(user);
		}

		authentication = buildAuthentication(user, request);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		boolean hasAdminRole = authentication.getAuthorities().stream()
				.anyMatch(r -> r.getAuthority().equals(ROLES.ADMIN.toString()));

		session.setAttribute("userName", authentication.getName());
		session.setAttribute("isAdmin", hasAdminRole);

		response.put(status, STATUS.SUCCESS.toString());

		return response;
	}

	private UsernamePasswordAuthenticationToken buildAuthentication(User account, HttpServletRequest request) {
		UserDetails userDetail = fb.buildUser(account);
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, null,
				userDetail.getAuthorities());
		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

		return authentication;
	}

}
