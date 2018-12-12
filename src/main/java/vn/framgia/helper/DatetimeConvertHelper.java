package vn.framgia.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class DatetimeConvertHelper {

	private static final Logger logger = Logger.getLogger(DatetimeConvertHelper.class);
	private static final String dateFormat = "dd-MM-yyyy";

	public static String convertDateToString(Date date) {

		try {
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			return sdf.format(date);
		} catch (Exception e) {
			logger.error("Error in convertDateToString: " + e.getMessage());
		}
		
		return null;
	}

	public static Date convertStringToDate(String source) {

		try {
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			return sdf.parse(source);
		} catch (ParseException e) {
			logger.error("Error in convertStringToDate: " + e.getMessage());
		}
		
		return null;
	}
}
