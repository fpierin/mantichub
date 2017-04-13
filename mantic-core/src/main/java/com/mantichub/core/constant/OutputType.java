package com.mantichub.core.constant;

import static com.mantichub.core.util.StringUtils.isNotBlank;

public enum OutputType {
	
	UNKNOW, CSV, JSON, TEXT, TSV, XML;
	
	public static OutputType from(String name) {
		if (isNotBlank(name)) {
			for (final OutputType output : OutputType.values()) {
				if (output.name().equalsIgnoreCase(name.trim())) {
					return output;
				}
			}
		}
		return UNKNOW;
	}

}
