package com.mantichub.commons.resource;

import java.util.List;

public interface Restaurant {
	
	String getCuisine();

	String getDescription();
	
	Double getLatitude();

	Double getLongitude();

	List<String> getOpeningHours();
	
	String getPriceRange();
	
	String getStreetAddress();

	String getTitle();

	Resources getType();

	String getUrl();

	String getTelephone();
	
}
