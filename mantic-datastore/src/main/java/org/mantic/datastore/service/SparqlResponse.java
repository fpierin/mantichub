package org.mantic.datastore.service;

import java.io.Serializable;

public class SparqlResponse implements Serializable {

	private static final long serialVersionUID = -1049518346014199881L;
	
	private SparqlResult results;

	public SparqlResult getResults() {
		return results;
	}

	public void setResults(SparqlResult results) {
		this.results = results;
	}

	@Override
	public String toString() {
		return "SparqlResponse [results=" + results + "]";
	}

}
