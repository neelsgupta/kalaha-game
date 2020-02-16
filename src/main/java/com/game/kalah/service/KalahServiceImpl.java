package com.game.kalah.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.kalah.domain.Game;
import com.game.kalah.domain.GameStatus;
import com.game.kalah.exception.GameEndedException;
import com.game.kalah.exception.InvalidIdException;
import com.game.kalah.mapper.KalahMapper;
import com.game.kalah.repository.KalahRepository;

@Service
public class KalahServiceImpl implements KalahService {

	@Autowired
	public KalahRepository repository;

	@Autowired
	public KalahMapper kalahMapper;

	@Autowired
	public GameService gameService;

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public Game create() {
		log.info("create game started");
		Game game = kalahMapper.createGame();
		repository.create(game);

		log.info("create game ended with gameId: " + game.getId());
		return game;
	}

	@Override
	public Game get(String gameId) throws InvalidIdException {
		log.info("get game started");
		Game game = repository.get(gameId);

		log.info("create get ended with gameId: " + game.getId());
		return game;
	}

	@Override
	public Game play(String gameId, Integer pitId) throws Exception {
		log.info("game play method started for gameId: " + gameId);
		Game game = repository.get(gameId);
		checkGameStatus(game);
		gameService.makeMove(game, pitId);
		repository.save(gameId, game);

		log.info("game play method ended for gameId: " + gameId);
		return game;
	}

	private void checkGameStatus(Game game) {
		GameStatus status = game.getGameStatus();
		if (status != GameStatus.IN_PROGRESS) {
			repository.delete(game.getId());
			log.info("Game already termianated for gameId: " + game.getId());
			throw new GameEndedException(game.getId(), "Game has been already terminated with status:" + status,
					status);
		}
	}

}
