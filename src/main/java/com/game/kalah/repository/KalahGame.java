package com.game.kalah.repository;

import com.game.kalah.exception.InvalidIdException;

public class KalahGame {

	/**
	 * 
	 * @param pit
	 * @param status
	 * @return
	 * @throws InvalidIdException
	 */
	public static int[] play(int pit, int status[]) {

		int stones = status[pit-1];
		int currentPit = pit;

		if (!winnerCheck(getPlayerId(currentPit), status)) {

			status[currentPit - 1] = 0;
			for (int i = 0; i < stones; i++) {
				if (currentPit == 14) {
					currentPit = 0;
				}
				if (currentPit != 13 && getPlayerId(pit) == 01) {
					status[currentPit]++;
					//System.out.println("Player 01 : ");
				} else if (currentPit != 6 && getPlayerId(pit) == 02) {
					status[currentPit]++;
					//System.out.println("Player 02 : ");
				} else {
					i--;
				}
				currentPit++;
			}
		} else {
			System.out.println("Game Over...Winner is " + getPlayerId(pit));
		}
		if (status[currentPit - 1] == 1) {
			pullfromOppositePit(currentPit, getPlayerId(pit), status);
		}

		return status;
	}

	public static int[] initGame() {

		int status[] = new int[14];
		for (int i = 0; i < status.length; i++) {
			if (i != 6 || i != 13) {
				status[i] = 6;
			}
		}
		return status;
	}

	private static int getPlayerId(int pit) {

		if (pit >= 0 && pit <= 6) {
			return 01;
		}
		return 02;
	}

	private static void pullfromOppositePit(int lastPit, int playerId, int[] status) {

		if (playerId == 01 && lastPit >= 0 && lastPit <= 5) {
			status[(status.length / 2) - 1] += status[status.length - lastPit - 2] + 1;
		} else if ((playerId == 02 && lastPit >= 7 && lastPit <= 12)) {
			status[status.length - 1] += status[status.length - lastPit - 2] + 1;
		}
		status[lastPit] = 0;
	}

	private static boolean winnerCheck(int playerId, int status[]) {

		boolean isWinner = true;
		if (playerId == 01) {
			for (int i = 0; i <= 6; i++) {
				if (status[i] != 0) {
					isWinner = false;
				}
			}
		} else {
			for (int i = 7; i <= 12; i++) {
				if (status[i] != 0) {
					isWinner = false;
				}
			}
		}

		if (isWinner == true) {
			return true;
		}
		return false;
	}

}
