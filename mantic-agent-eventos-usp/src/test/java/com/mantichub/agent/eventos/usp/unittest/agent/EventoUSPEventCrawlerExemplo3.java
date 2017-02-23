package com.mantichub.agent.eventos.usp.unittest.agent;

import static com.mantichub.agent.eventos.usp.infra.TestUtils.fromFile;
import static com.mantichub.core.util.ModelUtils.getRDFXMLFastWriter;

import org.apache.http.client.HttpClient;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.junit.Assert;
import org.junit.Test;
import org.mantic.datastore.client.api.DatastoreApi;
import org.mantic.datastore.client.api.DatastoreApiImpl;

import com.mantichub.agent.eventos.usp.agent.UspEventAgent;
import com.mantichub.core.agent.Agent;
import com.mantichub.core.http.HttpClientFactory;
import com.mantichub.core.serialization.JsonSerializationServiceImpl;
import com.mantichub.core.serialization.SerializationService;

public class EventoUSPEventCrawlerExemplo3 {
	
	private final static String arquivo = "src/test/resources/exemplo2.html";
	
	@Test
	public void persiteTriplas() throws Exception {
		final HttpClient httpClient = HttpClientFactory.get(10, 10, 3);
		final SerializationService serializationService = new JsonSerializationServiceImpl();
		final DatastoreApi datastoreApi = new DatastoreApiImpl(httpClient, serializationService);
		final Agent agent = new UspEventAgent(null, datastoreApi);
		final Model model = getRDFXMLFastWriter();
		final Resource resource = agent.resourceFromHtml("http://teste.com", model, fromFile(arquivo));
		datastoreApi.create(resource);
		Assert.fail();
	}
	
	@Test
	public void recuperaTriplas() throws Exception {
//		final String query = "SELECT * { ?s ?p ?o }";
//		PREFIX s1:http://mantichub.com
//			PREFIX p1:http://schema.org/
//			SELECT * WHERE { s1:vlaaaaaaav p1:startDate ?o }
		final String query = 
//				"PREFIX schema:<http://schema.org/> \n" +
//				"PREFIX wemantic:<http://www.wemantic.com/events#> \n" +
//				"PREFIX rdf-syntax:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n" +
//				"SELECT * WHERE { wemantic:BibliotecaBrasilianaapresentaexposicaoOMundoaoRedor ?p ?o }";
		
		"PREFIX s1:<http://www.wemantic.com/events#>\n" +
		"PREFIX p1:<http://schema.org/>\n" +
		"SELECT * WHERE { s1:BibliotecaBrasilianaapresentaexposicaoOMundoaoRedor p1:startDate ?o }";
		doQuery(query);
		Assert.fail();
	}

	private void doQuery(String query) {
		final HttpClient httpClient = HttpClientFactory.get(20, 20, 3);
		final SerializationService serializationService = new JsonSerializationServiceImpl();
		final DatastoreApi datastoreApi = new DatastoreApiImpl(httpClient, serializationService);
		datastoreApi.query(query);
	}
	
}
