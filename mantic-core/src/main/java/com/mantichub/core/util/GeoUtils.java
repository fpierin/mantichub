package com.mantichub.core.util;

public class GeoUtils {
	
	public static void radius(final double distanceInKm, final double latitude, final double longitude) {
		double R = 6371;  // earth radius in km
		double radius = distanceInKm; // km
		double x1 = longitude - Math.toDegrees(radius/R/Math.cos(Math.toRadians(latitude)));
		double x2 = longitude + Math.toDegrees(radius/R/Math.cos(Math.toRadians(latitude)));
		double y1 = latitude + Math.toDegrees(radius/R);
		double y2 = latitude - Math.toDegrees(radius/R);
		
		System.out.println(latitude + "," + longitude);
		System.out.println(y1 + "," + x1);
		System.out.println(y2 + "," + x2);
		System.out.println();
	}
	
	public static void main(String[] args) {
		radius(0.5, -23.6348042, -46.6400398);
//		radius(1, -21.171446, -47.860565);
	}
	

}
