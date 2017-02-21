package com.mantichub.agent.eventos.usp.config;

import org.apache.http.client.HttpClient;
import org.mantic.datastore.client.api.DatastoreApi;
import org.mantic.datastore.client.api.DatastoreApiImpl;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.mantichub.agent.eventos.usp.agent.UspEventAgent;
import com.mantichub.agent.eventos.usp.http.EventosUspHttpClient;
import com.mantichub.agent.eventos.usp.http.EventosUspHttpClientImpl;
import com.mantichub.agent.eventos.usp.resource.EventosUSPResource;
import com.mantichub.core.agent.Agent;
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
				final HttpClient httpClient = HttpClientFactory.get(20, 20, 3);
				final SerializationService serializationService = new JsonSerializationServiceImpl();
				final DatastoreApi datastoreApi = new DatastoreApiImpl(httpClient, serializationService);

				bind(HttpClient.class).toInstance(httpClient);
				bind(EventosUspHttpClient.class).to(EventosUspHttpClientImpl.class).asEagerSingleton();
				bind(DatastoreApi.class).toInstance(datastoreApi);
				bind(SerializationService.class).toInstance(serializationService);
				bind(Agent.class).to(UspEventAgent.class).asEagerSingleton();
			}
		};
	}

}
