package org.mantichub.commons.domain;

import java.io.Serializable;

public class DatastoreTriple implements Serializable {
	
	private static final long serialVersionUID = 9202765805312093722L;
	
	private String object;
	private String predicate;
	private String subject;
	
	public DatastoreTriple() {
	}
	
	public DatastoreTriple(final String object, final String predicate, final String subject) {
		this.object = object;
		this.predicate = predicate;
		this.subject = subject;
	}
	
	public String getObject() {
		return object;
	}
	
	public void setObject(final String object) {
		this.object = object;
	}
	
	public String getPredicate() {
		return predicate;
	}
	
	public void setPredicate(final String predicate) {
		this.predicate = predicate;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(final String subject) {
		this.subject = subject;
	}
	
	@Override
	public String toString() {
		return "DatastoreTriple [object=" + object + ", predicate=" + predicate + ", subject=" + subject + "]";
	}
	
}
