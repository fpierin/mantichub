package com.mantichub.agent.eventos.usp.agent;

import org.apache.jena.ext.com.google.common.util.concurrent.ListeningExecutorService;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.mantic.datastore.client.api.DatastoreApi;

import com.google.inject.Inject;
import com.mantichub.agent.core.http.HttpAgent;
import com.mantichub.agent.core.infra.Agent;
import com.mantichub.agent.core.infra.DefaultAgent;
import com.mantichub.agent.core.infra.ResourceCreator;
import com.mantichub.agent.eventos.usp.config.Configuration;

public class EventoUspAgent extends DefaultAgent implements Agent {
	
	public static final String PORTAL_URL = "http://www.eventos.usp.br/";
	public static final String EVENT_URL_PATTERN = "href=\"(http://www.eventos.usp.br/\\?events=(.*?))\"";
	
	@Inject
	public EventoUspAgent(final HttpAgent httpAgent, final DatastoreApi datastoreApi, final ListeningExecutorService service) {
		super(httpAgent, datastoreApi, service);
	}
	
	@Override
	public Model retrieve(final int ammount) throws Exception {
		return retrieveFromUrl(ammount, PORTAL_URL, EVENT_URL_PATTERN);
	}

	@Override
	public Resource fromHtml(final Model model, final String url, final String html) {
		try {
			return ResourceCreator.build(model, new EventoUspEventAdapter(url, html));
		} catch (final Exception e) {
			System.out.println("Falha ao recuperar dados do dominio: " + url);
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	protected boolean useParallelCalls() {
		return Configuration.USE_PARALLELL_CALLS;
	}

}
