package com.krizzscott.zedeliverychallenge.gateway.database.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonMultiPolygon;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;

import com.krizzscott.zedeliverychallenge.domains.GeoMultiPolygon;

public final class GeoMultiPolygonEntityConverter {
	
	private GeoMultiPolygonEntityConverter() {}
	
	static GeoJsonMultiPolygon toEntity(GeoMultiPolygon domain) {
		List<GeoJsonPolygon> polygons = new ArrayList<>();
		
		for (List<List<List<Double>>> polygonsRings : domain.getCoordinates()) {
			for (List<List<Double>> ringsCoordinates : polygonsRings) {
				List<Point> points = new ArrayList<>();
				for (List<Double> pointsCoordinate : ringsCoordinates) {
					points.add(new Point(pointsCoordinate.get(0), pointsCoordinate.get(1)));
				}
				polygons.add(new GeoJsonPolygon(points));
			}
		}
		return new GeoJsonMultiPolygon(polygons);
	}

}
