package org.mantic.datastore.client.api;

import org.apache.jena.rdf.model.Resource;

public interface DatastoreApi {

	void create(Resource resource);
	
	void query(String string);
	
}
