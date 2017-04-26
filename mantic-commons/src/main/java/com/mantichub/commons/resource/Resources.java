package com.mantichub.commons.resource;

public enum Resources {

	BarOrPub,
	DanceEvent,
	Event,
	ExhibitionEvent,
	Festival,
	FoodEstablishment,
	FoodEvent,
	MusicEvent,
	Resource,
	Restaurant, 
	ScreeningEvent,
	SocialEvent,
	TheaterEvent,;

	public static Resources from(final String value) {
		if (value != null && value.length() > 0) {
			final Resources[] resources = Resources.values();
			for (Resources resource : resources) {
				if (resource.name().equalsIgnoreCase(value)) {
					return resource;
				}
			}
		}
		return null;
	}

}
