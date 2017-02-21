package com.mantichub.agent.eventos.usp.integrationtest.agent;

import org.apache.http.client.HttpClient;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFactory;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.query.ResultSetRewindable;
import org.apache.jena.rdf.model.Model;
import org.mantic.datastore.client.api.DatastoreApiImpl;
import org.mantic.datastore.repository.DatastoreRepository;
import org.mantic.datastore.repository.DatastoreRepositoryImpl;

import com.mantichub.agent.eventos.usp.agent.UspEventAgent;
import com.mantichub.agent.eventos.usp.http.EventosUspHttpClient;
import com.mantichub.agent.eventos.usp.http.EventosUspHttpClientImpl;
import com.mantichub.core.http.HttpClientFactory;

public class EventoUSPEventCrawlerExemploITTest {

	public static void main(final String[] args) throws Exception {
		// persiste();
		recupera();
	}

	
	public static void recupera() {
		final DatastoreRepository tdbRepository = new DatastoreRepositoryImpl("/opt/apps/mantichub/datastore", "teste");
		final ResultSet query = tdbRepository.query("SELECT * { ?s ?p ?o }");
		final ResultSetRewindable results = ResultSetFactory.makeRewindable(query);
		ResultSetFormatter.out(results);
		results.reset();

	}

	public static void persiste() throws Exception {
		final HttpClient httpClient = HttpClientFactory.get(10, 5, 3);
		final EventosUspHttpClient eventosUspHttpClient = new EventosUspHttpClientImpl(httpClient, null);
		final UspEventAgent uspEventAgent = new UspEventAgent(eventosUspHttpClient, new DatastoreApiImpl(httpClient, null));
		final Model model = uspEventAgent.retrieve(1);
		info(model);
		System.out.println("Iniciando a criação do modelo");
		final DatastoreRepository tdbRepository = new DatastoreRepositoryImpl("/opt/apps/mantichub/datastore", "teste");
		tdbRepository.create(model);
		System.out.println("Criação de modelo finalizada");
	}

	public static void info(final Model model) {
		print("model size", model.size());
		print("graph size", model.getGraph().size());
		print("namespaces", model.listNameSpaces().toList());
		print("objects", model.listObjects().toList());
		print("statements", model.listStatements().toList());
		print("reifiedStatements", model.listReifiedStatements().toList());
		print("subjects", model.listSubjects().toList());
	}

	public static void print(final String param, final Object object) {
		System.out.println(param + ": " + object);
	}

}
