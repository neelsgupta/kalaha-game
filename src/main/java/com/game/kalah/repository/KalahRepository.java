package com.game.kalah.repository;

import com.game.kalah.dto.Kalah;

public interface KalahRepository {

	public Kalah create();

	public Kalah play(String gameId, String pitId);
}
