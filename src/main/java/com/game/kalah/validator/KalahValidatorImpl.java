package com.game.kalah.validator;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.game.kalah.domain.Game;
import com.game.kalah.exception.InvalidIdException;
import com.game.kalah.repository.KalahRepositoryImpl;

@Component
public class KalahValidatorImpl implements KalahValidator {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	public void validate(String gameId, Integer pitId) throws Exception {
		log.info("validation for gameId and pitId started.");
		isValidGameId(gameId);
		isValidPitId(gameId, pitId);
		log.info("validation for gameId and pitId ended.");
	}

	private void isValidGameId(String gameId) throws Exception {
		Map<String, Game> game = KalahRepositoryImpl.gameRepo;
		if (game != null && !game.containsKey(gameId)) {
			log.error("Validation failed for gameId: "+gameId);
			throw new InvalidIdException(gameId, "Invalid gameId");
		}
	}

	private void isValidPitId(String gameId, int pitId) throws Exception {

		if (!(pitId > 0 && pitId < 14)) {
			log.error("Validation failed for pitId, entered pitId is out of bound. "+String.valueOf(pitId));
			throw new InvalidIdException(String.valueOf(pitId), "Entered pitId is out of valid range, please enter valid pitId");
		}

		if (!(pitId != 7 && pitId != 14)) {
			log.error("Validation failed for pitId, entered pitId is one of kalah home pitid: "+String.valueOf(pitId));
			throw new InvalidIdException(String.valueOf(pitId), "Invalid pitId, you cannot enter Home pitId");
		}
	}
}
