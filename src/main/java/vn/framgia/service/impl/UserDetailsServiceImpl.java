package vn.framgia.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import vn.framgia.dao.UserDAO;
import vn.framgia.model.User;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserDAO userDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user;
		try {
			user = userDAO.findUserByEmail(username);
			if (user == null) {
				return null;
			}
			return createUserDetails(user);
		} catch (Exception e) {
			return null;
		}
	}

	private org.springframework.security.core.userdetails.User createUserDetails(User user) {
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPasswordHash(), enabled,
				accountNonExpired, credentialsNonExpired, accountNonLocked, user.getAuthorities());
	}
}
