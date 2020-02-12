package com.game.kalah.service;

import com.game.kalah.domain.Game;

public interface KalahService {

	public Game create();

	public Game play(String gameId, Integer pitId) throws Exception;

}
