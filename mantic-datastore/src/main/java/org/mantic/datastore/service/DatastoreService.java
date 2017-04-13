package org.mantic.datastore.service;

import java.util.List;

import com.mantichub.commons.domain.DatastoreQuery;
import com.mantichub.commons.domain.DatastoreTriple;

public interface DatastoreService {
	
	void create(List<DatastoreTriple> triples);

	String query(DatastoreQuery datastoreQuery);

	String query(String query, String output);
	
	void infer();


}
