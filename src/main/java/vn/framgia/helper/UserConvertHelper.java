package vn.framgia.helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import vn.framgia.bean.UserInfo;
import vn.framgia.model.User;

public class UserConvertHelper {

	private static final Logger logger = Logger.getLogger(UserConvertHelper.class);

	public static UserInfo convertSingleUserToUserInfo(User user) {

		UserInfo userInfo = new UserInfo();

		try {
			BeanUtils.copyProperties(userInfo, user);
			userInfo.setGender(getGenderToString(user.getGender()));
			userInfo.setBirthday(DatetimeConvertHelper.convertDateToString(user.getBirthday()));
		} catch (Exception e) {
			logger.error("Error in convertUserToInfo: " + e.getMessage());
		}

		return userInfo;
	}

	public static List<UserInfo> convertUserToUserInfo(List<User> listUser) {

		List<UserInfo> listUserInfo = new ArrayList<UserInfo>();

		for (User user : listUser) {
			listUserInfo.add(convertSingleUserToUserInfo(user));
		}
		return listUserInfo;
	}

	public static User convertSingleUserInfoToUser(UserInfo userInfo) {

		User user = new User();
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

		try {
			user.setBirthday(df.parse(userInfo.getBirthday()));
			user.setId(userInfo.getId());
			user.setPasswordHash(userInfo.getPasswordHash());
			user.setPasswordResetToken(userInfo.getPasswordResetToken());
			user.setEmail(userInfo.getEmail());
			user.setAvatar(userInfo.getAvatar());
			user.setPhone(userInfo.getPhone());
			user.setRole(userInfo.getRole());
			user.setGoogleAccount(userInfo.getGoogleAccount());
			user.setGender(getGenderToBoolean(userInfo.getGender()));
		} catch (ParseException e) {
			logger.error("Error in convertUserInfoToUser: " + e.getMessage());
		}

		return user;
	}

	public static List<User> convertUserInfoToUser(List<UserInfo> listUserInfo) {
		List<User> listUser = new ArrayList<User>();

		for (UserInfo userInfo : listUserInfo) {
			listUser.add(convertSingleUserInfoToUser(userInfo));
		}

		return listUser;
	}
	
	private static String getGenderToString(Boolean gender) {
		
		if (gender == true) return "Nam";
		
		return "Nu";
	}
	
	private static Boolean getGenderToBoolean(String gender) {
		
		if ("Nam".equals(gender)) return true;
		
		return false;
	}
	
}
