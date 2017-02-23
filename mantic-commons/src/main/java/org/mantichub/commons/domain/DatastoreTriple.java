package org.mantichub.commons.domain;

import java.io.Serializable;

public class DatastoreTriple implements Serializable {

	private static final long serialVersionUID = 9202765805312093722L;

	private TripleNode object;
	private TripleNode predicate;
	private TripleNode subject;

	public DatastoreTriple() {
	}

	public DatastoreTriple(final TripleNode subject, final TripleNode predicate, final TripleNode object) {
		this.object = object;
		this.predicate = predicate;
		this.subject = subject;
	}

	public TripleNode getObject() {
		return object;
	}

	public void setObject(final TripleNode object) {
		this.object = object;
	}
	
	public TripleNode getPredicate() {
		return predicate;
	}

	public void setPredicate(final TripleNode predicate) {
		this.predicate = predicate;
	}

	public TripleNode getSubject() {
		return subject;
	}

	public void setSubject(final TripleNode subject) {
		this.subject = subject;
	}

	@Override
	public String toString() {
		return "DatastoreTriple [object=" + object + ", predicate=" + predicate + ", subject=" + subject + "]";
	}

}
