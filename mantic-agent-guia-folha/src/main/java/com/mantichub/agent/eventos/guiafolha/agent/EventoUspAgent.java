package com.mantichub.agent.eventos.guiafolha.agent;

import org.apache.jena.rdf.model.Model;
import org.mantic.datastore.client.api.DatastoreApi;

import com.google.inject.Inject;
import com.mantichub.agent.core.http.HttpAgent;
import com.mantichub.agent.core.infra.Agent;
import com.mantichub.agent.core.infra.DefaultAgent;
import com.mantichub.agent.core.infra.EventResource;

public class EventoUspAgent extends DefaultAgent implements Agent {
	
	public static final String PORTAL_URL = "http://www.eventos.usp.br/";
	public static final String EVENT_URL_PATTERN = "href=\"(http://www.eventos.usp.br/\\?events=(.*?))\"";
	
	@Inject
	public EventoUspAgent(final HttpAgent httpAgent, final DatastoreApi datastoreApi) {
		super(httpAgent, datastoreApi);
	}
	
	@Override
	public Model retrieve(final int ammount) throws Exception {
		return retrieveFromUrl(ammount, PORTAL_URL, EVENT_URL_PATTERN);
	}

	@Override
	public EventResource getAdapter(final String html) {
		return new EventoUspEventAdapter(html);
	}

}
