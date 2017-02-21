package com.mantichub.core.util;

import java.util.List;

public class ListUtils {
	
	public static boolean isNotEmpty(final List<?> triples) {
		return (triples != null) && (!triples.isEmpty());
	}
	
}