package com.game.kalah.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class Game {

	private String id;

	private Map<Integer, Integer> scoreBoard;

	private GameStatus gameStatus;

	private Player player;

	private String uri;
	
	private long lastUpdated;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Map<Integer, Integer> getScoreBoard() {
		if (scoreBoard == null) {
			new LinkedHashMap<>();
		}
		return scoreBoard;
	}

	public void setScoreBoard(Map<Integer, Integer> scoreBoard) {
		this.scoreBoard = scoreBoard;
	}

	public GameStatus getGameStatus() {
		return gameStatus;
	}

	public void setGameStatus(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Long getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Long lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

}
