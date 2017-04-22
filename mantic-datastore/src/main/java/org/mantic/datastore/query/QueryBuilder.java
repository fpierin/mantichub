package org.mantic.datastore.query;

import static java.lang.String.valueOf;
import static java.text.MessageFormat.format;
import static org.mantic.datastore.infra.configuration.Configuration.BASIC_SPARQL_QUERY;

import com.mantichub.commons.resource.ResourceObject;
import com.mantichub.commons.resource.Resources;
import com.mantichub.core.domain.GeoCoordinates;
import com.mantichub.core.util.GeoUtils;

public class QueryBuilder {
	
	private ResourceObject resource;
	private Double radius;

	
	public QueryBuilder withFilter(final ResourceObject resource) {
		this.resource = resource;
		return this;
	}
	
	public QueryBuilder withRadius(final Double radius) {
		this.radius = radius;
		return this;
	}
	
	public String build() {
		return BASIC_SPARQL_QUERY
				.replace("{rdfType}", rdfType())
				.replace("{filtering}", filtering());
	}

	private String rdfType() {
		if (resource != null && resource.getType() != null) {
			return "schema:" + resource.getType().name(); 
		}
		return "rdfs:Resource";
	}

	private String filtering() {
		final StringBuilder sb = new StringBuilder();
		if (resource != null) {
			filter(sb, "?cuisine", "=", resource.getCuisine());
			filter(sb, "?description", "=", resource.getDescription());
			filter(sb, "?overview", "=", resource.getOverview());
			filter(sb, "?price", "=", resource.getPrice());
			filter(sb, "?streetAddress", "=", resource.getStreetAddress());
			filter(sb, "?telephone", "=", resource.getTelephone());
			filter(sb, "?priceRange", "=", resource.getPriceRange());
			filter(sb, "?title", "=", resource.getTitle());
			filter(sb, "?url", "=", resource.getUrl());
			addType(sb);
			addLatitudeFilter(sb);
		}
		return sb.toString();
//		final Date endDate = resource.getEndDate();
//		final Date endTime = resource.getEndTime();
//		final List<String> openingHours = resource.getOpeningHours();
//		final Date startDate = resource.getStartDate();
//		final Date startTime = resource.getStartTime();
	}

	private void addType(final StringBuilder sb) {
		String typeName = "Resource";
		final Resources type = resource.getType();
		if (type != null) {
			typeName = type.name(); 
		}
		filter(sb, "?type", "=", typeName);	
	}

	private void addLatitudeFilter(final StringBuilder sb) {
		if (radius == null) {
			filter(sb, "?latitude", "=", resource.getLatitude());
			filter(sb, "?longitude", "=", resource.getLongitude());
		} else {
			if (resource.getLatitude() != null && resource.getLongitude() != null)  {
				final GeoCoordinates coordinates = GeoUtils.radius(radius, resource.getLatitude(), resource.getLongitude());
				filter(sb, filterStatement("?latitude", ">", coordinates.getY1()) + " && " + filterStatement("?latitude", "<", coordinates.getY2()));
				filter(sb, filterStatement("?longitude ", ">", coordinates.getX1()) + " && " + filterStatement("?longitude ", "<", coordinates.getX2()));
			}
		}
//		"2017-04-18" | "2017-04-18" | "09:00:00" | "18:00:00"
	}

	private void filter(final StringBuilder sb, final String column, final String condition, final Object value) {
		if (value != null) {
			filter(sb, filterStatement(column, condition, value));
		}
	}
	
	private void filter(final StringBuilder sb, final String statement) {
		if (statement != null) {
			sb.append("	FILTER (" + statement + ")\n");
		}
	}

	private String filterStatement(final String column, final String condition, final Object value) {
		return format("{0} {1} {2}", column, condition, "'" + valueOf(value)) + "'";
	}
	
	public static void main(final String[] args) {
		final ResourceObject resource = new ResourceObject();
//		resource.setStartDate(new Date());
//		resource.setEndDate(new Date());
		System.out.println(new QueryBuilder()
//				.withRadius(new Double(0.5))
				.withFilter(resource)
				.build());
	}
	

}



