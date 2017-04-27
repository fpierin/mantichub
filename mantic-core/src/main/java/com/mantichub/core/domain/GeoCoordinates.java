package com.mantichub.core.domain;

public class GeoCoordinates {

	private double y1;
	private double x1;
	private double y2;
	private double x2;

	public GeoCoordinates(final double y1, final double x1, final double y2, final double x2) {
		this.y1 = y1;
		this.x1 = x1;
		this.y2 = y2;
		this.x2 = x2;
	}

	public double getY1() {
		return y1;
	}

	public void setY1(final double y1) {
		this.y1 = y1;
	}

	public double getX1() {
		return x1;
	}

	public void setX1(final double x1) {
		this.x1 = x1;
	}

	public double getY2() {
		return y2;
	}

	public void setY2(final double y2) {
		this.y2 = y2;
	}

	public double getX2() {
		return x2;
	}

	public void setX2(final double x2) {
		this.x2 = x2;
	}

	@Override
	public String toString() {
		return "GeoCoordinates [y1=" + y1 + ", x1=" + x1 + ", y2=" + y2 + ", x2=" + x2 + "]";
	}
	
}
