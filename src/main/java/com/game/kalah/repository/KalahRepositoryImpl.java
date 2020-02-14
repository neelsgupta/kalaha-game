package com.game.kalah.repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.game.kalah.domain.Game;
import com.game.kalah.exception.InvalidIdException;
import com.game.kalah.mapper.KalahMapper;

@Repository
public class KalahRepositoryImpl implements KalahRepository {

	@Autowired
	KalahMapper kalahMapper;

	private Logger log = LoggerFactory.getLogger(this.getClass());

	public static Map<String, Game> gameRepo = new ConcurrentHashMap<>();

	@Override
	public void create(Game game) {
		// TODO can put null check for gameId
		gameRepo.put(game.getId(), game);
	}

	@Override
	public Game get(String gameId) throws Exception {
		if (!gameRepo.containsKey(gameId)) {
			log.error("Game not found in game repo, invalid gameId: " + gameId);
			throw new InvalidIdException(gameId, "Invalid gameId");
		}
		return gameRepo.get(gameId);
	}

	@Override
	public void save(String gameId, Game game) {
		// TODO can put null check for gameId
		gameRepo.put(gameId, game);
	}

}
