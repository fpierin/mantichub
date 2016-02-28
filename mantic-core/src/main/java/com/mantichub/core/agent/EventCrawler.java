package com.mantichub.core.agent;

import java.util.Date;

import org.apache.jena.rdf.model.Resource;

public interface EventCrawler {

	Date getEndDate();

	Date getEndTime();

	Double getLatitude();

	Double getLongitude();

	String getOverview();

	Date getStartDate();

	Date getStartTime();

	String getStreetAddress();

	String getTitle();

	Resource getType();

	Double getPrice();

}
