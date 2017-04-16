package com.mantichub.agent.core.builder;

import static com.mantichub.core.util.StringUtils.isNotBlank;
import static com.mantichub.core.util.StringUtils.md5;

import java.util.List;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.RDF;

import com.mantichub.core.vocabulary.SCHEMA;

public class RestaurantResourceBuilder extends ResourceBuilder {

	private String addressRegion;
	private String addressLocality;
	private String description;
	private Double latitude;
	private Double longitude;
	private List<String> openingHours;
	private String priceRange;
	private String serviceUrl;
	private String streetAddress;
	private String telephone;
	private String title;
	private Resource type;

	public RestaurantResourceBuilder(final Model model, final String projectNS) {
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
		addProperty(SCHEMA.description, description);
		addProperty(SCHEMA.latitude, latitude);
		addProperty(SCHEMA.longitude, longitude);
		if ((openingHours != null) && !openingHours.isEmpty()) {
			openingHours.forEach(openingHour -> {
				addProperty(SCHEMA.openingHours, openingHour);
			});
		}
		addProperty(SCHEMA.priceRange, priceRange);
		addProperty(SCHEMA.serviceURL, serviceUrl);
		addProperty(SCHEMA.streetAddress, streetAddress);
		addProperty(SCHEMA.telephone, telephone);
		addProperty(SCHEMA.title, title);
		return getResource();
	}

	public RestaurantResourceBuilder addressRegion(final String addressRegion) {
		this.addressRegion = addressRegion;
		return this;
	}
	
	public RestaurantResourceBuilder addressLocality(final String addressLocality) {
		this.addressLocality = addressLocality;
		return this;
	}
	
	public RestaurantResourceBuilder description(final String description) {
		this.description = description;
		return this;
	}

	public RestaurantResourceBuilder latitude(final Double latitude) {
		this.latitude = latitude;
		return this;
	}

	public RestaurantResourceBuilder longitude(final Double longitude) {
		this.longitude = longitude;
		return this;
	}
	
	public RestaurantResourceBuilder openingHours(final List<String> openingHours) {
		this.openingHours = openingHours;
		return this;
	}

	public RestaurantResourceBuilder priceRange(final String priceRange) {
		this.priceRange = priceRange;
		return this;
	}

	public RestaurantResourceBuilder serviceUrl(final String serviceUrl) {
		this.serviceUrl = serviceUrl;
		return this;
	}
	
	public RestaurantResourceBuilder streetAddress(final String streetAddress) {
		this.streetAddress = streetAddress;
		return this;
	}
	
	public RestaurantResourceBuilder telephone(final String telephone) {
		this.telephone = telephone;
		return this;
	}
	
	public RestaurantResourceBuilder title(final String title) {
		this.title = title;
		return this;
	}

	public RestaurantResourceBuilder type(Resource type) {
		this.type = type;
		return this;
	}


}
