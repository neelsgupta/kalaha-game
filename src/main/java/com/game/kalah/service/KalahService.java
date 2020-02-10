package com.game.kalah.service;

import com.game.kalah.dto.Kalah;

public interface KalahService {

	public Kalah createGame();

	public Kalah playGame(String gameId, String pitId);

}
