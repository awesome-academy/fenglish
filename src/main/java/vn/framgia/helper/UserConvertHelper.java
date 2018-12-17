package vn.framgia.helper;

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

	public static void convertSingleUserInfoToUser(User user, UserInfo userInfo) {

		try {
			user.setBirthday(DatetimeConvertHelper.convertStringToDate(userInfo.getBirthday()));
			user.setId(userInfo.getId());
			user.setPasswordHash(userInfo.getPasswordHash());
			user.setPasswordResetToken(userInfo.getPasswordResetToken());
			user.setEmail(userInfo.getEmail());
			user.setAvatar(userInfo.getAvatar());
			user.setPhone(userInfo.getPhone());
			user.setFullname(userInfo.getFullname());
			user.setRole(userInfo.getRole());
			user.setGoogleAccount(userInfo.getGoogleAccount());
			user.setGender(getGenderToBoolean(userInfo.getGender()));
		} catch (Exception e) {
			logger.error("Error in convertUserInfoToUser: " + e.getMessage());
		}
	}

	public static List<User> convertUserInfoToUser(List<UserInfo> listUserInfo) {
		List<User> listUser = new ArrayList<User>();

		for (UserInfo userInfo : listUserInfo) {
			User user = new User();
			convertSingleUserInfoToUser(user, userInfo);
			listUser.add(user);
		}

		return listUser;
	}

	private static String getGenderToString(Boolean gender) {

		if (gender == true)
			return "Nam";

		return "Nu";
	}

	private static Boolean getGenderToBoolean(String gender) {

		if ("Nam".equals(gender))
			return true;

		return false;
	}

}
