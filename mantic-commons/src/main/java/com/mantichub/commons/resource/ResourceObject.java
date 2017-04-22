package com.mantichub.commons.resource;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ResourceObject implements Event, Restaurant, Serializable {

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

	public void setCuisine(final String cuisine) {
		this.cuisine = cuisine;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public void setOpeningHours(final List<String> openingHours) {
		this.openingHours = openingHours;
	}

	public void setPriceRange(final String priceRange) {
		this.priceRange = priceRange;
	}

	public void setTelephone(final String telephone) {
		this.telephone = telephone;
	}

	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}

	public void setEndTime(final Date endTime) {
		this.endTime = endTime;
	}

	public void setLatitude(final Double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(final Double longitude) {
		this.longitude = longitude;
	}

	public void setOverview(final String overview) {
		this.overview = overview;
	}

	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

	public void setStartTime(final Date startTime) {
		this.startTime = startTime;
	}

	public void setStreetAddress(final String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public void setType(final Resources type) {
		this.type = type;
	}

	public void setPrice(final Double price) {
		this.price = price;
	}

	public void setUrl(final String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "ResourceObject [cuisine=" + cuisine + ", description=" + description + ", openingHours=" + openingHours
				+ ", priceRange=" + priceRange + ", telephone=" + telephone + ", endDate=" + endDate + ", endTime="
				+ endTime + ", latitude=" + latitude + ", longitude=" + longitude + ", overview=" + overview
				+ ", startDate=" + startDate + ", startTime=" + startTime + ", streetAddress=" + streetAddress
				+ ", title=" + title + ", type=" + type + ", price=" + price + ", url=" + url + "]";
	}

}
