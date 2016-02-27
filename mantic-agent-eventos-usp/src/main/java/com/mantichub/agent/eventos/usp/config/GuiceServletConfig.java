package com.mantichub.agent.eventos.usp.config;

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
import com.mantichub.core.http.RestfullHttpClient;
import com.mantichub.core.http.RestfullHttpClientImpl;

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
				bind(RestfullHttpClient.class).to(RestfullHttpClientImpl.class).asEagerSingleton();
				bind(EventosUspHttpClient.class).to(EventosUspHttpClientImpl.class).asEagerSingleton();
				bind(Agent.class).to(UspEventAgent.class).asEagerSingleton();
			}
		};
	}

}
