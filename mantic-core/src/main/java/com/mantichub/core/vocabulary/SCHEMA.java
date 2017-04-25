package com.mantichub.core.vocabulary;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

public class SCHEMA {
	
	private static Model m_model = ModelFactory.createDefaultModel();
	
	public static final String NS = "http://schema.org/";
	public static final String NSX = "http://topbraid.org/schemax/";
	
	public static final String dateFormat = "yyyy-MM-dd";
	public static final String datetimeFormat = "yyyy-MM-dd'T'HH:mm:ss";
	
	public static final Resource NAMESPACE = m_model.createResource(NS);
	public static final Resource BarOrPub = retrieveResource("BarOrPub");
	public static final Resource Event = retrieveResource("Event");
	public static final Resource DanceEvent = retrieveResource("DanceEvent");
	public static final Resource ExhibitionEvent = retrieveResource("ExhibitionEvent");
	public static final Resource Festival = retrieveResource("Festival");
	public static final Resource FoodEstablishment = retrieveResource("FoodEstablishment");
	public static final Resource FoodEvent = retrieveResource("FoodEvent");
	public static final Resource MusicEvent = retrieveResource("MusicEvent");
	public static final Resource Restaurant = retrieveResource("Restaurant");
	public static final Resource ScreeningEvent = retrieveResource("ScreeningEvent");
	public static final Resource SocialEvent = retrieveResource("SocialEvent");
	public static final Resource TheaterEvent = retrieveResource("TheaterEvent");
	
	public static final Property addressRegion = retrieveProperty("addressRegion");
	public static final Property addressLocality = retrieveProperty("addressLocality");
	public static final Property description = retrieveProperty("description");
	public static final Property endDate = retrieveProperty("endDate");
	public static final Property endTime = retrieveProperty("endTime");
	public static final Property latitude = retrieveProperty("latitude");
	public static final Property longitude = retrieveProperty("longitude");
	public static final Property openingHours = retrieveProperty("openingHours");
	public static final Property overview = retrieveProperty("overview");
	public static final Property price = retrieveProperty("price");
	public static final Property priceRange = retrieveProperty("priceRange");
	public static final Property serviceURL = retrieveProperty("serviceURL");
	public static final Property startDate = retrieveProperty("startDate");
	public static final Property startTime = retrieveProperty("startTime");
	public static final Property streetAddress = retrieveProperty("streetAddress");
	public static final Property telephone = retrieveProperty("telephone");
	public static final Property title = retrieveProperty("title");

	public static String getURI() {
		return NS;
	}
	
	private static Resource retrieveResource(final String resourceName) {
		return m_model.createResource(NS + resourceName);
	}
	
	private static Property retrieveProperty(final String propertyName) {
		return m_model.createProperty(NS + propertyName);
	}
	
}