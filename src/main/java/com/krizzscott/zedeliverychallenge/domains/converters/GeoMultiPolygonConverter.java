package com.krizzscott.zedeliverychallenge.domains.converters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonLineString;
import org.springframework.data.mongodb.core.geo.GeoJsonMultiPolygon;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;

import com.krizzscott.zedeliverychallenge.domains.GeoMultiPolygon;
import com.krizzscott.zedeliverychallenge.domains.GeometryType;

public class GeoMultiPolygonConverter {

	public static GeoMultiPolygon toDomain(GeoJsonMultiPolygon entity) {
		return new GeoMultiPolygon().toBuilder().type(GeometryType.findValue(entity.getType()))
				.coordinates(convertMultiPolygonList(entity.getCoordinates())).build();
	}

	private static List<List<List<List<Double>>>> convertMultiPolygonList(List<GeoJsonPolygon> polygons) {
		List<List<List<List<Double>>>> multiPolygons = new ArrayList<>();

		for (GeoJsonPolygon polygonsRings : polygons) {
			List<List<List<Double>>> rings = new ArrayList<>();

			for (GeoJsonLineString ringsCoordinates : polygonsRings.getCoordinates()) {
				List<List<Double>> points = new ArrayList<>();

				for (Point pointsCoordinate : ringsCoordinates.getCoordinates()) {
					points.add(Arrays.asList(Double.valueOf(pointsCoordinate.getX()), Double.valueOf(pointsCoordinate.getY())));
				}

				rings.add(points);

			}

			multiPolygons.add(rings);

		}

		return multiPolygons;
	}

}
