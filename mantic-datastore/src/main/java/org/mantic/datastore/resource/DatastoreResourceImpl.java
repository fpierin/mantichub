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
		return Response.accepted().build();
	}

	@Override
	public Response query(final ResourceObject resource, final Double radius) {
		final QueryResult result = datastoreService.query(resource, radius);
		return Response.ok(result).build();
	}
	
	@Override
	public Response infer() {
		datastoreService.infer();
		return Response.ok().build();
	}

	@Override
	public Response query(final String query, final String output) {
		final String result = datastoreService.query(query, output);
		return Response.ok(result).build();
	}

}
