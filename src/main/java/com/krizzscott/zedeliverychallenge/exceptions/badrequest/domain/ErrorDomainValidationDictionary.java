package com.krizzscott.zedeliverychallenge.exceptions.badrequest.domain;

import lombok.Getter;


@Getter
public enum ErrorDomainValidationDictionary {

	PARAM_PARTNERID_CANNOT_BE_NULL(400001, "Parameter [partnerId] cannot be null"), 
	PARAM_DOCUMENT_CANNOT_BE_NULL(400001, "Parameter [document] cannot be null"), 
	
	// Partner domain
	PARTNER_OBJECT_CANNOT_BE_NULL(400001, "Domain Object [partner] cannot be null"),
	PARTNER_FIELD_TRADINGNAME_CANNOT_BE_NULL(400001, "Parameter [tradingName] cannot be null or empty"),
	PARTNER_FIELD_OWNERNAME_CANNOT_BE_NULL(400001, "Parameter [ownerName] cannot be null or empty"),
	PARTNER_FIELD_DOCUMENT_CANNOT_BE_NULL(400001, "Parameter [document] cannot be null or empty"),
	PARTNER_FIELD_DOCUMENT_MUST_CONTAINS_14_NUMBERS(400001, "Parameter [document] must contains 14 numbers"),
	PARTNER_OBJECT_ADDRESS_CANNOT_BE_NULL(400001, "Parameter [address] cannot be null"),
	PARTNER_OBJECT_COVERAGEAREA_CANNOT_BE_NULL(400001, "Parameter [coverageArea] cannot be null"),

	//GeoPoint domain
	GEOPOINT_FIELD_TYPE_CANNOT_BE_INVALID(400001, "Parameter [geopoint.type] cannot be null or invalid"),
	GEOPOINT_ARRAY_COORDINATES_CANNOT_BE_NULL(400001, "Parameter [geopoint.coordinates] cannot be null or empty"),
	GEOPOINT_ARRAY_COORDINATES_MUST_CONTAINS_TWO_COORDINATES_LAT_LONG(400001, "Parameter [geopoint.coordinates] must contains 2 coordinates (longitude, latitude)"),

	//GeoMultiPolygon domain
	GEOMULTIPOLYGON_FIELD_TYPE_CANNOT_BE_INVALID(400001, "Parameter [geomultipolygon.type] cannot be null or invalid"),
	GEOMULTIPOLYGON_ARRAY_COORDINATES_CANNOT_BE_NULL(400001, "Parameter [geomultipolygon.coordinates] cannot be null or empty"), 
	GEOMULTIPOLYGON_ARRAY_COORDINATES_MUST_CONTAINS_AT_LEAST_ONE_POLYGON(400001, "Parameter [geomultipolygon.coordinates] must contains at least 1 polygon"), 
	GEOMULTIPOLYGON_ARRAY_COORDINATES_LINEAR_POINTS_MUST_CONTAINS_FIVE_POINTS(400001, "Parameter [geomultipolygon.coordinates] must contains 5 points of coordinates"), 
	GEOMULTIPOLYGON_ARRAY_COORDINATES_MUST_CONTAINS_TWO_COORDINATES_LAT_LONG(400001, "Parameter [geomultipolygon.coordinates] must contains 2 coordinates (longitude, latitude)"),
	;

	private int errorCode;
	private String message;

	private ErrorDomainValidationDictionary(int errorCode, String message) {
		this.errorCode = errorCode;
		this.message = message;
	}

}
