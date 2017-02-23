package org.mantichub.commons.domain;

import java.io.Serializable;

public class TripleNode implements Serializable {

	private static final long serialVersionUID = 7846118528376240870L;
	
	private String namespace;
	private String value;

	public TripleNode() {
	}

	public TripleNode(final String namespace, final String value) {
		this.namespace = namespace;
		this.value = value;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return namespace != null ? namespace + value : value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((namespace == null) ? 0 : namespace.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TripleNode other = (TripleNode) obj;
		if (namespace == null) {
			if (other.namespace != null)
				return false;
		} else if (!namespace.equals(other.namespace))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	
	public boolean isLiteral() {
		return namespace == null || namespace.length() == 0;
	}

}
