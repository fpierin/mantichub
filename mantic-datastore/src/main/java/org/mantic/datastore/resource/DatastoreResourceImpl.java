package org.mantic.datastore.resource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.Response;

import org.mantic.datastore.service.DatastoreService;

import com.mantichub.commons.domain.DatastoreQuery;
import com.mantichub.commons.domain.DatastoreTriple;
import com.mantichub.commons.domain.QueryResult;
import com.mantichub.commons.resource.ResourceObject;

@Named("datastoreResource")
public class DatastoreResourceImpl implements DatastoreResource {

	private final DatastoreService datastoreService;

	@Inject
	public DatastoreResourceImpl(final DatastoreService datastoreService) {
		this.datastoreService = datastoreService;
	}
	
	@Override
	public Response create(final List<DatastoreTriple> triples) {
		datastoreService.create(triples);
		return response("");
	}

	@Override
	public Response query(final ResourceObject resource, final Double radius, final Integer limit) {
		final QueryResult result = datastoreService.query(resource, radius, limit);
		return response(result);
	}
	
	@Override
	public Response infer(final String source) {
		datastoreService.infer(source);
		return response("");
	}

	@Override
	public Response query(final String query, final String output) {
		final String result = datastoreService.query(query, output);
		return response(result);
	}

	@Override
	public Response probe() {
		return response("");
	}

	@Override
	public Response resources(final String query, final Double radius) {
		final QueryResult result = datastoreService.query(query, radius);
		return response(result);
	}

	@Override
	public Response options() {
	    return response("");
	}
	
	private Response response(final Object result) {
		return Response.ok(result)
	            .header("Access-Control-Allow-Origin", "*")
	            .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
	            .header("Access-Control-Allow-Credentials", "true")
	            .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
	            .header("Access-Control-Max-Age", "1209600")
				.build();
	}

	@Override
	public Response datastoreQuery(final DatastoreQuery query) {
		final QueryResult result = datastoreService.query(query.getQuery(), 1.0);
		return response(result);
	}	

}
