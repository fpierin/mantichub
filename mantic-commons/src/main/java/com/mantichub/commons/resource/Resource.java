package com.mantichub.commons.resource;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Resource implements Event, Restaurant, Serializable  {

	private static final long serialVersionUID = -6633286043010778566L;
	
	private String cuisine;
	private String description;
	private List<String> openingHours;
	private String priceRange;
	private String telephone;
	private Date endDate;
	private Date endTime;
	private Double latitude;
	private Double longitude;
	private String overview;
	private Date startDate;
	private Date startTime;
	private String streetAddress;
	private String title;
	private Resources type;
	private Double price;
	private String url;

	@Override
	public String getCuisine() {
		return cuisine;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public List<String> getOpeningHours() {
		return openingHours;
	}

	@Override
	public String getPriceRange() {
		return priceRange;
	}

	@Override
	public String getTelephone() {
		return telephone;
	}

	@Override
	public Date getEndDate() {
		return endDate;
	}

	@Override
	public Date getEndTime() {
		return endTime;
	}

	@Override
	public Double getLatitude() {
		return latitude;
	}

	@Override
	public Double getLongitude() {
		return longitude;
	}

	@Override
	public String getOverview() {
		return overview;
	}

	@Override
	public Date getStartDate() {
		return startDate;
	}

	@Override
	public Date getStartTime() {
		return startTime;
	}

	@Override
	public String getStreetAddress() {
		return streetAddress;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public Resources getType() {
		return type;
	}

	@Override
	public Double getPrice() {
		return price;
	}

	@Override
	public String getUrl() {
		return url;
	}

}
