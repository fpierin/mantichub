package org.mantic.datastore.resource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.Response;

import org.mantic.datastore.service.DatastoreService;

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
		return Response.ok().build();
	}

	@Override
	public Response query(final ResourceObject resource, final Double radius, final Integer limit) {
		final QueryResult result = datastoreService.query(resource, radius, limit);
		return Response.ok(result).build();
	}
	
	@Override
	public Response infer(final String url) {
		datastoreService.infer(url);
		return Response.ok().build();
	}

	@Override
	public Response query(final String query, final String output) {
		final String result = datastoreService.query(query, output);
		return Response.ok(result).build();
	}

	@Override
	public Response probe() {
		return Response.ok().build();
	}

	@Override
	public Response resources(final String query, final Double radius) {
		final QueryResult result = datastoreService.query(query, radius);
		return Response.ok(result).build();
	}

}
