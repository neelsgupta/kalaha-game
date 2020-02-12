/*package com.game.kalah.validator;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import com.game.kalah.domain.Kalah;
import com.game.kalah.validator.KalahValidator;

public class KalahValidatorTest {

	@InjectMocks
	private KalahValidator kalahValidator;

	Map<String, Kalah> game = new HashMap<>();

	@Before
	public void setUp() throws Exception {

		Kalah kalah = new Kalah();
		String gameId = "1234";
		String pitId = "4";
		kalah.setId(gameId);
		kalah.setUri(pitId);
		kalah.setStatus(Mockito.any());
		game.put(gameId, kalah);
	}

	@Test
	public void testValidate() {
		try {
			// kalahValidator.validate(Mockito.anyString(),
		} catch (Exception e) {
		}
	}

	@Test
	public void testValidationThrowInvalidIdExceptionIfInvalidGameId() throws Exception {
		// kalahValidator.validate(Mockito.anyString(), Mockito.anyString());
	}

	@Test
	public void testValidationThrowInvalidIdExceptionIfInvalidPitId() throws Exception {
		// kalahValidator.validate(Mockito.anyString(), Mockito.anyString());
	}

}
*/