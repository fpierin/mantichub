package com.mantichub.commons.resource;

import java.util.Date;

public interface Event {
	
	Date getEndDate();

	Date getEndTime();

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
