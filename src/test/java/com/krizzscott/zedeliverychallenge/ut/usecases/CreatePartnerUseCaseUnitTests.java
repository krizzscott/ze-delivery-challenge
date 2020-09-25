package com.krizzscott.zedeliverychallenge.ut.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.krizzscott.zedeliverychallenge.domains.Partner;
import com.krizzscott.zedeliverychallenge.exceptions.badrequest.BadRequestException;
import com.krizzscott.zedeliverychallenge.exceptions.badrequest.domain.DomainValidationException;
import com.krizzscott.zedeliverychallenge.exceptions.badrequest.usecase.PartnerDocumentAlreadyExistsException;
import com.krizzscott.zedeliverychallenge.gateway.database.converters.PartnerEntityConverter;
import com.krizzscott.zedeliverychallenge.gateway.database.entities.PartnerEntity;
import com.krizzscott.zedeliverychallenge.gateway.database.repositories.PartnerRepository;
import com.krizzscott.zedeliverychallenge.usecases.CreatePartnerUseCase;
import com.krizzscott.zedeliverychallenge.usecases.ExistsPartnerWithTheSameDocumentNumberUseCase;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;


@ExtendWith(SpringExtension.class)
public class CreatePartnerUseCaseUnitTests {
	
	@InjectMocks
	private CreatePartnerUseCase useCase;

	@Mock
	private ExistsPartnerWithTheSameDocumentNumberUseCase existsPartnerWithTheSameDocumentNumberUseCase;
	
	@Mock
	private PartnerRepository partnerRepository;

	
	@BeforeAll
	public static void setUp() {
	    FixtureFactoryLoader.loadTemplates("com.krizzscott.zedeliverychallenge.fixtures");
	}
	
	@Test
	public void shouldThrowDomainValidationExceptionWhenPartnerDomainIsNull() {
		
		//GIVEN
		Partner partner = null;
		
		//WHEN
		BadRequestException exception = assertThrows(DomainValidationException.class, () -> {
			useCase.execute(partner);
	    });
		
		//THEN
		assertEquals("Domain Object [partner] cannot be null", exception.getMessage());
		assertEquals(400001, exception.getErrorCode());
		
		verifyNoInteractions(existsPartnerWithTheSameDocumentNumberUseCase);
		verifyNoInteractions(partnerRepository);

	}
	
	@Test
	public void shouldThrowDomainValidationExceptionWhenPartnerTradingNameIsNull() {
		
		//GIVEN
		Partner partner = Fixture.from(Partner.class).gimme("create-partner-invalid_tradingname");
		
		//WHEN
		BadRequestException exception = assertThrows(DomainValidationException.class, () -> {
			useCase.execute(partner);
		});
		
		//THEN
		assertEquals("Parameter [tradingName] cannot be null or empty", exception.getMessage());
		assertEquals(400001, exception.getErrorCode());

		verifyNoInteractions(existsPartnerWithTheSameDocumentNumberUseCase);
		verifyNoInteractions(partnerRepository);
		
	}
	
	@Test
	public void shouldThrowDomainValidationExceptionWhenPartnerOwnerNameIsNull() {
		
		//GIVEN
		Partner partner = Fixture.from(Partner.class).gimme("create-partner-invalid_ownername");
		
		//WHEN
		BadRequestException exception = assertThrows(DomainValidationException.class, () -> {
			useCase.execute(partner);
		});
		
		//THEN
		assertEquals("Parameter [ownerName] cannot be null or empty", exception.getMessage());
		assertEquals(400001, exception.getErrorCode());
		
		verifyNoInteractions(existsPartnerWithTheSameDocumentNumberUseCase);
		verifyNoInteractions(partnerRepository);
		
	}
	
	@Test
	public void shouldThrowDomainValidationExceptionWhenPartnerDocumentIsNull() {
		
		//GIVEN
		Partner partner = Fixture.from(Partner.class).gimme("create-partner-invalid_document");
		
		//WHEN
		BadRequestException exception = assertThrows(DomainValidationException.class, () -> {
			useCase.execute(partner);
		});
		
		//THEN
		assertEquals("Parameter [document] cannot be null or empty", exception.getMessage());
		assertEquals(400001, exception.getErrorCode());
		
		verifyNoInteractions(existsPartnerWithTheSameDocumentNumberUseCase);
		verifyNoInteractions(partnerRepository);
		
	}
	
	@Test
	public void shouldThrowDomainValidationExceptionWhenPartnerAddressIsNull() {
		
		//GIVEN
		Partner partner = Fixture.from(Partner.class).gimme("create-partner-null_address");
		
		//WHEN
		BadRequestException exception = assertThrows(DomainValidationException.class, () -> {
			useCase.execute(partner);
		});
		
		//THEN
		assertEquals("Parameter [address] cannot be null", exception.getMessage());
		assertEquals(400001, exception.getErrorCode());
		
		verifyNoInteractions(existsPartnerWithTheSameDocumentNumberUseCase);
		verifyNoInteractions(partnerRepository);
		
	}
	
	@Test
	public void shouldThrowDomainValidationExceptionWhenPartnerAddressCoordinatesIsNull() {
		
		//GIVEN
		Partner partner = Fixture.from(Partner.class).gimme("create-partner-null_coordinates_address");
		
		//WHEN
		BadRequestException exception = assertThrows(DomainValidationException.class, () -> {
			useCase.execute(partner);
		});
		
		//THEN
		assertEquals("Parameter [geopoint.coordinates] cannot be null or empty", exception.getMessage());
		assertEquals(400001, exception.getErrorCode());
		
		verifyNoInteractions(existsPartnerWithTheSameDocumentNumberUseCase);
		verifyNoInteractions(partnerRepository);
		
	}

	@Test
	public void shouldThrowDomainValidationExceptionWhenPartnerAddressCoordinatesIsEmpty() {
		
		//GIVEN
		Partner partner = Fixture.from(Partner.class).gimme("create-partner-empty_coordinates_address");
		
		//WHEN
		BadRequestException exception = assertThrows(DomainValidationException.class, () -> {
			useCase.execute(partner);
		});
		
		//THEN
		assertEquals("Parameter [geopoint.coordinates] cannot be null or empty", exception.getMessage());
		assertEquals(400001, exception.getErrorCode());
		
		verifyNoInteractions(existsPartnerWithTheSameDocumentNumberUseCase);
		verifyNoInteractions(partnerRepository);
		
	}
	
	@Test
	public void shouldThrowDomainValidationExceptionWhenPartnerCoverageAreaIsNull() {
		
		//GIVEN
		Partner partner = Fixture.from(Partner.class).gimme("create-partner-null_coveragearea");
		
		//WHEN
		BadRequestException exception = assertThrows(DomainValidationException.class, () -> {
			useCase.execute(partner);
		});
		
		//THEN
		assertEquals("Parameter [coverageArea] cannot be null", exception.getMessage());
		assertEquals(400001, exception.getErrorCode());
		
		verifyNoInteractions(existsPartnerWithTheSameDocumentNumberUseCase);
		verifyNoInteractions(partnerRepository);
		
	}
	
	@Test
	public void shouldThrowDomainValidationExceptionWhenPartnerCoverageAreaCoordinatesIsNull() {
		
		//GIVEN
		Partner partner = Fixture.from(Partner.class).gimme("create-partner-null_coordinates_coveragearea");
		
		//WHEN
		BadRequestException exception = assertThrows(DomainValidationException.class, () -> {
			useCase.execute(partner);
		});
		
		//THEN
		assertEquals("Parameter [geomultipolygon.coordinates] cannot be null or empty", exception.getMessage());
		assertEquals(400001, exception.getErrorCode());
		
		verifyNoInteractions(existsPartnerWithTheSameDocumentNumberUseCase);
		verifyNoInteractions(partnerRepository);
		
	}

	@Test
	public void shouldThrowDomainValidationExceptionWhenPartnerCoverageAreaCoordinatesIsEmpty() {
		
		//GIVEN
		Partner partner = Fixture.from(Partner.class).gimme("create-partner-empty_coordinates_coveragearea");
		
		//WHEN
		BadRequestException exception = assertThrows(DomainValidationException.class, () -> {
			useCase.execute(partner);
		});
		
		//THEN
		assertEquals("Parameter [geomultipolygon.coordinates] cannot be null or empty", exception.getMessage());
		assertEquals(400001, exception.getErrorCode());
		
		verifyNoInteractions(existsPartnerWithTheSameDocumentNumberUseCase);
		verifyNoInteractions(partnerRepository);
		
	}
	
	@Test
	public void shouldThrowWhenExistsEqualDocumentSavedDatabase() {
		
		//GIVEN
		Partner partner = Fixture.from(Partner.class).gimme("create-partner-valid");
		
		when(existsPartnerWithTheSameDocumentNumberUseCase.execute(anyString())).thenReturn(true);
		
		//WHEN
		BadRequestException exception = assertThrows(PartnerDocumentAlreadyExistsException.class, () -> {
			useCase.execute(partner);
		});
		//THEN
		
		assertEquals("Document already exists in this collection", exception.getMessage());
		assertEquals(400100, exception.getErrorCode());

		verify(existsPartnerWithTheSameDocumentNumberUseCase, times(1)).execute(anyString());
		verifyNoMoreInteractions(existsPartnerWithTheSameDocumentNumberUseCase);
		verifyNoInteractions(partnerRepository);
		
		InOrder inOrder = inOrder(existsPartnerWithTheSameDocumentNumberUseCase);
		inOrder.verify(existsPartnerWithTheSameDocumentNumberUseCase).execute(anyString());
		
	}
	
	@Test
	public void shouldBeCreatedPartnerWithSuccess() {
		
		//GIVEN
		Partner partner = Fixture.from(Partner.class).gimme("create-partner-valid");
		Partner partnerExpected = Fixture.from(Partner.class).gimme("create-partner-valid-with-id");
		PartnerEntity partnerEntityCreated = PartnerEntityConverter.toEntity(partnerExpected);
		
		when(existsPartnerWithTheSameDocumentNumberUseCase.execute(anyString())).thenReturn(false);
		when(partnerRepository.save(any(PartnerEntity.class))).thenReturn(partnerEntityCreated);
		
		//WHEN
		Partner actualPartner = useCase.execute(partner);

		//THEN
		assertEquals(partnerExpected, actualPartner);
		
		verify(existsPartnerWithTheSameDocumentNumberUseCase, times(1)).execute(anyString());
		verify(partnerRepository, times(1)).save(any(PartnerEntity.class));
		verifyNoMoreInteractions(existsPartnerWithTheSameDocumentNumberUseCase, partnerRepository);
		
		InOrder inOrder = inOrder(existsPartnerWithTheSameDocumentNumberUseCase, partnerRepository);
		inOrder.verify(existsPartnerWithTheSameDocumentNumberUseCase).execute(anyString());
		inOrder.verify(partnerRepository, times(1)).save(any(PartnerEntity.class));
		
	}
	

}
