package com.mantichub.agent.core.infra;

import java.util.Date;

import org.apache.jena.rdf.model.Resource;

public interface EventResource {

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
