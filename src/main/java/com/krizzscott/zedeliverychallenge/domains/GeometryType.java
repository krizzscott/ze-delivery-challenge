package com.krizzscott.zedeliverychallenge.domains;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public enum GeometryType {

	POINT("Point"),
	LINESTRING("LineString"),
	POLYGON("Polygon"),
	MULTIPOLYGON("MultiPolygon");

	private String value;
	private GeometryType(String value) { 
		this.value = value;
	}
	
	private static final Map<String, GeometryType> types = new HashMap<>();
	static {
		for (GeometryType type : values()) {
			types.put(type.name(), type);
		}
	}

	public static String findValue(String value) {
		GeometryType geometryType = types.get(value.toUpperCase(Locale.US));
		return Objects.nonNull(geometryType) ? geometryType.toString() : null;
	}
	
	@Override
	public String toString() {
		return this.value;
	}
}
