package org.mantic.client.api.resource;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.Response;

import org.mantic.client.api.service.QueryService;

import com.mantichub.commons.domain.DatastoreQuery;

@Named("queryResource")
public class QueryResourceImpl implements QueryResource {

	private final QueryService queryService;

	@Inject
	public QueryResourceImpl(final QueryService queryService) {
		this.queryService = queryService;
	}
	
	@Override
	public Response probe() {
		return Response.ok().build();
	}

	@Override
	public Response query(final DatastoreQuery datastoreQuery) {
		queryService.query(datastoreQuery.getQuery());
		return Response.ok("ok").build();
	}


}
