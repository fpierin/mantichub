package org.mantic.client.api.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mantichub.commons.domain.DatastoreQuery;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
@Api(tags = "Query")
@Path("/")
public interface QueryResource {
	
	@GET
	@Path("/probe")
	@ApiOperation(value = "Probe da aplicação", response = Response.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "App ok"),
			@ApiResponse(code = 500, message = "Erro interno")
	})
	Response probe();
	
	
	@POST
	@Path("/query")
	@ApiOperation(value = "Faz uma query Sparql", response = Response.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "App ok"),
			@ApiResponse(code = 500, message = "Erro interno")
	})
	Response query(DatastoreQuery datastoreQuery);	
	
}
