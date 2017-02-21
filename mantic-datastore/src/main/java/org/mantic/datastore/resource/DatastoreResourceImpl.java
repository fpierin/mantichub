package org.mantic.datastore.resource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.Response;

import org.mantic.datastore.service.DatastoreService;
import org.mantichub.commons.domain.DatastoreQuery;
import org.mantichub.commons.domain.DatastoreTriple;

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
	public Response query(final DatastoreQuery datastoreQuery) {
		final String result = datastoreService.query(datastoreQuery);
		return Response.ok(result).build();
	}

}
