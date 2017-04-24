package com.mantichub.core.constant;

public enum ContentType {

	APPLICATION_FORM_URLENCODED("application/x-www-form-urlencoded"),
	JSON("application/json; charset=utf-8");

	private final String type;

	private ContentType(final String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return type;
	}

}