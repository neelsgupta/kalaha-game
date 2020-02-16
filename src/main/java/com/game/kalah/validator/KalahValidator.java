package com.game.kalah.validator;

import com.game.kalah.exception.InvalidIdException;

public interface KalahValidator {
	
	public void validate(String gameId, Integer pitId) throws InvalidIdException;

	public void validateGame(String gameId);
}
