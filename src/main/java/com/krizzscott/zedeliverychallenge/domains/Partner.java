package com.krizzscott.zedeliverychallenge.domains;




import static com.krizzscott.zedeliverychallenge.exceptions.badrequest.domain.ErrorDomainValidationDictionary.PARTNER_FIELD_DOCUMENT_CANNOT_BE_NULL;
import static com.krizzscott.zedeliverychallenge.exceptions.badrequest.domain.ErrorDomainValidationDictionary.PARTNER_FIELD_DOCUMENT_MUST_CONTAINS_14_NUMBERS;
import static com.krizzscott.zedeliverychallenge.exceptions.badrequest.domain.ErrorDomainValidationDictionary.PARTNER_FIELD_OWNERNAME_CANNOT_BE_NULL;
import static com.krizzscott.zedeliverychallenge.exceptions.badrequest.domain.ErrorDomainValidationDictionary.PARTNER_FIELD_TRADINGNAME_CANNOT_BE_NULL;
import static com.krizzscott.zedeliverychallenge.exceptions.badrequest.domain.ErrorDomainValidationDictionary.PARTNER_OBJECT_ADDRESS_CANNOT_BE_NULL;
import static com.krizzscott.zedeliverychallenge.exceptions.badrequest.domain.ErrorDomainValidationDictionary.PARTNER_OBJECT_COVERAGEAREA_CANNOT_BE_NULL;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.ObjectUtils;

import com.krizzscott.zedeliverychallenge.exceptions.badrequest.domain.DomainValidationException;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode
@ToString
public class Partner {
	
	private String id;
	private String tradingName;
	private String ownerName;
	private String document;
	private GeoPoint address;
	private GeoMultiPolygon coverageArea;
	
	
	public void didMount() {
		if(StringUtils.isBlank(tradingName)) { throw new DomainValidationException(PARTNER_FIELD_TRADINGNAME_CANNOT_BE_NULL);}
		if(StringUtils.isBlank(ownerName)) { throw new DomainValidationException(PARTNER_FIELD_OWNERNAME_CANNOT_BE_NULL);}
		if(StringUtils.isBlank(document)) { throw new DomainValidationException(PARTNER_FIELD_DOCUMENT_CANNOT_BE_NULL);}
		if(document.length() != 14) { throw new DomainValidationException(PARTNER_FIELD_DOCUMENT_MUST_CONTAINS_14_NUMBERS);}
		if(ObjectUtils.isEmpty(address)) { throw new DomainValidationException(PARTNER_OBJECT_ADDRESS_CANNOT_BE_NULL);}
		address.didMount();
		if(ObjectUtils.isEmpty(coverageArea)) { throw new DomainValidationException(PARTNER_OBJECT_COVERAGEAREA_CANNOT_BE_NULL);}
		coverageArea.didMount();
	}
	
}
