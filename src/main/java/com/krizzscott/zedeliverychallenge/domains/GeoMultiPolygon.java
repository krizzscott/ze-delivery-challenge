package com.krizzscott.zedeliverychallenge.domains;

import static com.krizzscott.zedeliverychallenge.domains.GeometryType.MULTIPOLYGON;
import static com.krizzscott.zedeliverychallenge.exceptions.badrequest.domain.ErrorDomainValidationDictionary.GEOMULTIPOLYGON_ARRAY_COORDINATES_CANNOT_BE_NULL;
import static com.krizzscott.zedeliverychallenge.exceptions.badrequest.domain.ErrorDomainValidationDictionary.GEOMULTIPOLYGON_ARRAY_COORDINATES_LINEAR_POINTS_MUST_CONTAINS_FIVE_POINTS;
import static com.krizzscott.zedeliverychallenge.exceptions.badrequest.domain.ErrorDomainValidationDictionary.GEOMULTIPOLYGON_ARRAY_COORDINATES_MUST_CONTAINS_AT_LEAST_ONE_POLYGON;

import java.util.List;

import org.springframework.util.ObjectUtils;

import com.krizzscott.zedeliverychallenge.exceptions.badrequest.domain.DomainValidationException;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode
@ToString
public class GeoMultiPolygon {

	@Getter
	private String type;
	@Getter
	@Setter
	private List<List<List<List<Double>>>> coordinates;

	public GeoMultiPolygon() {
		this.type = MULTIPOLYGON.toString();
	}

	public void didMount() {
		validatePolygonLayer();
		validateLinearLayer();
	}
	
	private void validatePolygonLayer() {
		if (ObjectUtils.isEmpty(getCoordinates())) {//multipolygon
			throw new DomainValidationException(GEOMULTIPOLYGON_ARRAY_COORDINATES_CANNOT_BE_NULL);
		}
		if (ObjectUtils.isEmpty(getCoordinates().get(0))) {//polygons
			throw new DomainValidationException(GEOMULTIPOLYGON_ARRAY_COORDINATES_MUST_CONTAINS_AT_LEAST_ONE_POLYGON);
		}
	}
	
	private void validateLinearLayer() {
		if (ObjectUtils.isEmpty(getCoordinates().get(0).get(0))) {//linear
			throw new DomainValidationException(GEOMULTIPOLYGON_ARRAY_COORDINATES_LINEAR_POINTS_MUST_CONTAINS_FIVE_POINTS);
		}		
		if (getCoordinates().get(0).get(0).get(0).size() < 5) {//linear
			throw new DomainValidationException(GEOMULTIPOLYGON_ARRAY_COORDINATES_LINEAR_POINTS_MUST_CONTAINS_FIVE_POINTS);
		}		
	}
	
}
