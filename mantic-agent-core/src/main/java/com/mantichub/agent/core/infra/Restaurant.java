package com.mantichub.agent.core.infra;

import java.util.List;

import org.apache.jena.rdf.model.Resource;

public interface Restaurant {
	
	String getCuisine();

	String getDescription();
	
	Double getLatitude();

	Double getLongitude();

	List<String> getOpeningHours();
	
	String getPriceRange();
	
	String getStreetAddress();

	String getTitle();

	Resource getType();

	String getUrl();

	String getTelephone();
	
}
