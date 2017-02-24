package com.mantichub.commons.domain;

import java.io.Serializable;

public class DatastoreTriple implements Cloneable, Serializable {
	
	private static final long serialVersionUID = 9202765805312093722L;
	
	private TripleNode subject;
	private TripleNode predicate;
	private TripleNode object;
	
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
		return "DatastoreTriple [subject=" + subject + ", predicate=" + predicate + ", object=" + object + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((object == null) ? 0 : object.hashCode());
		result = (prime * result) + ((predicate == null) ? 0 : predicate.hashCode());
		result = (prime * result) + ((subject == null) ? 0 : subject.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final DatastoreTriple other = (DatastoreTriple) obj;
		if (object == null) {
			if (other.object != null) {
				return false;
			}
		} else if (!object.equals(other.object)) {
			return false;
		}
		if (predicate == null) {
			if (other.predicate != null) {
				return false;
			}
		} else if (!predicate.equals(other.predicate)) {
			return false;
		}
		if (subject == null) {
			if (other.subject != null) {
				return false;
			}
		} else if (!subject.equals(other.subject)) {
			return false;
		}
		return true;
	}

	@Override
	public DatastoreTriple clone() {
		return new DatastoreTriple(subject, predicate, object);
	}
	
	public static void main(final String[] args) {
		final TripleNode s = new TripleNode();
		s.setNamespace("http://www.mantichub.com.br#");
		s.setValue("mp");
		final DatastoreTriple datastoreTriple = new DatastoreTriple(s, s, s);
		System.out.println(datastoreTriple.clone());
	}

}
