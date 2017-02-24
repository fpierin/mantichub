package com.mantichub.commons.builder;

import com.mantichub.commons.domain.DatastoreTriple;
import com.mantichub.commons.domain.TripleNode;

public class DatastoreTripleBuilder {

	private TripleNode object;
	private TripleNode predicate;
	private TripleNode subject;
	
	public DatastoreTripleBuilder() {
	}
	
	public DatastoreTriple create() {
		return new DatastoreTriple(subject, predicate, object);
	}

	public DatastoreTripleBuilder withObject(final String namespace, final String value) {
		this.object = new TripleNode(namespace, value);
		return this;
	}

	public DatastoreTripleBuilder withPredicate(String namespace, String value) {
		this.predicate = new TripleNode(namespace, value);
		return this;
	}
	
	public DatastoreTripleBuilder withSubject(String namespace, String value) {
		this.subject = new TripleNode(namespace, value);
		return this;
	}	

}
