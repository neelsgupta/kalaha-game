package com.game.kalah.repository;

import java.util.LinkedHashMap;
import java.util.Map;

public class KalahGame {

	public static Map<Integer, Integer> setInitScoreBoard() {

		Map<Integer, Integer> scoreBoard = new LinkedHashMap<>();
		for (int i = 1; i <= 14; i++) {
			int value = (i != 7 && i != 14) ? 6 : 0;
			scoreBoard.put(i, value);
		}
		return scoreBoard;
	}	

}
