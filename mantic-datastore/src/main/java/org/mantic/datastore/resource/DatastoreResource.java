package org.mantic.datastore.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.mantichub.commons.domain.DatastoreQuery;
import com.mantichub.commons.domain.DatastoreTriple;

@Produces({ "application/json; charset=utf-8" })
@Consumes({ "application/json; charset=utf-8" })
@Path("/triplestore")
public interface DatastoreResource {
	
	@POST
	@Path("/")
	Response create(List<DatastoreTriple> triples);
	
	@POST
	@Path("/query")
	Response query(DatastoreQuery datastoreQuery);
	

}
