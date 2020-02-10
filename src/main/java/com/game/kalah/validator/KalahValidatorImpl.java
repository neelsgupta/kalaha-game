package com.game.kalah.validator;

import java.util.Map;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.game.kalah.dto.Kalah;
import com.game.kalah.exception.InvalidIdException;
import com.game.kalah.repository.KalahRepositoryImpl;

@Component
public class KalahValidatorImpl implements KalahValidator {

	public void validate(String gameId, String pitId) throws Exception {
		isValidGameId(gameId);
		isValidPitId(gameId,Integer.parseInt(pitId));
	}
	
	private void isValidGameId(String gameId) throws Exception{
		 Map<String, Kalah> game = KalahRepositoryImpl.game;
		 if(game!=null && !game.containsKey(gameId)){
			 throw new InvalidIdException(gameId, "Invalid gameId");
		 }
	}

	private void isValidPitId(String gameId,int pitId) throws Exception {
		if(!(pitId > 0 && pitId < 14 && pitId!=7 )){
			throw new InvalidIdException(gameId, "Invalid pitId");
		}
	}
}
