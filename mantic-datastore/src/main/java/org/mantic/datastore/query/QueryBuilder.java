package org.mantic.datastore.query;

import static com.mantichub.commons.resource.Resources.Resource;
import static java.lang.String.valueOf;
import static java.text.MessageFormat.format;
import static org.mantic.datastore.infra.configuration.Configuration.BASIC_SPARQL_QUERY;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mantichub.commons.resource.ResourceObject;
import com.mantichub.core.domain.GeoCoordinates;
import com.mantichub.core.util.GeoUtils;

public class QueryBuilder {
	
	private ResourceObject resource;
	private Double radius;
	private Integer limit = 0;

	
	public QueryBuilder withFilter(final ResourceObject resource) {
		this.resource = resource;
		return this;
	}
	
	public QueryBuilder withNoLimit() {
		this.limit = 0;
		return this;
	}
	
	public QueryBuilder withLimit(final Integer limit) {
		this.limit = limit;
		return this;
	}		
	
	public QueryBuilder withRadius(final Double radius) {
		this.radius = radius;
		return this;
	}
	
	public String build() {
		return BASIC_SPARQL_QUERY
				.replace("{rdfType}", rdfType())
				.replace("{filtering}", filtering())
				.replace("{limit}", limit());
	}

	private String limit() {
		if (limit == null || limit == 0) {
			return "";
		}
		return "LIMIT " + limit;
	}

	private String rdfType() {
		if (resource != null && resource.getType() != null) {
			if (!Resource.equals(resource.getType())) {
				return "schema:" + resource.getType().name(); 
			}
		}
		return "rdfs:Resource";
	}

	private String filtering() {
		final StringBuilder sb = new StringBuilder();
		if (resource != null) {
			filter(sb, "?cuisine", "=", resource.getServesCuisine());
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

	private void addLatitudeFilter(final StringBuilder sb) {
		if (radius == null) {
			filter(sb, "?latitude", "=", resource.getLatitude());
			filter(sb, "?longitude", "=", resource.getLongitude());
		} else {
			if (resource.getLatitude() != null && resource.getLongitude() != null)  {
				final GeoCoordinates coordinates = GeoUtils.radius(radius, resource.getLatitude(), resource.getLongitude());
				filter(sb, filterStatement("?latitude", ">", coordinates.getY2()) + " && " + filterStatement("?latitude", "<", coordinates.getY1()));
				filter(sb, filterStatement("?longitude", ">", coordinates.getX2()) + " && " + filterStatement("?longitude", "<", coordinates.getX1()));
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
//		FILTER (?lat > '-23.625810983940813' && ?lat < '-23.64379741605919')
//		FILTER (?lon > '-46.63022315780229' && ?lon < '-46.6498564421977')
		final ResourceObject resource = new ResourceObject();
//		resource.setType(Resources.TrainStation);
//	    FILTER (?latitude > '-23.625810983940813' && ?latitude < '-23.64379741605919')
//		FILTER (?longitude > '-46.63022315780229' && ?longitude < '-46.6498564421977')
		resource.setLatitude(-23.5486);
		resource.setLongitude(-46.6392);
		System.out.println(new QueryBuilder()
				.withRadius(new Double(1))
				.withFilter(resource)
				.withLimit(1000)
				.build());
//				-23.569518, -46.69407
//		latitude: -23.560437,
//		longitude: -46.723105,
	}
	
	

}