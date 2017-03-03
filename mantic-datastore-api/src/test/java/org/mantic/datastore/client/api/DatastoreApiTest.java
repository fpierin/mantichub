package org.mantic.datastore.client.api;

import org.apache.http.client.HttpClient;

import com.mantichub.core.http.HttpClientFactory;
import com.mantichub.core.serialization.JsonSerializationServiceImpl;
import com.mantichub.core.serialization.SerializationService;

public class DatastoreApiTest {

	public static void main(final String[] args) {
		queryGeral();
		queryInferencia();
	}

	public static void queryInferencia() {
		doQuery(
			"PREFIX rdfs:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
			"PREFIX mantichub:<http://www.wemantic.com/events#>\n" +
			"PREFIX schema:<http://schema.org/>\n" +
			"SELECT * WHERE { ?s rdfs:type schema:ExhibitionEvent }"
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
