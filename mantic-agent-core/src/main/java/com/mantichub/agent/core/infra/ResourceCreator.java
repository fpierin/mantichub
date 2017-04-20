package com.mantichub.agent.core.infra;

import static com.mantichub.agent.core.utils.ResourceUtils.resourceFromName;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;

import com.mantichub.agent.core.builder.EventResourceBuilder;
import com.mantichub.agent.core.builder.RestaurantResourceBuilder;
import com.mantichub.commons.constant.MantichubConstants;
import com.mantichub.commons.resource.Event;
import com.mantichub.commons.resource.Restaurant;

public class ResourceCreator {
	
	public static Resource build(final Model model, final Event o) throws Exception {
		return new EventResourceBuilder(model, MantichubConstants.NAMESPACE)
				.price(o.getPrice())
				.endDate(o.getEndDate())
				.endTime(o.getEndTime())
				.latitude(o.getLatitude())
				.longitude(o.getLongitude())
				.overview(o.getOverview())
				.serviceUrl(o.getUrl())
				.startDate(o.getStartDate())
				.startTime(o.getStartTime())
				.streetAddress(o.getStreetAddress())
				.title(o.getTitle())
				.type(resourceFromName(o.getType()))
				.create();
	}
	
	public static Resource build(final Model model, final Restaurant o) throws Exception {
		return new RestaurantResourceBuilder(model, MantichubConstants.NAMESPACE)
				.description(o.getDescription())
				.latitude(o.getLatitude())
				.longitude(o.getLongitude())
				.openingHours(o.getOpeningHours())
				.priceRange(o.getPriceRange())
				.serviceUrl(o.getUrl())
				.streetAddress(o.getStreetAddress())
				.telephone(o.getTelephone())
				.title(o.getTitle())
				.type(resourceFromName(o.getType()))
				.create();
	}
	
}
