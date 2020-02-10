package com.game.kalah.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.game.kalah.dto.Kalah;
import com.game.kalah.mapper.KalahMapper;

@Repository
public class KalahRepositoryImpl implements KalahRepository {

	@Autowired
	KalahMapper kalahMapper;

	public static Map<String, Kalah> game = new HashMap<>();

	public Kalah create() {
		Kalah kalah = kalahMapper.mapToKalah();
		game.put(kalah.getId(), kalah);
		return kalah;
	}

	public Kalah play(String gameId, String pitId) {
		Kalah kalah = game.get(gameId);
		int[] previousStatus = kalah.getStatus();
		int[] updatedStatus = KalahGame.play(Integer.parseInt(pitId), previousStatus);
		kalah.setStatus(updatedStatus);
		game.put(gameId, kalah);
		return kalah;
	}

}
