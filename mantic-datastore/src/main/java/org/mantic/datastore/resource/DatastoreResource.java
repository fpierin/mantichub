package org.mantic.datastore.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mantichub.commons.domain.DatastoreQuery;
import com.mantichub.commons.domain.DatastoreTriple;
import com.mantichub.commons.domain.QueryResult;
import com.mantichub.commons.resource.ResourceObject;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Query")
@Produces({ "application/json;charset=UTF-8" })
@Consumes({ MediaType.APPLICATION_JSON })
@Path("/")
public interface DatastoreResource {
	
	@GET
	@Path("/probe")
	@ApiOperation(value = "Probe da aplicação", response = Response.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "App ok"),
			@ApiResponse(code = 404, message = "Inexistente"),
			@ApiResponse(code = 500, message = "Erro interno")
	})
	Response probe();
	
	@GET
	@Path("/")
	@ApiOperation(value = "Faz uma consulta por recurso", response = Response.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "App ok", response = QueryResult.class),
			@ApiResponse(code = 500, message = "Erro interno")
	})
	Response query(@QueryParam("query") String query, @QueryParam("output") String output);
	
	@GET
	@POST
	@Path("/resources")
	@ApiOperation(value = "Faz uma consulta por recurso", response = Response.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "App ok", response = QueryResult.class, responseContainer = "Set"),
			@ApiResponse(code = 500, message = "Erro interno")
	})
	Response resources(@QueryParam("query") String query, @QueryParam("radius") Double radius);
	
	@GET
	@Path("/infer")
	@ApiOperation(value = "Realiza inferências na base de dados", response = Response.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Inferências realizadas"),
			@ApiResponse(code = 500, message = "Erro interno")
	})
	Response infer(@QueryParam("url") String url);
	
	@POST
	@Path("/")
	@ApiOperation(value = "Cria lista de recurso", response = Response.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Recurso criado com sucesso"),
			@ApiResponse(code = 500, message = "Falha ao criar recurso")
	})
	Response create(List<DatastoreTriple> triples);
	
	@POST
	@Path("/query")
	@ApiOperation(value = "Faz uma consulta por recurso", response = Response.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "App ok", response = QueryResult.class, responseContainer = "Set"),
			@ApiResponse(code = 500, message = "Erro interno")
	})
	Response query(ResourceObject resource, @QueryParam("radius") Double radius, @QueryParam("limit") Integer limit);
	
	@POST
	@Path("/dquery")
	@ApiOperation(value = "Faz uma consulta por recurso", response = Response.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "App ok", response = QueryResult.class, responseContainer = "Set"),
			@ApiResponse(code = 500, message = "Erro interno")
	})
	Response datastoreQuery(DatastoreQuery query);	
	
	@OPTIONS
	@Path("{path : .*}")
	Response options();

}
