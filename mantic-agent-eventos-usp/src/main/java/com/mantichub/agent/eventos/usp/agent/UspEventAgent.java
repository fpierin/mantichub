package com.mantichub.agent.eventos.usp.agent;

import static com.mantichub.agent.eventos.usp.config.Configuration.EVENT_URL_PATTERN;
import static com.mantichub.agent.eventos.usp.config.Configuration.PORTAL_URL;
import static com.mantichub.core.util.HTMLUtils.setByPattern;
import static com.mantichub.core.util.ModelUtils.getRDFXMLFastWriter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;

import com.google.inject.Inject;
import com.mantichub.agent.eventos.usp.http.EventosUspHttpClient;
import com.mantichub.core.agent.Agent;
import com.mantichub.core.agent.EventCrawler;
import com.mantichub.core.builder.EventBuilder;

public class UspEventAgent implements Agent {

	private final EventosUspHttpClient eventosUspHttpClient;

	private final static String projectNS = "http://www.wemantic.com/events#";
	
	@Inject
	public UspEventAgent(final EventosUspHttpClient eventosUspHttpClient) {
		this.eventosUspHttpClient = eventosUspHttpClient;
	}

	public Model retrieve() {
		final Model model = getRDFXMLFastWriter();
		final String htmlFromURL = eventosUspHttpClient.htmlFromURL(PORTAL_URL);
		final Set<String> urls = setByPattern(htmlFromURL, EVENT_URL_PATTERN);
		final List<Resource> results = new ArrayList<Resource>();
		final Iterator<String> iterator = urls.iterator();
		int i = 0;
		while (i < 2 && iterator.hasNext()) {
			i++;
			results.add(resourceFromURI(iterator.next(), model));
		}
		return model;
	}

	private Resource resourceFromURI(final String url, final Model model) {
		final String html = eventosUspHttpClient.unescapeHtmlFromURL(url);
		final EventCrawler event = new EventoUSPEventCrawler(html);
		try {
			return new EventBuilder(model, projectNS)
					.serviceUrl(url)
					.streetAddress(event.getStreetAddress())
					.type(event.getType())
					.title(event.getTitle())
					.create();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
