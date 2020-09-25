package com.krizzscott.zedeliverychallenge.ut.usecases;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.krizzscott.zedeliverychallenge.exceptions.badrequest.BadRequestException;
import com.krizzscott.zedeliverychallenge.exceptions.badrequest.domain.DomainValidationException;
import com.krizzscott.zedeliverychallenge.gateway.database.repositories.PartnerRepository;
import com.krizzscott.zedeliverychallenge.usecases.ExistsPartnerWithTheSameDocumentNumberUseCase;

@ExtendWith(SpringExtension.class)
class ExistsPartnerWithTheSameDocumentNumberUseCaseUnitTests {

	@InjectMocks
	private ExistsPartnerWithTheSameDocumentNumberUseCase useCase;

	@Mock
	private PartnerRepository partnerRepository;
	

	@Test
	void shouldThrowDomainExceptionWhenDocumentParamIsNull() {
		
		// GIVEN
		final String document = null;

		// WHEN
		BadRequestException exception = assertThrows(DomainValidationException.class, () -> {
			useCase.execute(document);
		});
		
		// THEN
		assertEquals("Parameter [document] cannot be null", exception.getMessage());
		assertEquals(400001, exception.getErrorCode());

		verifyNoInteractions(partnerRepository);

	}
	@Test
	void shouldReturnFalseWhenDocumentNotFound() {
		
		// GIVEN
		final String document = UUID.randomUUID().toString();
		
		when(partnerRepository.existsByDocument(anyString())).thenReturn(Boolean.FALSE);
		
		// WHEN
		boolean documentHasFound = useCase.execute(document);
		
		// THEN
		assertFalse(documentHasFound);
		
		verify(partnerRepository, times(1)).existsByDocument(anyString());
		verifyNoMoreInteractions(partnerRepository);
		
		InOrder inOrder = inOrder(partnerRepository);
		inOrder.verify(partnerRepository).existsByDocument(anyString());		
	}
	
	@Test
	void shouldReturnTrueWhenDocumentFound() {
		
		// GIVEN
		final String document = UUID.randomUUID().toString();

		when(partnerRepository.existsByDocument(anyString())).thenReturn(Boolean.TRUE);

		// WHEN
		boolean documentHasFound = useCase.execute(document);
		
		// THEN	
		assertTrue(documentHasFound);
		
		verify(partnerRepository, times(1)).existsByDocument(anyString());
		verifyNoMoreInteractions(partnerRepository);
		
		InOrder inOrder = inOrder(partnerRepository);
		inOrder.verify(partnerRepository).existsByDocument(anyString());	
			
	}
	
}
