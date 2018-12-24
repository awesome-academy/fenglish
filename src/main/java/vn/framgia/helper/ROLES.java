package vn.framgia.helper;

public enum ROLES {

	ADMIN, USER, UNCONFIRM;

	@Override
	public String toString() {
		if (this == ADMIN) {
			return "ROLE_ADMIN";
		}
		if (this == USER) {
			return "ROLE_USER";
		}
		return "ROLE_UNCONFIRM";
	}

}
