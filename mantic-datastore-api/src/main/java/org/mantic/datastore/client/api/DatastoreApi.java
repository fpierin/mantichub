package org.mantic.datastore.client.api;

import java.util.List;

import org.apache.jena.rdf.model.Resource;

import com.mantichub.commons.domain.DatastoreTriple;
import com.mantichub.core.http.ServerResponse;

public interface DatastoreApi {

	void create(Resource resource);
	
	void query(String string);

	ServerResponse create(List<DatastoreTriple> triples);
	
}
