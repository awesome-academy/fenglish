package vn.framgia.helper;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;

public class DatetimeConvertHelper {

	private static final Logger logger = Logger.getLogger(DatetimeConvertHelper.class);
	private static final String dateFormat = "dd/MM/yyyy";
	private static final String timeFormat = "dd/MM/yyyy hh:mm:ss";

	public static String convertDateToString(Timestamp date) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			return sdf.format(date);
		} catch (Exception e) {
			logger.error("Error in convertDateToString: " + e.getMessage());
			return null;
		}
	}

	public static Timestamp convertStringToDate(String source) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			return new Timestamp(sdf.parse(source).getTime());
		} catch (ParseException e) {
			logger.error("Error in convertStringToDate: " + e.getMessage());
			return null;
		}
	}

	public static String convertTimestampToString(Timestamp time) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(timeFormat);
			return sdf.format(time);
		} catch (Exception e) {
			logger.error("Error in convertTimestampToString: " + e.getMessage());
			return null;
		}
	}

	public static Timestamp convertStringToTimeStamp(String source) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(timeFormat);
			return new Timestamp(sdf.parse(source).getTime());
		} catch (Exception e) {
			logger.error("Error in convertStringToTimeStamp: " + e.getMessage());
			return null;
		}
	}

}
