package com.mantichub.agent.core.builder;

import static com.mantichub.core.util.DateUtils.getString;
import static com.mantichub.core.util.StringUtils.isNotBlank;
import static com.mantichub.core.util.StringUtils.md5;
import static com.mantichub.core.util.StringUtils.normalize;
import static com.mantichub.core.vocabulary.SCHEMA.dateFormat;
import static com.mantichub.core.vocabulary.SCHEMA.datetimeFormat;

import java.util.Date;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.shared.Lock;
import org.apache.jena.vocabulary.RDF;

import com.mantichub.core.vocabulary.SCHEMA;

public class EventBuilder {

	private final Model model;
	private final String projectNS;
	private String addressRegion;
	private Resource type;
	private Resource resource;
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
	private String title;

	public EventBuilder(final Model model, final String projectNS) {
		this.model = model;
		this.projectNS = projectNS;
	}

	private void addProperty(final Property property, final Object value) {
		if (value == null) {
			return;
		}
		if (value instanceof Resource) {
			resource.addProperty(property, (Resource) value);
		}
		if ((value instanceof String) && isNotBlank((String) value)) {
			resource.addProperty(property, (String) value);
		}
		if (value instanceof Double) {
			resource.addProperty(property, String.valueOf(value));
		}
	}

	public Resource create() throws Exception {
		if (resource == null) {
			resource(isNotBlank(title) ? title.replace(" ", "") : md5(serviceUrl));
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
		return resource;
	}

	public EventBuilder projectNS(final String projectNS) {
		return this;
	}

	public EventBuilder resource(final String resourceName) {
		try {
			model.enterCriticalSection(Lock.WRITE);
			resource = model.createResource(projectNS + normalize(resourceName));
		} finally {
			model.leaveCriticalSection();

		}
		return this;
	}

	public EventBuilder type(final Resource type) {
		this.type = type;
		return this;
	}

	public EventBuilder addressRegion(final String addressRegion) {
		this.addressRegion = addressRegion;
		return this;
	}

	public EventBuilder addressLocality(final String addressLocality) {
		this.addressLocality = addressLocality;
		return this;
	}

	public EventBuilder endDate(final Date endDate) {
		this.endDate = getString(endDate, dateFormat);
		return this;
	}

	public EventBuilder endTime(final Date endTime) {
		this.endTime = getString(endTime, datetimeFormat);
		return this;
	}

	public EventBuilder latitude(final Double latitude) {
		this.latitude = latitude;
		return this;
	}

	public EventBuilder longitude(final Double longitude) {
		this.longitude = longitude;
		return this;
	}

	public EventBuilder overview(final String overview) {
		this.overview = overview;
		return this;
	}

	public EventBuilder price(final Double price) {
		this.price = price;
		return this;
	}

	public EventBuilder serviceUrl(final String serviceUrl) {
		this.serviceUrl = serviceUrl;
		return this;
	}

	public EventBuilder startDate(final Date startDate) {
		this.startDate = getString(startDate, dateFormat);
		return this;
	}

	public EventBuilder startTime(final Date startTime) {
		this.startTime = getString(startTime, datetimeFormat);
		return this;
	}

	public EventBuilder streetAddress(final String streetAddress) {
		this.streetAddress = streetAddress;
		return this;
	}

	public EventBuilder title(final String title) {
		this.title = title;
		return this;
	}

}
