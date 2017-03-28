package org.mantic.datastore.client.api;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.HttpClient;

import com.mantichub.commons.domain.DatastoreTriple;
import com.mantichub.commons.domain.TripleNode;
import com.mantichub.core.http.HttpClientFactory;
import com.mantichub.core.serialization.JsonSerializationServiceImpl;
import com.mantichub.core.serialization.SerializationService;

public class DatastoreApiTest {

	public static void main(final String[] args) {
//		queryGeral();
		queryInferencia();
//		insert();
	}

	public static void insert() {
		// TODO Auto-generated method stub
		final SerializationService serializationService = new JsonSerializationServiceImpl();
		final HttpClient httpClient = HttpClientFactory.get(1, 1, 3);
		final DatastoreApi datastoreApi = new DatastoreApiImpl(httpClient, serializationService);
		final List<DatastoreTriple> triples = new ArrayList<>();
		DatastoreTriple t = new DatastoreTriple();
		t.setSubject(new TripleNode("http://schema.org/", "ExhibitionEvent"));
		t.setPredicate(new TripleNode("http://www.w3.org/2000/01/rdf-schema#", "subClassOf"));
		t.setObject(new TripleNode("http://schema.org/", "Event"));
		triples.add(t);
		datastoreApi.create(triples);
	}

	public static void queryInferencia() {
		doQuery(
			"PREFIX rdfs:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
			"PREFIX mantichub:<http://www.wemantic.com/events#>\n" +
			"PREFIX schema:<http://schema.org/>\n" +
			"SELECT * WHERE { ?s rdfs:subClassOf schema:Event }"
		);
	}

	public static void queryGeral() {
		doQuery("SELECT * { ?s ?p ?o }");
	}

	public static void doQuery(String query) {
		final SerializationService serializationService = new JsonSerializationServiceImpl();
		final HttpClient httpClient = HttpClientFactory.get(1, 1, 3);
		final DatastoreApi datastoreApi = new DatastoreApiImpl(httpClient, serializationService);
		datastoreApi.query(query);
	}
	
	

}
