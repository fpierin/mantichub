package org.mantic.datastore.service;

import java.util.List;

import com.mantichub.commons.domain.DatastoreTriple;
import com.mantichub.commons.domain.QueryResult;
import com.mantichub.commons.resource.ResourceObject;

public interface DatastoreService {
	
	void create(List<DatastoreTriple> triples);

	QueryResult query(ResourceObject resource, Double radius);

	String query(String query, String output);
	
	void infer();


}
