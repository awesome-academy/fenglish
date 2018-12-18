package vn.framgia.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import vn.framgia.bean.GoogleAccountInfo;
import vn.framgia.dao.UserDAO;
import vn.framgia.service.GoogleUtilsService;

@Component
@PropertySource(ignoreResourceNotFound = true, value = "classpath:google-login.properties")
public class GoogleUtilsServiceImpl implements GoogleUtilsService{
	@Autowired
	UserDAO userDAO;

	@Value("${google.app.id}")
	String googleAppId;
	@Value("${google.app.secret}")
	String googleAppSecret;
	@Value("${google.redirect.uri}")
	String googleRedirectUri;
	@Value("${google.link.get.token}")
	String googleLinkGetToken;
	@Value("${google.link.get.user_info}")
	String googleLinkGetUserInfo;
	@Value("${google.request.url}")
	String googleRequestUrl;

	public String getToken(final String code) throws ClientProtocolException, IOException {

		String response = Request.Post(googleLinkGetToken)
				.bodyForm(Form.form().add("client_id", googleAppId)
						.add("client_secret", googleAppSecret)
						.add("redirect_uri", googleRedirectUri).add("code", code)
						.add("grant_type", "authorization_code").build())
				.execute().returnContent().asString();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(response).get("access_token");
		return node.textValue();
	}

	public GoogleAccountInfo getGoogleAccount(final String accessToken) throws ClientProtocolException, IOException {
		String link = googleLinkGetUserInfo + accessToken;
		String response = Request.Get(link).execute().returnContent().asString();
		ObjectMapper mapper = new ObjectMapper();
		GoogleAccountInfo googleAccountInfo = mapper.readValue(response, GoogleAccountInfo.class);
		return googleAccountInfo;
	}

	public UserDetails buildUser(GoogleAccountInfo accountInfo) {
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		vn.framgia.model.User user = userDAO.findUserByEmail(accountInfo.getEmail());
		authorities.add(new SimpleGrantedAuthority(user.getRole()));
		UserDetails userDetail = new User(accountInfo.getEmail(), "", enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
		return userDetail;
	}
}