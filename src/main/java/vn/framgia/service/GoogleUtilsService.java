package vn.framgia.service;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.springframework.security.core.userdetails.UserDetails;

import vn.framgia.bean.GoogleAccountInfo;

public interface GoogleUtilsService {
	public String getToken(final String code) throws ClientProtocolException, IOException;
	public GoogleAccountInfo getGoogleAccount(final String accessToken) throws ClientProtocolException, IOException;
	public UserDetails buildUser(GoogleAccountInfo googleAccountInfo);
}
