package org.mantic.datastore;

import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;

public interface TdbRepository {
	
	void create(Model model);
	
	void read();
	
	void update();
	
	void delete();

	ResultSet query(String query);
	
	
}
