package com.mantichub.agent.core.builder;

import static com.mantichub.core.util.DateUtils.getString;
import static com.mantichub.core.util.StringUtils.isNotBlank;
import static com.mantichub.core.util.StringUtils.md5;
import static com.mantichub.core.vocabulary.SCHEMA.dateFormat;
import static com.mantichub.core.vocabulary.SCHEMA.datetimeFormat;

import java.util.Date;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.RDF;

import com.mantichub.core.vocabulary.SCHEMA;

public class EventResourceBuilder extends ResourceBuilder {
	
	private String addressRegion;
	private String addressLocality;
	private String endDate;
	private String endTime;
	private Double latitude;
	private Double longitude;
	private String overview;
	private Double price;
	private String serviceUrl;
	private String startDate;
	private String startTime;
	private String streetAddress;
	private Resource type;
	private String title;
	
	public EventResourceBuilder(final Model model, final String projectNS) {
		super(model, projectNS);
	}
	
	public Resource create() throws Exception {
		if (getResource() == null) {
			final String resourceName = isNotBlank(title) ? title : md5(serviceUrl);
			resource(resourceName);
		}
		addProperty(RDF.type, type);
		addProperty(SCHEMA.addressRegion, addressRegion);
		addProperty(SCHEMA.addressLocality, addressLocality);
		addProperty(SCHEMA.endDate, endDate);
		addProperty(SCHEMA.endTime, endTime);
		addProperty(SCHEMA.latitude, latitude);
		addProperty(SCHEMA.longitude, longitude);
		addProperty(SCHEMA.overview, overview);
		addProperty(SCHEMA.price, price);
		addProperty(SCHEMA.serviceURL, serviceUrl);
		addProperty(SCHEMA.startDate, startDate);
		addProperty(SCHEMA.startTime, startTime);
		addProperty(SCHEMA.streetAddress, streetAddress);
		addProperty(SCHEMA.title, title);
		return getResource();
	}
	
	public EventResourceBuilder addressRegion(final String addressRegion) {
		this.addressRegion = addressRegion;
		return this;
	}
	
	public EventResourceBuilder addressLocality(final String addressLocality) {
		this.addressLocality = addressLocality;
		return this;
	}
	
	public EventResourceBuilder endDate(final Date endDate) {
		this.endDate = getString(endDate, dateFormat);
		return this;
	}
	
	public EventResourceBuilder endTime(final Date endTime) {
		this.endTime = getString(endTime, datetimeFormat);
		return this;
	}
	
	public EventResourceBuilder latitude(final Double latitude) {
		this.latitude = latitude;
		return this;
	}
	
	public EventResourceBuilder longitude(final Double longitude) {
		this.longitude = longitude;
		return this;
	}
	
	public EventResourceBuilder overview(final String overview) {
		this.overview = overview;
		return this;
	}
	
	public EventResourceBuilder price(final Double price) {
		this.price = price;
		return this;
	}
	
	public EventResourceBuilder serviceUrl(final String serviceUrl) {
		this.serviceUrl = serviceUrl;
		return this;
	}
	
	public EventResourceBuilder startDate(final Date startDate) {
		this.startDate = getString(startDate, dateFormat);
		return this;
	}
	
	public EventResourceBuilder startTime(final Date startTime) {
		this.startTime = getString(startTime, datetimeFormat);
		return this;
	}
	
	public EventResourceBuilder streetAddress(final String streetAddress) {
		this.streetAddress = streetAddress;
		return this;
	}
	
	public EventResourceBuilder type(final Resource type) {
		this.type = type;
		return this;
	}	
	
	public EventResourceBuilder title(final String title) {
		this.title = title;
		return this;
	}
	
}
