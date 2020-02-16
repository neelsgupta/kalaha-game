package com.game.kalah.validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.game.kalah.exception.InvalidIdException;

@RunWith(MockitoJUnitRunner.class)
public class KalahValidatorTest {

	@InjectMocks
	private KalahValidatorImpl kalahValidator;

	private String gameId = "anyGameId";
	private Integer pitId = 1;

	@Test
	public void testValidation() throws Exception {
		// kalahValidator.validate(Mockito.anyString(), Mockito.anyString());
	}

	@Test(expected = InvalidIdException.class)
	public void testValidationThrowInvalidIdExceptionIfInvalidGameId() {
		kalahValidator.validate(gameId, pitId);
	}

	// @Test(expected = InvalidIdException.class)
	public void testValidationThrowInvalidIdExceptionIfInvalidPitId() throws InvalidIdException {
		// kalahValidator.validate(Mockito.anyString(), Mockito.anyInt());
	}

}
