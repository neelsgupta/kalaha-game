package com.game.kalah.repository;

import com.game.kalah.domain.Game;

public interface KalahRepository {

	public void create(Game game);

	public Game get(String gameId) throws Exception;

	public void save(String gameId, Game game);

}
