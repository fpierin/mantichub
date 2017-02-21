package org.mantic.datastore.client.api;

import static com.mantichub.core.util.ListUtils.isNotEmpty;
import static com.mantichub.core.util.StringUtils.isNotBlank;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.http.client.HttpClient;
import org.apache.jena.graph.Triple;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.mantichub.commons.domain.DatastoreQuery;
import org.mantichub.commons.domain.DatastoreTriple;

import com.mantichub.core.http.RestSupport;
import com.mantichub.core.serialization.SerializationService;

public class DatastoreApiImpl extends RestSupport implements DatastoreApi {

	String url = "http://localhost:8080/api/triplestore";

	@Inject
	public DatastoreApiImpl(final HttpClient httpClient, final SerializationService serializationService) {
		super(httpClient, serializationService);
	}
	
	@Override
	public void create(final Resource resource) {
		final StmtIterator listProperties = resource.listProperties();
		final List<DatastoreTriple> triples = new ArrayList<>();
		while (listProperties.hasNext()) {
			final Statement statement = listProperties.next();
			final Triple asTriple = statement.asTriple();
			final String object = asTriple.getObject().toString();
			final String predicate = asTriple.getPredicate().toString();
			final String subject = asTriple.getSubject().toString();
			triples.add(new DatastoreTriple(object, predicate, subject));
		}
		if (isNotEmpty(triples)) {
			System.out.println(post(url, triples));
		}
	}
	
	@Override
	public void query(final String queryStr) {
		if (isNotBlank(queryStr)) {
			System.out.println(post(url + "/query", new DatastoreQuery(queryStr)));
		}
	}

}
