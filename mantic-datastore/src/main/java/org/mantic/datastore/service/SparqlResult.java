package org.mantic.datastore.service;

import java.io.Serializable;
import java.util.List;

import org.mantic.datastore.query.domain.Binding;

public class SparqlResult implements Serializable {
	
	private static final long serialVersionUID = -5190848031962124419L;
	
	private List<Binding> bindings;

	public SparqlResult() {
	}

	public List<Binding> getBindings() {
		return bindings;
	}

	public void setBindings(final List<Binding> bindings) {
		this.bindings = bindings;
	}

	@Override
	public String toString() {
		return "SparqlResult [bindings=" + bindings + "]";
	}

}
