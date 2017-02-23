package org.mantic.datastore.repository;

import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.mantichub.commons.domain.DatastoreTriple;

public interface DatastoreRepository {
	
	void create(Model model);

	void create(DatastoreTriple triple);
	
	void create(String subject, String predicate, String object);
	
	ResultSet query(String query);

	boolean find(DatastoreTriple triple);

	String queryFrom(DatastoreTriple triple);

	
	
	
}
