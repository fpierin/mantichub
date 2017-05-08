package com.mantichub.commons.resource;

import java.util.List;

public interface CivicStructure {
	
	String getDescription();
	
	String getImage();
	
	Double getLatitude();

	Double getLongitude();
	
	List<String> getOpeningHours();
	
	String getTitle();
	
	Resources getType();

	String getUrl();	
	
	String getStreetAddress();
	
	

}
