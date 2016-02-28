package com.mantichub.core.util;

import static com.mantichub.core.util.StringUtils.isNotBlank;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTMLUtils {

	private static final String HTML_TAG_PATTERN = "\\<[^>]*>";

	public static Set<String> setByPattern(final String html, final String regex) {
		final Set<String> set = new HashSet<String>();
		final Pattern pattern = Pattern.compile(regex);
		final Matcher matcher = pattern.matcher(html);
		while (matcher.find()) {
			final String node = matcher.group(1);
			set.add(node);
		}
		return set;
	}

	public static String valueByPattern(final String html, final String regex) {
		final Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
		final Matcher matcher = pattern.matcher(html);
		if (matcher.find()) {
			final String group = matcher.group(1);
			if (StringUtils.isNotBlank(group)) {
				return group.trim();
			}
		}
		return null;
	}

	public static Date dateFromRegex(final String html, final String regexPattern, final String datePattern) {
		try {
			final String value = valueByPattern(html, regexPattern);
			final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern);
			return StringUtils.isNotBlank(value) ? simpleDateFormat.parse(value) : null;
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String nonhtmlValueByPattern(final String html, final String regex) {
		String value = valueByPattern(html, regex);
		value = isNotBlank(value) ? value.replaceAll(HTML_TAG_PATTERN, "") : value;
		return value;
	}

	public static Double doubleFromRegex(final String html, final String regex) {
		final String value = valueByPattern(html, regex);
		return StringUtils.isNotBlank(value) ? new Double(value) : null;
	}

}
