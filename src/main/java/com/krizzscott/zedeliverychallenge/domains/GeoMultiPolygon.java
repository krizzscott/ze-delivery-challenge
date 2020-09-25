package com.krizzscott.zedeliverychallenge.domains;

import static com.krizzscott.zedeliverychallenge.domains.GeometryType.MULTIPOLYGON;
import static com.krizzscott.zedeliverychallenge.exceptions.badrequest.domain.ErrorDomainValidationDictionary.GEOMULTIPOLYGON_ARRAY_COORDINATES_CANNOT_BE_NULL;
import static com.krizzscott.zedeliverychallenge.exceptions.badrequest.domain.ErrorDomainValidationDictionary.GEOMULTIPOLYGON_ARRAY_COORDINATES_LINEAR_POINTS_MUST_CONTAINS_FOUR_OR_MORE_POSITIONS;
import static com.krizzscott.zedeliverychallenge.exceptions.badrequest.domain.ErrorDomainValidationDictionary.GEOMULTIPOLYGON_ARRAY_COORDINATES_MUST_CONTAINS_AT_LEAST_ONE_POLYGON;
import static com.krizzscott.zedeliverychallenge.exceptions.badrequest.domain.ErrorDomainValidationDictionary.GEOMULTIPOLYGON_ARRAY_COORDINATES_MUST_CONTAINS_TWO_COORDINATES_LAT_LONG;

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
		if (ObjectUtils.isEmpty(getCoordinates())) {// multipolygon
			throw new DomainValidationException(GEOMULTIPOLYGON_ARRAY_COORDINATES_CANNOT_BE_NULL);
		}

		getCoordinates().forEach(polygons -> {// polygons
			validPolygons(polygons);
			polygons.forEach(linearPoints -> { // linear points
				validLinearPoints(linearPoints);
				linearPoints.forEach(points -> validPoints(points));
			});
		});

	}

	private void validPoints(List<Double> points) {
		if (ObjectUtils.isEmpty(points)
				|| points.size() != 2) {// coordinates
			throw new DomainValidationException(
					GEOMULTIPOLYGON_ARRAY_COORDINATES_MUST_CONTAINS_TWO_COORDINATES_LAT_LONG);
		}
	}

	private void validLinearPoints(List<List<Double>> linearPoints) {
		if (ObjectUtils.isEmpty(linearPoints) || linearPoints.size() < 4) {
			throw new DomainValidationException(
					GEOMULTIPOLYGON_ARRAY_COORDINATES_LINEAR_POINTS_MUST_CONTAINS_FOUR_OR_MORE_POSITIONS);
		}
	}

	private void validPolygons(List<List<List<Double>>> polygons) {
		if (ObjectUtils.isEmpty(polygons)) {
			throw new DomainValidationException(GEOMULTIPOLYGON_ARRAY_COORDINATES_MUST_CONTAINS_AT_LEAST_ONE_POLYGON);
		}
	}

}
