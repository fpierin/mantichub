package org.mantic.datastore.query.domain;

import java.io.Serializable;

public class Binding implements Serializable {

	private static final long serialVersionUID = -9097256964833028192L;
	
	private Item title;
	private Item latitude;
	private Item longitude;
	private Item startDate;
	private Item endDate;
	private Item startTime;
	private Item endTime;
	private Item cuisine;
	private Item description;
	private Item priceRange;
	private Item telephone;
	private Item overview;
	private Item streetAddress;
	private Item price;
	private Item url;

	public Binding() {
	}

	public Item getTitle() {
		return title;
	}

	public void setTitle(final Item title) {
		this.title = title;
	}

	public Item getLatitude() {
		return latitude;
	}

	public void setLatitude(final Item latitude) {
		this.latitude = latitude;
	}

	public Item getLongitude() {
		return longitude;
	}

	public void setLongitude(final Item longitude) {
		this.longitude = longitude;
	}

	public Item getStartDate() {
		return startDate;
	}

	public void setStartDate(final Item startDate) {
		this.startDate = startDate;
	}

	public Item getEndDate() {
		return endDate;
	}

	public void setEndDate(final Item endDate) {
		this.endDate = endDate;
	}

	public Item getStartTime() {
		return startTime;
	}

	public void setStartTime(final Item startTime) {
		this.startTime = startTime;
	}

	public Item getEndTime() {
		return endTime;
	}

	public void setEndTime(final Item endTime) {
		this.endTime = endTime;
	}

	public Item getCuisine() {
		return cuisine;
	}

	public void setCuisine(final Item cuisine) {
		this.cuisine = cuisine;
	}

	public Item getDescription() {
		return description;
	}

	public void setDescription(final Item description) {
		this.description = description;
	}

	public Item getPriceRange() {
		return priceRange;
	}

	public void setPriceRange(final Item priceRange) {
		this.priceRange = priceRange;
	}

	public Item getTelephone() {
		return telephone;
	}

	public void setTelephone(final Item telephone) {
		this.telephone = telephone;
	}

	public Item getOverview() {
		return overview;
	}

	public void setOverview(final Item overview) {
		this.overview = overview;
	}

	public Item getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(final Item streetAddress) {
		this.streetAddress = streetAddress;
	}

	public Item getPrice() {
		return price;
	}

	public void setPrice(final Item price) {
		this.price = price;
	}

	public Item getUrl() {
		return url;
	}

	public void setUrl(final Item url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Binding [title=" + title + ", latitude=" + latitude + ", longitude=" + longitude + ", startDate="
				+ startDate + ", endDate=" + endDate + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", cuisine=" + cuisine + ", description=" + description + ", priceRange=" + priceRange
				+ ", telephone=" + telephone + ", overview=" + overview + ", streetAddress=" + streetAddress
				+ ", price=" + price + ", url=" + url + "]";
	}

}
