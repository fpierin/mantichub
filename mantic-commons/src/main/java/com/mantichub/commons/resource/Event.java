package com.mantichub.commons.resource;

import java.util.Date;

public interface Event {

	String getDescription();

	Date getEndDate();

	Date getEndTime();

	String getImage();
	
	Double getLatitude();

	Double getLongitude();

	String getOverview();

	Date getStartDate();

	Date getStartTime();

	String getStreetAddress();

	String getTitle();

	Resources getType();

	Double getPrice();

	String getUrl();

}
