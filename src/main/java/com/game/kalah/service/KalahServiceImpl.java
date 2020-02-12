package com.game.kalah.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.kalah.domain.Game;
import com.game.kalah.mapper.KalahMapper;
import com.game.kalah.repository.KalahRepository;

@Service
public class KalahServiceImpl implements KalahService {

	@Autowired
	public KalahRepository repository;

	@Autowired
	public KalahMapper kalahMapper;

	@Autowired
	public GameFacade facade;

	public Game create() {
		Game game = kalahMapper.createGame();
		repository.create(game);
		return game;
	}
	
	public Game play(String gameId, String pitId) throws Exception {
		Game game = repository.get(gameId);
		facade.makeMove(game, Integer.parseInt(pitId));
		repository.save(gameId, game);
		return game;
	}

}
