package com.krizzscott.zedeliverychallenge.ut.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

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
import com.krizzscott.zedeliverychallenge.exceptions.notfound.NotFoundException;
import com.krizzscott.zedeliverychallenge.exceptions.notfound.usecase.PartnerByIdNotFoundException;
import com.krizzscott.zedeliverychallenge.gateway.database.converters.PartnerEntityConverter;
import com.krizzscott.zedeliverychallenge.gateway.database.entities.PartnerEntity;
import com.krizzscott.zedeliverychallenge.gateway.database.repositories.PartnerRepository;
import com.krizzscott.zedeliverychallenge.usecases.FindPartnerByIdUseCase;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

@ExtendWith(SpringExtension.class)
public class FindPartnerByIdUseCaseUnitTests {

	@InjectMocks
	private FindPartnerByIdUseCase useCase;

	@Mock
	private PartnerRepository partnerRepository;
	
	@BeforeAll
	public static void setUp() {
	    FixtureFactoryLoader.loadTemplates("com.krizzscott.zedeliverychallenge.fixtures");
	}

	@Test
	public void shouldThrowDomainValidationExceptionWhenPartnerIdIsNull() {
		// GIVEN
		final String partnerId = null;

		// WHEN
		BadRequestException exception = assertThrows(DomainValidationException.class, () -> {
			useCase.execute(partnerId);
		});

		// THEN
		assertEquals("Parameter [partnerId] cannot be null", exception.getMessage());
		assertEquals(400001, exception.getErrorCode());

		verifyNoInteractions(partnerRepository);

	}
	
	@Test
	public void shouldThrowPartnerNotFoundWhenPartnerIdNotFound() {
		
		// GIVEN
		final String partnerId = UUID.randomUUID().toString();

		when(partnerRepository.findById(anyString())).thenReturn(Optional.empty());

		// WHEN
		NotFoundException exception = assertThrows(PartnerByIdNotFoundException.class, () -> {
			useCase.execute(partnerId);
		});
		
		// THEN	
		assertEquals("Partner not found by ID", exception.getMessage());
		assertEquals(404001, exception.getErrorCode());
		
		verify(partnerRepository, times(1)).findById(anyString());
		verifyNoMoreInteractions(partnerRepository);
		
		InOrder inOrder = inOrder(partnerRepository);
		inOrder.verify(partnerRepository).findById(anyString());	
		
	}
	
	@Test
	public void shouldReturnPartnerFoundSuccess() {
		
		// GIVEN
		final String partnerId = UUID.randomUUID().toString();
		Partner partnerExpected = Fixture.from(Partner.class).gimme("create-partner-valid-with-id");
		PartnerEntity partnerEntityCreated = PartnerEntityConverter.toEntity(partnerExpected);

		when(partnerRepository.findById(anyString())).thenReturn(Optional.of(partnerEntityCreated));
		
		// WHEN
		Partner actualPartner = useCase.execute(partnerId);
		
		// THEN	
		assertEquals(partnerExpected, actualPartner);
		
		verify(partnerRepository, times(1)).findById(anyString());
		verifyNoMoreInteractions(partnerRepository);
		
		InOrder inOrder = inOrder(partnerRepository);
		inOrder.verify(partnerRepository).findById(anyString());	
		
	}

}
