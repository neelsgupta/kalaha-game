package com.game.kalah.repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import com.game.kalah.domain.Game;
import com.game.kalah.exception.InvalidIdException;

@Repository
public class KalahRepositoryImpl implements KalahRepository {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	public static final ConcurrentHashMap<String, Game> gameRepo = new ConcurrentHashMap<>();

	@Override
	public void create(Game game) {
		gameRepo.put(game.getId(), game);
	}

	@Override
	public Game get(String gameId) throws InvalidIdException {
		if (!gameRepo.containsKey(gameId)) {
			log.error("Game not found in game repo, invalid gameId: " + gameId);
			throw new InvalidIdException(gameId, "Invalid gameId");
		}
		return gameRepo.get(gameId);
	}

	@Override
	public void save(String gameId, Game game) {
		gameRepo.put(gameId, game);
	}

	@Override
	public void delete(String gameId) {
		if (!gameRepo.containsKey(gameId)) {
			log.error("Game not found in game repo, invalid gameId: " + gameId);
			throw new InvalidIdException(gameId, "Invalid gameId");
		}
		gameRepo.remove(gameId);

	}

	@Scheduled(fixedRate = 300000)
	public void deleteOldGame() {
		log.debug("size of the map {}", gameRepo.size());

		for (Map.Entry<String, Game> entry : gameRepo.entrySet()) {
			long diff = (System.currentTimeMillis() - entry.getValue().getLastUpdated());
			long diffMinutes = diff / (60 * 1000) % 60;
			if (diffMinutes > 60) {
				gameRepo.remove(entry.getKey());
			}
		}
	}

}
