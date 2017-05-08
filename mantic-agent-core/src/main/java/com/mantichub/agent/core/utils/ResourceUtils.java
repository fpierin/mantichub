package com.mantichub.agent.core.utils;

import static com.mantichub.core.util.StringUtils.isNotBlank;

import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

import com.mantichub.commons.resource.Resources;
import com.mantichub.core.vocabulary.SCHEMA;

public class ResourceUtils {

	public static void addProperty(final Resource resource, final Property property, final Object value) {
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

	public static Resource resourceFromName(final Resources resource) {
		if (resource != null) {
			switch (resource) {
			case BarOrPub:
				return SCHEMA.BarOrPub;
			case DanceEvent:
				return SCHEMA.DanceEvent;
			case Event:
				return SCHEMA.Event;				
			case ExhibitionEvent:
				return SCHEMA.ExhibitionEvent;
			case Festival:
				return SCHEMA.Festival;
			case FoodEstablishment:
				return SCHEMA.FoodEstablishment;				
			case FoodEvent:
				return SCHEMA.FoodEvent;
			case MusicEvent:
				return SCHEMA.MusicEvent;				
			case Restaurant:
				return SCHEMA.Restaurant;
			case ScreeningEvent:
				return SCHEMA.ScreeningEvent;
			case SocialEvent:
				return SCHEMA.SocialEvent;
			case TheaterEvent:
				return SCHEMA.TheaterEvent;
			case TrainStation:
				return SCHEMA.TrainStation;
			default:
				break;
			}
		}
		return null;
	}

}

