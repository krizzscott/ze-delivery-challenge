package com.krizzscott.zedeliverychallenge.gateway.database.entities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonMultiPolygon;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

public class GeoJsonMultiPolygonDeserializer extends JsonDeserializer<GeoJsonMultiPolygon> {

	@Override
	public GeoJsonMultiPolygon deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
		List<GeoJsonPolygon> polygons = new ArrayList<>();

		JsonNode node = jp.getCodec().readTree(jp);
		Iterator<JsonNode> polygonsRings = node.get("coordinates").elements();
		while (polygonsRings.hasNext()) {
			Iterator<JsonNode> ringsCoordinates = polygonsRings.next().elements();
			while (ringsCoordinates.hasNext()) {
				Iterator<JsonNode> positionsCoordinate = ringsCoordinates.next().elements();
				List<Point> points = new ArrayList<>();
				while(positionsCoordinate.hasNext()) {
					Iterator<JsonNode> pointsCoordinates = positionsCoordinate.next().elements();
					while(pointsCoordinates.hasNext()) {
						double x = pointsCoordinates.next().asDouble();
						double y = pointsCoordinates.next().asDouble();
						points.add(new Point(x, y));
					}
				}
				polygons.add(new GeoJsonPolygon(points));

			}			
			
		}
		
		return new GeoJsonMultiPolygon(polygons);

	}
}
