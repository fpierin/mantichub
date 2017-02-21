package org.mantic.datastore.service;

import java.util.List;

import org.mantichub.commons.domain.DatastoreQuery;
import org.mantichub.commons.domain.DatastoreTriple;

public interface DatastoreService {
	
	void create(List<DatastoreTriple> triples);

	String query(DatastoreQuery datastoreQuery);

}
