package org.mantic.datastore.query.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

import org.mantic.datastore.query.QueryBuilder;
import org.mantic.datastore.query.domain.Binding;
import org.mantic.datastore.query.domain.Item;
import org.mantic.datastore.service.SparqlResponse;
import org.mantic.datastore.service.SparqlResult;

import com.mantichub.commons.resource.ResourceObject;
import com.mantichub.commons.resource.Resources;
import com.mantichub.core.serialization.SerializationService;
import com.mantichub.core.util.StringUtils;

@Named("queryService")
public class QueryServiceImpl implements QueryService {

	private final SerializationService serializationService;

	@Inject
	public QueryServiceImpl(final SerializationService serializationService) {
		this.serializationService = serializationService;
	}

	@Override
	public String buildQuery(final ResourceObject resource, final Double radius, final Integer limit) {
		return new QueryBuilder()
				.withFilter(resource)
				.withRadius(radius)
				.withLimit(limit == null? 100 : limit)
				.build();
	}

	@Override
	public Set<ResourceObject> map(final String json) {
		final Set<ResourceObject> resources = new HashSet<>();
		final SparqlResponse sparqlResponse = serializationService.toObject(json, SparqlResponse.class);
		if (sparqlResponse != null) {
			final SparqlResult results = sparqlResponse.getResults();
			final List<Binding> bindings = results.getBindings();
			if (bindings != null && !bindings.isEmpty()) {
				bindings.forEach(binding -> {
					resources.add(resourceFrom(binding));
				});
			}
		}
		return resources;
	}

	@Override
	public ResourceObject resourceFrom(final Binding b) {
		final ResourceObject rs = new ResourceObject();
		rs.setCuisine(valueFromItem(b.getCuisine(), String.class));
		rs.setDescription(valueFromItem(b.getDescription(), String.class));
		rs.setEndDate(valueFromItem(b.getEndDate(), Date.class));
		rs.setEndTime(valueFromItem(b.getEndTime(), Date.class));
		rs.setLatitude(valueFromItem(b.getLatitude(), Double.class));
		rs.setLongitude(valueFromItem(b.getLongitude(), Double.class));
		// rs.setOpeningHours(valueFromItem(b.get, String.class));
		rs.setOverview(valueFromItem(b.getOverview(), String.class));
		rs.setPrice(valueFromItem(b.getPrice(), Double.class));
		rs.setPriceRange(valueFromItem(b.getPriceRange(), String.class));
		rs.setStartDate(valueFromItem(b.getStartDate(), Date.class));
		rs.setStartTime(valueFromItem(b.getStartTime(), Date.class));
		rs.setStreetAddress(valueFromItem(b.getStreetAddress(), String.class));
		rs.setTelephone(valueFromItem(b.getTelephone(), String.class));
		rs.setTitle(valueFromItem(b.getTitle(), String.class));
		rs.setType(valueFromItem(b.getType(), Resources.class));
		rs.setImage(valueFromItem(b.getImage(), String.class));
		rs.setUrl(valueFromItem(b.getUrl(), String.class));
		return rs;
	}

	private <T> T valueFromItem(final Item item, final Class<T> clazz) {
		try {
			if (item != null) {
				Object obj = item.getValue();
				if (clazz == Double.class) {
					obj = new Double(item.getValue());
				} else if (clazz == Date.class) {
					final String value = item.getValue();
					if (value.contains("-")) {
						obj = parseDate(value, "yyyy-MM-dd");
					} else if (value.contains(":")) {
						obj = parseDate(value, "HH:mm:ss");
					}
				} else if (clazz == Resources.class) {
					final String value = item.getValue();
					obj = resourceFromUri(value);
				}
				return clazz.cast(obj);
			}
		} catch (final Exception e) {
			return null;
		}
		return null;
	}

	private Object parseDate(final String value, final String dateFormat) throws ParseException {
		final DateFormat df = new SimpleDateFormat(dateFormat);
		return df.parse(value);
	}

	private static Resources resourceFromUri(final String url) {
		if (StringUtils.isNotBlank(url)) {
			final String value = url.substring(url.lastIndexOf("/") + 1, url.length());
			return Resources.from(value);
		} 
		return null;
	}

}

