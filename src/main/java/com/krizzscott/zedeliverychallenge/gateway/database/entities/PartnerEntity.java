package com.krizzscott.zedeliverychallenge.gateway.database.entities;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonMultiPolygon;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
@Document("partners")
public class PartnerEntity {

	@Id
	@Getter private String id;
	@Getter @Setter private String tradingName;
	@Getter @Setter private String ownerName;
	
	@Indexed(unique = true)
	@Getter @Setter private String document;
	
	@JsonSerialize(using = GeoJsonPointSerializer.class)
	@JsonDeserialize(using = GeoJsonPointDeserializer.class)
	@GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
	@Getter @Setter private GeoJsonPoint address;
	
	@JsonDeserialize(using = GeoJsonMultiPolygonDeserializer.class)
	@JsonSerialize(using = GeoJsonMultiPolygonSerializer.class)
	@Getter @Setter private GeoJsonMultiPolygon coverageArea;


}
