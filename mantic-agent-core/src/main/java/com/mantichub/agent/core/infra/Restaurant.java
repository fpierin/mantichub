package com.mantichub.agent.core.infra;

import org.apache.jena.rdf.model.Resource;

public interface Restaurant {
	
	String getCuisine();

	String getDescription();
	
	Double getLatitude();

	Double getLongitude();

	String getStreetAddress();

	String getTitle();

	Resource getType();

	String getUrl();

	String getTelephone();
	
}
