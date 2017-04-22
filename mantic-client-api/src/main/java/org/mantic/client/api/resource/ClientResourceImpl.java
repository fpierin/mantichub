package org.mantic.client.api.resource;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.Response;

import org.mantic.client.api.service.ClientService;

import com.mantichub.commons.domain.QueryResult;
import com.mantichub.commons.resource.ResourceObject;

@Named("clientResource")
public class ClientResourceImpl implements ClientResource {

	private final ClientService queryService;

	@Inject
	public ClientResourceImpl(final ClientService queryService) {
		this.queryService = queryService;
	}
	
	@Override
	public Response probe() {
		return Response.ok().build();
	}

	@Override
	public Response query(final ResourceObject queryResource) {
		final QueryResult queryResult = queryService.query(queryResource);
		return Response.ok(queryResult).build();
	}

	@Override
	public Response query(final Long radius, final ResourceObject resource) {
		final QueryResult queryResult = queryService.query(resource, radius);
		return Response.ok(queryResult).build();
	}

}