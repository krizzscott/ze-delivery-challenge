package com.krizzscott.zedeliverychallenge.gateway.database.entities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

public class GeoJsonPointDeserializer extends JsonDeserializer<GeoJsonPoint> {

	@Override
	public GeoJsonPoint deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException {
		
		JsonNode node = jp.getCodec().readTree(jp);
		Iterator<JsonNode> iteratorCoordinates = node.get("coordinates").elements();
		List<Double> coordinates = new ArrayList<>();
		while (iteratorCoordinates.hasNext()) {
			Iterator<JsonNode> iteratorPositions = iteratorCoordinates.next().elements();
			while (iteratorPositions.hasNext()) {
				coordinates.add(iteratorPositions.next().asDouble());
			}
		}
		
		return coordinates.size() == 2 ? new GeoJsonPoint(coordinates.get(0), coordinates.get(1)) : 
			new GeoJsonPoint(0.0, 0.0);
	}

}
