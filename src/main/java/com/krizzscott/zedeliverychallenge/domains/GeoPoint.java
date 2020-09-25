package com.krizzscott.zedeliverychallenge.domains;

import static com.krizzscott.zedeliverychallenge.domains.GeometryType.POINT;
import static com.krizzscott.zedeliverychallenge.exceptions.badrequest.domain.ErrorDomainValidationDictionary.GEOPOINT_ARRAY_COORDINATES_CANNOT_BE_NULL;
import static com.krizzscott.zedeliverychallenge.exceptions.badrequest.domain.ErrorDomainValidationDictionary.GEOPOINT_ARRAY_COORDINATES_MUST_CONTAINS_TWO_COORDINATES_LAT_LONG;

import java.util.List;

import org.springframework.util.ObjectUtils;

import com.krizzscott.zedeliverychallenge.exceptions.badrequest.domain.DomainValidationException;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode
@Builder(toBuilder = true)
@ToString
public class GeoPoint {

	@Getter
	private String type;
	@Getter
	private List<Double> coordinates;

	public GeoPoint() {
		this.type = POINT.toString();
	}

	public void didMount() {
		if (ObjectUtils.isEmpty(getCoordinates())) {
			throw new DomainValidationException(GEOPOINT_ARRAY_COORDINATES_CANNOT_BE_NULL);
		}
		if (getCoordinates().size() != 2) {
			throw new DomainValidationException(GEOPOINT_ARRAY_COORDINATES_MUST_CONTAINS_TWO_COORDINATES_LAT_LONG);
		}
	}

}
