package com.game.kalah.validator;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.game.kalah.domain.Game;
import com.game.kalah.exception.InvalidIdException;
import com.game.kalah.repository.KalahRepositoryImpl;

@Component
public class KalahValidatorImpl implements KalahValidator {

	public void validate(String gameId, Integer pitId) throws Exception {
		isValidGameId(gameId);
		isValidPitId(gameId, pitId);
	}

	private void isValidGameId(String gameId) throws Exception {
		Map<String, Game> game = KalahRepositoryImpl.gameRepo;
		if (game != null && !game.containsKey(gameId)) {
			throw new InvalidIdException(gameId, "Invalid gameId");
		}
	}

	private void isValidPitId(String gameId, int pitId) throws Exception {

		if (!(pitId > 0 && pitId < 14)) {
			throw new InvalidIdException(String.valueOf(pitId), "Invalid pitId, Please enter valid pitId");
		}

		if (!(pitId != 7 && pitId != 14)) {
			throw new InvalidIdException(String.valueOf(pitId), "Invalid pitId, You cannot enter Home pitId");
		}
	}
}
