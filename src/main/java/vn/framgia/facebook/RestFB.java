package vn.framgia.facebook;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.fluent.Request;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restfb.DefaultFacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;

import vn.framgia.bean.UserInfo;
import vn.framgia.service.UserService;

@Component
public class RestFB {

	private static final Logger logger = Logger.getLogger(RestFB.class);

	@Autowired
	private UserService userService;

	private String appId;
	private String appSecret;
	private String redirectUrl;
	private String linkGetToken;

	@Autowired
	public RestFB(ReloadableResourceBundleMessageSource messageSource) {
		appId = messageSource.getMessage("fb.app_id", null, LocaleContextHolder.getLocale());
		appSecret = messageSource.getMessage("fb.app_secret", null, LocaleContextHolder.getLocale());
		redirectUrl = messageSource.getMessage("fb.redirect_url", null, LocaleContextHolder.getLocale());
		linkGetToken = messageSource.getMessage("fb.link_get_token", null, LocaleContextHolder.getLocale());
	}

	public RestFB(String appId, String appSecret, String redirectUrl, String linkGetToken) {
		this.appId = appId;
		this.appSecret = appSecret;
		this.redirectUrl = redirectUrl;
		this.linkGetToken = linkGetToken;
	}

	public String getToken(final String code) {
		try {
			String link = String.format(linkGetToken, appId, appSecret, redirectUrl, code);
			String response = Request.Get(link).execute().returnContent().asString();
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readTree(response).get("access_token");

			return node.textValue();
		} catch (Exception e) {
			logger.error("Error in getToken: " + e.getMessage());
			return null;
		}
	}

	public com.restfb.types.User getUserInfo(final String accessToken) {
		try {
			return new DefaultFacebookClient(accessToken, appSecret, Version.LATEST).fetchObject("me",
					com.restfb.types.User.class, Parameter.with("fields", "id, name, email"));
		} catch (Exception e) {
			logger.error("Error in getUserInfo: " + e.getMessage());
			return null;
		}
	}

	public UserDetails buildUser(com.restfb.types.User account) {
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		UserInfo user = userService.findUserByFacebookId(account.getId());
		String authorityName = StringUtils.isNotBlank(user.getEmail()) ? user.getEmail() : user.getFullname();
		authorities.add(new SimpleGrantedAuthority(user.getRole()));
		UserDetails userDetail = new User(authorityName, "", enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
		return userDetail;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

}
