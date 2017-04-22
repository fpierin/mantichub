package org.mantic.datastore.query.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.mantic.datastore.query.QueryBuilder;
import org.mantic.datastore.query.domain.Binding;
import org.mantic.datastore.query.domain.Item;
import org.mantic.datastore.service.SparqlResponse;
import org.mantic.datastore.service.SparqlResult;

import com.mantichub.commons.resource.ResourceObject;
import com.mantichub.core.serialization.JsonSerializationServiceImpl;
import com.mantichub.core.serialization.SerializationService;

@Named("queryService")
public class QueryServiceImpl implements QueryService {

	private final SerializationService serializationService;

	@Inject
	public QueryServiceImpl(final SerializationService serializationService) {
		this.serializationService = serializationService;
	}

	@Override
	public String buildQuery(final ResourceObject resource, final Double radius) {
		return new QueryBuilder().withFilter(resource).withRadius(radius).build();
	}

	@Override
	public List<ResourceObject> map(final String json) {
		final List<ResourceObject> resources = new ArrayList<>();
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
		rs.setTelephone(valueFromItem(b.getUrl(), String.class));
		rs.setTitle(valueFromItem(b.getUrl(), String.class));
//		 rs.setType(valueFromItem(b.get, String.class));
		rs.setUrl(valueFromItem(b.getUrl(), String.class));
		return rs;
	}

	private <T> T valueFromItem(final Item item, Class<T> clazz) {
		try {
			if (item != null) {
				Object obj = item.getValue();
				if (clazz == Double.class) {
					obj = new Double(item.getValue());
				}
				return clazz.cast(obj);
			}
		} catch (final Exception e) {
			return null;
		}
		return null;
	}

	public static void main(String[] args) {
		QueryService queryService = new QueryServiceImpl(new JsonSerializationServiceImpl());
		Binding binding = new Binding();
		Item item = new Item();
		item.setValue("-25.232131");
		binding.setLatitude(item);
		;
		ResourceObject resourceFrom = queryService.resourceFrom(binding);
		System.out.println(resourceFrom.getLatitude());
	}

}
