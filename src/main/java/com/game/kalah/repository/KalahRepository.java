package com.game.kalah.repository;

import com.game.kalah.domain.Game;
import com.game.kalah.exception.InvalidIdException;

public interface KalahRepository {

	public void create(Game game);

	public Game get(String gameId) throws InvalidIdException;

	public void save(String gameId, Game game);

	public void delete(String gameId);

}
