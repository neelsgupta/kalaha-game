package com.game.kalah.repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.game.kalah.domain.Game;
import com.game.kalah.exception.InvalidIdException;
import com.game.kalah.mapper.KalahMapper;

@Repository
public class KalahRepositoryImpl implements KalahRepository {

	@Autowired
	KalahMapper kalahMapper;

	public static Map<String, Game> newGame = new ConcurrentHashMap<>();

	@Override
	public void create(Game game) {
		newGame.put(game.getId(), game);
	}

	@Override
	public Game get(String gameId) throws Exception {
		Game game = newGame.get(gameId);
		if (game == null) {
			throw new InvalidIdException(gameId, "Invalid gameId");
		}
		return game;
	}

	@Override
	public void save(String gameId, Game game) {
		newGame.put(gameId, game);	
	}

}
