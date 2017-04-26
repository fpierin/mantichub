package com.mantichub.commons.resource;

import java.util.Date;
import java.util.List;

public class ResourceObject implements ResourceInterface {
	
	private static final long serialVersionUID = 5658255987517125203L;
	
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
	private String image;

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
	public String getImage() {
		return this.image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "ResourceObject [cuisine=" + cuisine + ", description=" + description + ", openingHours=" + openingHours
				+ ", priceRange=" + priceRange + ", telephone=" + telephone + ", endDate=" + endDate + ", endTime="
				+ endTime + ", latitude=" + latitude + ", longitude=" + longitude + ", overview=" + overview
				+ ", startDate=" + startDate + ", startTime=" + startTime + ", streetAddress=" + streetAddress
				+ ", title=" + title + ", type=" + type + ", price=" + price + ", url=" + url + ", image=" + image
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cuisine == null) ? 0 : cuisine.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
		result = prime * result + ((openingHours == null) ? 0 : openingHours.hashCode());
		result = prime * result + ((overview == null) ? 0 : overview.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((priceRange == null) ? 0 : priceRange.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
		result = prime * result + ((streetAddress == null) ? 0 : streetAddress.hashCode());
		result = prime * result + ((telephone == null) ? 0 : telephone.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResourceObject other = (ResourceObject) obj;
		if (cuisine == null) {
			if (other.cuisine != null)
				return false;
		} else if (!cuisine.equals(other.cuisine))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (longitude == null) {
			if (other.longitude != null)
				return false;
		} else if (!longitude.equals(other.longitude))
			return false;
		if (openingHours == null) {
			if (other.openingHours != null)
				return false;
		} else if (!openingHours.equals(other.openingHours))
			return false;
		if (overview == null) {
			if (other.overview != null)
				return false;
		} else if (!overview.equals(other.overview))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (priceRange == null) {
			if (other.priceRange != null)
				return false;
		} else if (!priceRange.equals(other.priceRange))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		if (streetAddress == null) {
			if (other.streetAddress != null)
				return false;
		} else if (!streetAddress.equals(other.streetAddress))
			return false;
		if (telephone == null) {
			if (other.telephone != null)
				return false;
		} else if (!telephone.equals(other.telephone))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (type != other.type)
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

}