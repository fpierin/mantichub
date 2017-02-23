package com.mantichub.core.util;

import java.util.List;

public class ListUtils {
	
	public static boolean isNotEmpty(final List<?> list) {
		return !isEmpty(list);
	}
	
	public static boolean isEmpty(final List<?> list) {
		return (list == null) || (list.isEmpty());
	}	
	
}