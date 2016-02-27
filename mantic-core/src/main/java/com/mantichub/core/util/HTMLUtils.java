package com.mantichub.core.util;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTMLUtils {

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

}
