package com.mantichub.agent.guiafolha.config;

import org.apache.http.client.HttpClient;
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
import com.mantichub.agent.guiafolha.agent.GuiaDaFolhaAgent;
import com.mantichub.agent.guiafolha.resource.GuiaDaSemanaResource;
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
				serve("*").with(GuiaDaSemanaResource.class);
			}
		};
	}
	
	private Module bindingsModule() {
		return new AbstractModule() {
			@Override
			protected void configure() {
				final HttpClient httpClient = HttpClientFactory.get(2, 2, 3);
				final SerializationService serializationService = new JsonSerializationServiceImpl();
				final DatastoreApi datastoreApi = new DatastoreApiImpl(httpClient, serializationService);
				
				bind(HttpClient.class).toInstance(httpClient);
				bind(HttpAgent.class).to(HttpAgentImpl.class).asEagerSingleton();
				bind(DatastoreApi.class).toInstance(datastoreApi);
				bind(SerializationService.class).toInstance(serializationService);
				bind(Agent.class).to(GuiaDaFolhaAgent.class).asEagerSingleton();
			}
		};
	}
	
}
