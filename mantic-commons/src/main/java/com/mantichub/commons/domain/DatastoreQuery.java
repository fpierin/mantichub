package com.mantichub.commons.domain;

import java.io.Serializable;

public class DatastoreQuery implements Serializable {

	private static final long serialVersionUID = 8243195722799041786L;
	
	private String query;

	public DatastoreQuery() {
	}
	
	public DatastoreQuery(final String query) {
		super();
		this.query = query;
	}
	
	public String getQuery() {
		return query;
	}
	
	public void setQuery(final String query) {
		this.query = query;
	}
	
	@Override
	public String toString() {
		return "DatastoreQuery [query=" + query + "]";
	}
	
}
