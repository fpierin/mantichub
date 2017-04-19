package org.mantic.client.api.resource;

import javax.inject.Named;
import javax.ws.rs.core.Response;

@Named("queryResource")
public class QueryResourceImpl implements QueryResource {

	public QueryResourceImpl() {
	}
	
	@Override
	public Response probe() {
		return Response.ok().build();
	}


}
