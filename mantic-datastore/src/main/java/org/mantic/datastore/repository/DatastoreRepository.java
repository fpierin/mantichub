package org.mantic.datastore.repository;

import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;

public interface DatastoreRepository {
	
	void create(Model model);

	void create(String subject, String property, String object);
	
	ResultSet query(String query);
	
	
	
}
