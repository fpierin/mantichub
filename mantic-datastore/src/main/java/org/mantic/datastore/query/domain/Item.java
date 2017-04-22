package org.mantic.datastore.query.domain;

import java.io.Serializable;

public class Item implements Serializable {

	private static final long serialVersionUID = -1506384750343024606L;
	
	private String type;
	private String value;

	public Item() {
	}

	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(final String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Item [type=" + type + ", value=" + value + "]";
	}

}
