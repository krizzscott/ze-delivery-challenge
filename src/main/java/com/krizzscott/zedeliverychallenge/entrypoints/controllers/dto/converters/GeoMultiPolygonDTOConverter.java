package com.krizzscott.zedeliverychallenge.entrypoints.controllers.dto.converters;

import static com.krizzscott.zedeliverychallenge.domains.GeometryType.MULTIPOLYGON;

import java.util.ArrayList;
import java.util.List;

import com.krizzscott.zedeliverychallenge.domains.GeoMultiPolygon;
import com.krizzscott.zedeliverychallenge.entrypoints.controllers.dto.CoverageAreaDTO;

public final class GeoMultiPolygonDTOConverter {
	
	private GeoMultiPolygonDTOConverter() {}

	public static GeoMultiPolygon toDomain(CoverageAreaDTO schema) {
		return new GeoMultiPolygon().toBuilder().type(MULTIPOLYGON.toString())
				.coordinates(convertMultiPolygonList(schema.getCoordinates())).build();
	}

	public static CoverageAreaDTO toDTO(GeoMultiPolygon domain) {
		return new CoverageAreaDTO().toBuilder().type(domain.getType())
				.coordinates(convertMultiPolygonList(domain.getCoordinates())).build();
	}

	private static List<List<List<List<Double>>>> convertMultiPolygonList(List<List<List<List<Double>>>> coordinates) {
		List<List<List<List<Double>>>> multiPolygons = new ArrayList<>();

		for (List<List<List<Double>>> polygonsRings : coordinates) {
			List<List<List<Double>>> rings = new ArrayList<>();

			for (List<List<Double>> ringsCoordinates : polygonsRings) {
				List<List<Double>> points = new ArrayList<>();

				for (List<Double> pointsCoordinate : ringsCoordinates) {
					points.add(pointsCoordinate);
				}

				rings.add(points);

			}

			multiPolygons.add(rings);

		}

		return multiPolygons;
	}
}
