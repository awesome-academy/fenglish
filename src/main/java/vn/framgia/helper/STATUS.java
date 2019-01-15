package vn.framgia.helper;

public enum STATUS {

	SUCCESS, CONNECTED;

	@Override
	public String toString() {
		if (this == CONNECTED) {
			return "connected";
		}
		
		return "success";
	}

}
