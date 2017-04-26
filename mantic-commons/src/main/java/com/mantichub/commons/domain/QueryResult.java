	package com.mantichub.commons.domain;

import java.io.Serializable;
import java.util.Set;

import com.mantichub.commons.resource.ResourceObject;

public class QueryResult implements Serializable {

	private static final long serialVersionUID = 7971740297655348343L;

	private String sparqlQuery;
	private Set<ResourceObject> resources;
	
	public QueryResult() {
	}

	public void setSparqlQuery(final String sparqlQuery) {
		this.sparqlQuery = sparqlQuery;
	}

	public void setResources(final Set<ResourceObject> resources) {
		this.resources = resources;
	}

	public String getSparqlQuery() {
		return sparqlQuery;
	}

	public Set<ResourceObject> getResources() {
		return resources;
	}

}
