package com.krizzscott.zedeliverychallenge.gateway.database.entities;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonMultiPolygon;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

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
	@Getter @Setter private String document;
	@GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
	@Getter @Setter private GeoJsonPoint address;
	@Getter @Setter private GeoJsonMultiPolygon coverageArea;


}
