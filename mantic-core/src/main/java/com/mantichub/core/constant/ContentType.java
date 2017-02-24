package com.mantichub.core.constant;


public enum ContentType {

	APPLICATION_FORM_URLENCODED("application/x-www-form-urlencoded"),
	JSON("application/json"),
	JSON_VND_RESOURCE("application/vnd.resource+json"),
	JSON_BILLING_V1("application/uol.com.br.billing-v1+json; charset=UTF-8;");

	private final String type;

	private ContentType(final String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return type;
	}

}