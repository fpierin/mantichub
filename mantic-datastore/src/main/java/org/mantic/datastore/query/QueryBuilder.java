package org.mantic.datastore.query;

import static java.lang.String.valueOf;
import static java.text.MessageFormat.format;
import static org.mantic.datastore.infra.configuration.Configuration.BASIC_SPARQL_QUERY;

import java.util.Arrays;
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
		return BASIC_SPARQL_QUERY.replace("{filtering}", filtering());
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
			filter(sb, "?type", "=", resource.getType());
			filter(sb, "?url", "=", resource.getUrl());
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
		return sb.toString();
//		final Date endDate = resource.getEndDate();
//		final Date endTime = resource.getEndTime();
//		final List<String> openingHours = resource.getOpeningHours();
//		final Date startDate = resource.getStartDate();
//		final Date startTime = resource.getStartTime();
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
		resource.setCuisine("xpto");
		resource.setDescription("desc");
		resource.setEndDate(new Date());
		resource.setEndTime(new Date());
		resource.setLatitude(-12.543);
		resource.setLongitude(-25.187412);
		resource.setOpeningHours(Arrays.asList(new String[]{"a", "b"}));
		resource.setOverview("oveeee");
		resource.setPrice(2.12);
		resource.setPriceRange("$$");
		resource.setStartDate(new Date());
		resource.setStartTime(new Date());
		resource.setStreetAddress("endeeeeeeeere");
		resource.setTelephone("12421412");
		resource.setTitle("titeeelue");
		resource.setType(Resources.Event);
		resource.setUrl("http://hgfwhqgfhqw");
		System.out.println(new QueryBuilder()
				.withRadius(new Double(0.5))
				.withFilter(resource)
				.build());
	}
	

}



