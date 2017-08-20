package com.mantichub.agent.core.builder;

import static com.mantichub.core.util.StringUtils.md5;

import java.util.List;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.RDF;

import com.mantichub.core.vocabulary.SCHEMA;

public class CivicStructureBuilder extends ResourceBuilder  {

	private String image;
	private String title;
	private Resource type;
	private String streetAddress;
	private String serviceUrl;
	private Double longitude;
	private Double latitude;
	private String description;
	private List<String> openingHours;

	public CivicStructureBuilder(final Model model, final String projectNS) {
		super(model, projectNS);
	}

	public Resource create() throws Exception {
		if (getResource() == null) {
			if (title == null || title.length() == 0) {
				return null;
			}
			resource(md5(title.replaceAll(" ", "").toLowerCase()));
		}
		addProperty(RDF.type, type);
		addProperty(SCHEMA.description, description);
		addProperty(SCHEMA.image, image);
		addProperty(SCHEMA.latitude, latitude);
		addProperty(SCHEMA.longitude, longitude);
		addProperty(SCHEMA.openingHours, openingHours);
		addProperty(SCHEMA.serviceURL, serviceUrl);
		addProperty(SCHEMA.streetAddress, streetAddress);
		addProperty(SCHEMA.title, title);
		return getResource();
	}
	
	public CivicStructureBuilder description(final String description) {
		this.description = description;
		return this;
	}	
	
	public CivicStructureBuilder latitude(final Double latitude) {
		this.latitude = latitude;
		return this;
	}
	
	public CivicStructureBuilder longitude(final Double longitude) {
		this.longitude = longitude;
		return this;
	}
	
	public CivicStructureBuilder serviceUrl(final String serviceUrl) {
		this.serviceUrl = serviceUrl;
		return this;
	}
	
	public CivicStructureBuilder streetAddress(final String streetAddress) {
		this.streetAddress = streetAddress;
		return this;
	}
	
	public CivicStructureBuilder type(final Resource type) {
		this.type = type;
		return this;
	}	
	
	public CivicStructureBuilder title(final String title) {
		this.title = title;
		return this;
	}

	public CivicStructureBuilder image(final String image) {
		this.image = image;
		return this;
	}

	public CivicStructureBuilder openingHours(final List<String> openingHours) {
		this.openingHours = openingHours;
		return this;
	}

}
