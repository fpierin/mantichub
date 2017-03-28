package org.mantic.datastore.client.api;

import org.apache.http.client.HttpClient;

import com.mantichub.core.http.HttpClientFactory;
import com.mantichub.core.serialization.JsonSerializationServiceImpl;
import com.mantichub.core.serialization.SerializationService;

public class DatastoreApiTest2 {

	public static void main(final String[] args) {
		query();
	}

	public static void query() {
		doQuery( 
			"PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
			"PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>\n" +
			"PREFIX mantichub:<http://www.wemantic.com/events#>\n" +
			"PREFIX schema:<http://schema.org/>\n" +
			"SELECT * WHERE { mantichub:OqueaFisicaeosfisicospodemfazerparaasuasaude a ?o }"
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
