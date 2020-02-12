package com.game.kalah.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.kalah.domain.Game;
import com.game.kalah.domain.GameStatus;
import com.game.kalah.exception.GameEndedException;
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

	public Game create() {
		Game game = kalahMapper.createGame();
		repository.create(game);
		return game;
	}

	public Game play(String gameId, Integer pitId) throws Exception {
		Game game = repository.get(gameId);
		checkGameStatus(game);
		gameService.makeMove(game, pitId);
		repository.save(gameId, game);
		return game;
	}
	
	private void checkGameStatus(Game game) {
        GameStatus status = game.getGameStatus();
        if (status != GameStatus.IN_PROGRESS) {
            throw new GameEndedException(game.getId(), "Game has been already terminated with status:" + status, status);
        }
    }

}
