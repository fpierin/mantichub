package org.mantic.datastore.query;

import static java.lang.String.valueOf;
import static java.text.MessageFormat.format;
import static org.mantic.datastore.infra.configuration.Configuration.BASIC_SPARQL_QUERY;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
			addDateFilter(sb);
			addTimeFilter(sb);
			addTypeFilter(sb);
			addLatitudeFilter(sb);
		}
		return sb.toString();
	}

	private void addTimeFilter(final StringBuilder sb) {
		if (resource != null) {
			final DateFormat df = new SimpleDateFormat("HH:mm:ss");
			final Date startTime = resource.getStartTime();
			final Date endTime = resource.getEndTime();
			if (startTime != null && endTime != null) {
				filter(sb, filterStatement("?startTime", ">", df.format(startTime)) + " && " + filterStatement("?endTime", "<", df.format(endTime)));	
			} else if (startTime != null) {
				filter(sb, "?startTime", "=", resource.getStartTime());	
			} else if (endTime != null) {
				filter(sb, "?endTime", "=", resource.getEndTime());
			}
		}
	}

	private void addDateFilter(final StringBuilder sb) {
		if (resource != null) {
			final DateFormat df = new SimpleDateFormat("yyyy-MM-ss");
			final Date startDate = resource.getStartDate();
			final Date endDate = resource.getEndDate();
			if (startDate != null && endDate != null) {
				filter(sb, filterStatement("?startDate", ">", df.format(startDate)) + " && " + filterStatement("?endDate", "<", df.format(endDate)));	
			} else if (startDate != null) {
				filter(sb, filterStatement("?startDate", "=", df.format(startDate)));
			} else if (endDate != null) {
				filter(sb, filterStatement("?endDate", "=", df.format(endDate)));
			}
		}
	}

	private void addTypeFilter(final StringBuilder sb) {
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
		resource.setStartDate(new Date());
		resource.setEndDate(new Date());
		System.out.println(new QueryBuilder()
//				.withRadius(new Double(0.5))
				.withFilter(resource)
				.build());
	}
	

}



