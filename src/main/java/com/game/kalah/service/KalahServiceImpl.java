package com.game.kalah.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.kalah.dto.Kalah;
import com.game.kalah.repository.KalahRepository;

@Service
public class KalahServiceImpl implements KalahService {

	@Autowired
	public KalahRepository repository;

	public Kalah createGame() {
		Kalah kalah = repository.create();
		return kalah;
	}	

	public Kalah playGame(String gameId, String pitId) {
		Kalah kalah = repository.play(gameId, pitId);
		return kalah;
	}
}
