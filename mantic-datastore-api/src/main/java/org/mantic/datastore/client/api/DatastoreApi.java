package org.mantic.datastore.client.api;

import java.util.List;

import org.apache.jena.rdf.model.Resource;

import com.mantichub.commons.domain.DatastoreTriple;
import com.mantichub.commons.domain.QueryResult;
import com.mantichub.commons.resource.ResourceObject;
import com.mantichub.core.http.ServerResponse;

public interface DatastoreApi {

	void create(Resource resource);
	
	ServerResponse create(List<DatastoreTriple> triples);

	QueryResult query(ResourceObject resource, Long l);

	void query(String queryStr);

}
