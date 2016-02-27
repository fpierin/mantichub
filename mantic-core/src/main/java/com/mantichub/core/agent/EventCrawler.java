package com.mantichub.core.agent;

import org.apache.jena.rdf.model.Resource;

public interface EventCrawler {

	String getStreetAddress();

	String getTitle();

	Resource getType();

}
