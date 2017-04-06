package com.mantichub.core.util;

import static com.mantichub.core.util.StringUtils.isNotBlank;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class HTMLUtils {

	private static final String HTML_TAG_PATTERN = "\\<[^>]*>";

	public static Set<String> setByPattern(final String html, final String regex) {
		final Set<String> set = new HashSet<>();
		final Pattern pattern = Pattern.compile(regex);
		final Matcher matcher = pattern.matcher(html);
		while (matcher.find()) {
			final String node = matcher.group(1);
			set.add(node);
		}
		return set;
	}

	public static String trimValueByPattern(final String html, final String regex, final String... replacements) {
		return trim(replace(valueByPattern(html, regex), replacements));
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

	public static Date dateFromRegex(final String html, final String regexPattern, final String datePattern, final Locale locale, final String... replacements) {
		try {
			final String value = replace(valueByPattern(html, regexPattern), replacements);
			final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern, locale);
			return StringUtils.isNotBlank(value) ? simpleDateFormat.parse(value) : null;
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Date dateFromRegex(final String html, final String regexPattern, final String datePattern, final String... replacements) {
		return dateFromRegex(html, regexPattern, datePattern, Locale.getDefault(), replacements);
	}

	public static String nonhtmlValueByPattern(final String html, final String regex) {
		String value = valueByPattern(html, regex);
		value = isNotBlank(value) ? value.replaceAll(HTML_TAG_PATTERN, "") : value;
		return value;
	}

	public static Double doubleFromRegex(final String html, final String regex) {
		final String value = valueByPattern(html, regex);
		if (isNotBlank(value)) {
			try {
				return new Double(value);
			} catch (final Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public static String trim(String value) {
		if (isNotBlank(value)) {
			value = value.trim();
		}
		return value;
	}
	
	public static String replace(String value, final String... replacements) {
		if (isNotBlank(value) && (replacements != null)) {
			for (final String replacement : replacements) {
				value = replace(value, replacement);
			}
		}
		return value;
	}
	
	public static String replace(String value, final String replacement) {
		if (isNotBlank(replacement)) {
			final String word = replacement.substring(0, replacement.indexOf("|"));
			final String toWord = replacement.substring(replacement.indexOf("|") + 1, replacement.length());
			value = value.replaceAll(word, toWord);
			if (value.contains(word)) {
				return replace(value, replacement);
			}
		}
		return value;
	}
	

	{
		
	}

}
