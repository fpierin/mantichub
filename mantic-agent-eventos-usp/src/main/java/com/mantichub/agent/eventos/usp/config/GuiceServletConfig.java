package com.mantichub.agent.eventos.usp.config;

import static com.mantichub.agent.eventos.usp.config.Configuration.MAX_CONNECTION_PER_ROUTE;
import static com.mantichub.agent.eventos.usp.config.Configuration.MAX_CONNECTION_TOTAL;
import static com.mantichub.agent.eventos.usp.config.Configuration.MAX_RETRY_ATTEMPTS;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.client.HttpClient;
import org.apache.jena.ext.com.google.common.util.concurrent.ListeningExecutorService;
import org.apache.jena.ext.com.google.common.util.concurrent.MoreExecutors;
import org.mantic.datastore.client.api.DatastoreApi;
import org.mantic.datastore.client.api.DatastoreApiImpl;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.mantichub.agent.core.http.HttpAgent;
import com.mantichub.agent.core.http.HttpAgentImpl;
import com.mantichub.agent.core.infra.Agent;
import com.mantichub.agent.eventos.usp.agent.EventoUspAgent;
import com.mantichub.agent.eventos.usp.resource.EventosUSPResource;
import com.mantichub.core.http.HttpClientFactory;
import com.mantichub.core.serialization.JsonSerializationServiceImpl;
import com.mantichub.core.serialization.SerializationService;

public class GuiceServletConfig extends GuiceServletContextListener {

	@Override
	protected Injector getInjector() {
		return Guice.createInjector(bindingsModule(), servletModule());
	}

	private ServletModule servletModule() {
		return new ServletModule() {
			@Override
			protected void configureServlets() {
				serve("*").with(EventosUSPResource.class);
			}
		};
	}

	private Module bindingsModule() {
		return new AbstractModule() {
			@Override
			protected void configure() {
				final HttpClient httpClient = HttpClientFactory.get(MAX_CONNECTION_TOTAL, MAX_CONNECTION_PER_ROUTE, MAX_RETRY_ATTEMPTS);
				final SerializationService serializationService = new JsonSerializationServiceImpl();
				final DatastoreApi datastoreApi = new DatastoreApiImpl(httpClient, serializationService);

				final ExecutorService executorService = Executors.newFixedThreadPool(Configuration.THREADS);
				final ListeningExecutorService service = MoreExecutors.listeningDecorator(executorService);
				
				bind(ListeningExecutorService.class).toInstance(service);
				bind(HttpClient.class).toInstance(httpClient);
				bind(HttpAgent.class).to(HttpAgentImpl.class).asEagerSingleton();
				bind(DatastoreApi.class).toInstance(datastoreApi);
				bind(SerializationService.class).toInstance(serializationService);
				bind(Agent.class).to(EventoUspAgent.class).asEagerSingleton();
			}
		};
	}

}
