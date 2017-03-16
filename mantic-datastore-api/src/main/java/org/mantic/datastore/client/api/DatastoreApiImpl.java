package org.mantic.datastore.client.api;

import static com.mantichub.core.util.ListUtils.isNotEmpty;
import static com.mantichub.core.util.StringUtils.isNotBlank;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.http.client.HttpClient;
import org.apache.jena.graph.Node;
import org.apache.jena.graph.Triple;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;

import com.mantichub.commons.builder.DatastoreTripleBuilder;
import com.mantichub.commons.domain.DatastoreQuery;
import com.mantichub.commons.domain.DatastoreTriple;
import com.mantichub.core.http.RestfulSupport;
import com.mantichub.core.serialization.SerializationService;

public class DatastoreApiImpl extends RestfulSupport implements DatastoreApi {
	
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
			final DatastoreTriple datastoreTriple = buildTripleFromStatement(statement);
			triples.add(datastoreTriple);
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

	private DatastoreTriple buildTripleFromStatement(final Statement statement) {
		final Triple asTriple = statement.asTriple();
		final Node subject = asTriple.getSubject();
		final Node predicate = asTriple.getPredicate();
		final Node object = asTriple.getObject();
		final DatastoreTriple datastoreTriple = new DatastoreTripleBuilder()
				.withSubject(namespaceFrom(subject), valueFrom(subject))
				.withPredicate(namespaceFrom(predicate), valueFrom(predicate))
				.withObject(namespaceFrom(object), valueFrom(object))
				.create();
		return datastoreTriple;
	}
	
	private String valueFrom(final Node node) {
		return node.isLiteral() ? clearLiteral(node) : clearUrl(node);
	}

	private String clearUrl(final Node node) {
		return node.getURI().replaceAll(namespaceFrom(node), "");
	}

	private String clearLiteral(final Node node) {
		return node.getLiteral().toString();
	}
	
	private String namespaceFrom(final Node node) {
		return node.isLiteral()? null : node.getNameSpace();
	}
	
}

