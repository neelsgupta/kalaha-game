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

	private Integer validPitId = 1;
	private Integer pitIdOutOfBound = 25;
	private Integer firstPlayerHomePitId = 7;
	private Integer secondPlayerHomePitId = 14;

	@Test
	public void testValidationForValidPitId() {
		kalahValidator.validatePitId(validPitId);
	}

	@Test(expected = InvalidIdException.class)
	public void testValidationThrowInvalidIdExceptionIfInvalidPitId() throws InvalidIdException {
		kalahValidator.validatePitId(pitIdOutOfBound);
	}

	@Test(expected = InvalidIdException.class)
	public void testValidationThrowInvalidIdExceptionIfPitIdIsFirstPlayerHome() throws InvalidIdException {
		kalahValidator.validatePitId(firstPlayerHomePitId);
	}

	@Test(expected = InvalidIdException.class)
	public void testValidationThrowInvalidIdExceptionIfPitIdIsSecondPlayerHome() throws InvalidIdException {
		kalahValidator.validatePitId(secondPlayerHomePitId);
	}

}
