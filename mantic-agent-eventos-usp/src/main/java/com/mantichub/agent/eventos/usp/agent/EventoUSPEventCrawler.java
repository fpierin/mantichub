package com.mantichub.agent.eventos.usp.agent;

import static com.mantichub.core.util.HTMLUtils.valueByPattern;

import org.apache.jena.rdf.model.Resource;

import com.mantichub.agent.eventos.usp.config.Configuration;
import com.mantichub.core.agent.EventCrawler;
import com.mantichub.core.vocabulary.SCHEMA;

public class EventoUSPEventCrawler implements EventCrawler {

	private final String html;

	public EventoUSPEventCrawler(final String html) {
		this.html = html;
	}

	public String getStreetAddress() {
		return valueByPattern(html, Configuration.STREET_ADDRESS_PATTERN);
	}

	public String getTitle() {
		return valueByPattern(html, Configuration.TITLE_PATTERN);
	}

	public Resource getType() {
		return SCHEMA.ExhibitionEvent;
	}

}
