package com.krizzscott.zedeliverychallenge.ut.usecases;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.ObjectUtils;

import com.krizzscott.zedeliverychallenge.domains.Partner;
import com.krizzscott.zedeliverychallenge.exceptions.notfound.NotFoundException;
import com.krizzscott.zedeliverychallenge.exceptions.notfound.usecase.PartnerByGeoLocationNotFoundException;
import com.krizzscott.zedeliverychallenge.gateway.database.converters.PartnerEntityConverter;
import com.krizzscott.zedeliverychallenge.gateway.database.entities.PartnerEntity;
import com.krizzscott.zedeliverychallenge.gateway.database.repositories.PartnerCriteriaRepository;
import com.krizzscott.zedeliverychallenge.usecases.FindByNearGeoLocationUseCase;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

@ExtendWith(SpringExtension.class)
public class FindByNearGeoLocationUnitTests {

	@InjectMocks
	private FindByNearGeoLocationUseCase useCase;

	@Mock
	private PartnerCriteriaRepository partnerCriteriaRepository;
	
	@BeforeAll
	public static void setUp() {
	    FixtureFactoryLoader.loadTemplates("com.krizzscott.zedeliverychallenge.fixtures");
	}
	

	@Test
	public void shouldReturnListEmptyWhenNotFoundPartnerNearestToThePoint() {
		
		// GIVEN
		final double longitude = 1d;
		final double latitude = 2d;
		
		when(partnerCriteriaRepository.searchPartnerNearestAndInCoverageAreaByGeoPoint(anyDouble(), anyDouble()))
				.thenReturn(Optional.empty());

		// WHEN
		NotFoundException exception = assertThrows(PartnerByGeoLocationNotFoundException.class, () -> {
			useCase.execute(longitude, latitude);
		});
		
		// THEN
		assertEquals("Partner not found by Geolocation", exception.getMessage());
		assertEquals(404002, exception.getErrorCode());

		verify(partnerCriteriaRepository, times(1)).searchPartnerNearestAndInCoverageAreaByGeoPoint(anyDouble(), anyDouble());
		verifyNoMoreInteractions(partnerCriteriaRepository);
		
		InOrder inOrder = inOrder(partnerCriteriaRepository);
		inOrder.verify(partnerCriteriaRepository).searchPartnerNearestAndInCoverageAreaByGeoPoint(anyDouble(), anyDouble());	
		
	}

	@Test
	public void shouldReturnListWhenFoundPartnerNearestToThePoint() {
		
		// GIVEN
		final double longitude = 1d;
		final double latitude = 2d;

		//GIVEN
		Partner partnerExpected = Fixture.from(Partner.class).gimme("create-partner-valid-with-id");
		PartnerEntity partnerEntityCreated = PartnerEntityConverter.toEntity(partnerExpected);

		when(partnerCriteriaRepository.searchPartnerNearestAndInCoverageAreaByGeoPoint(anyDouble(), anyDouble()))
				.thenReturn(Optional.of(partnerEntityCreated));
		
		// WHEN
		Partner partner = useCase.execute(longitude, latitude);
		
		// THEN
		assertFalse(ObjectUtils.isEmpty(partner));
		
		verify(partnerCriteriaRepository, times(1)).searchPartnerNearestAndInCoverageAreaByGeoPoint(anyDouble(), anyDouble());
		verifyNoMoreInteractions(partnerCriteriaRepository);
		
		InOrder inOrder = inOrder(partnerCriteriaRepository);
		inOrder.verify(partnerCriteriaRepository).searchPartnerNearestAndInCoverageAreaByGeoPoint(anyDouble(), anyDouble());	
		
	}
	
}
