package com.mantichub.core.util;

import static com.mantichub.core.util.StringUtils.isNotBlank;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	public static String getString(final Date date, final String format) {
		if (date != null) {
			final DateFormat df = new SimpleDateFormat(format);
			final String ds = df.format(date);
			return ds;
		}
		return null;
	}

	public static Date getDate(final String string, final String format) {
		Date date = null;
		if (isNotBlank(string)) {
			final DateFormat df = new SimpleDateFormat(format);
			try {
				date = df.parse(string);
			} catch (final ParseException e) {
				e.printStackTrace();
			}
		}
		return date;
	}

}
