package com.game.kalah.exception;

import com.game.kalah.domain.GameStatus;

public class GameEndedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private GameStatus gameStatus;
	
	private String gameId;

	public GameEndedException(String gameId, String message, GameStatus gameStatus) {
		super(message);
		this.gameId = gameId;
		this.gameStatus = gameStatus;
	}

	public GameStatus getGameStatus() {
		return gameStatus;
	}
	
	public String getGameId() {
		return gameId;
	}
}
