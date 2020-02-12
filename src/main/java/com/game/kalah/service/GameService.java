package com.game.kalah.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.game.kalah.domain.Game;
import com.game.kalah.domain.GameStatus;
import com.game.kalah.domain.Player;
import com.game.kalah.exception.InvalidIdException;

@Service
public class GameService {

	public static final int FIRST_PIT_INDEX = 1;

	public static final int LAST_PIT_INDEX = 14;
	
	public static final int INITIAL_STONES_QUANTITY = 6;

	public void makeMove(Game game, int pitId) {
		validatePitNumber(pitId, game);

		Map<Integer, Integer> board = game.getScoreBoard();
		int amount = board.get(pitId);
		int lastIndex = pitId + amount;
		clearPit(pitId, game.getScoreBoard());

		int lastPit = lastIndex;
		for (int currentIndex = pitId + 1; currentIndex <= lastIndex; currentIndex++) {
			int currentPit = currentIndex;
			if (currentIndex == LAST_PIT_INDEX && lastIndex != LAST_PIT_INDEX) {
				lastIndex = lastIndex - currentIndex;
				currentIndex = 0;
			}
			if (game.getPlayer().getOppositePlayer().getKalahId() != currentPit) {
				addStonesToPit(currentPit, board, 1);
			} else {
				if (currentIndex != LAST_PIT_INDEX) {
					lastIndex++;
				} else {
					lastIndex = FIRST_PIT_INDEX;
					currentIndex = 0;
				}
			}
		}
		lastPit = lastPit > LAST_PIT_INDEX ? lastIndex : lastPit;
		checkLastPit(lastPit, game);

		if (!playerHasAnotherTurn(lastPit, game.getPlayer())) {
			game.setPlayer(game.getPlayer().getOppositePlayer());
		}
		if (gameIsTerminated(game)) {
			GameStatus winner = findTheWinner(game);
			game.setGameStatus(winner);
		}
	}

	/**
	 * Checks pit where the last stone landed. If the pit is own empty pit
	 * captures this stone and all stones in the opposite pit and puts them in
	 * own Kalah.
	 *
	 * @param lastPit
	 * @param game
	 */
	private void checkLastPit(int lastPit, Game game) {
		if (lastPitWasOwnEmptyPit(lastPit, game)) {
			int oppositePit = getOppositePit(lastPit);
			int oppositePitAmount = game.getScoreBoard().get(oppositePit);
			if (oppositePitAmount != 0) {
				clearPit(oppositePit, game.getScoreBoard());
				clearPit(lastPit, game.getScoreBoard());
				addStonesToPit(game.getPlayer().getKalahId(), game.getScoreBoard(), oppositePitAmount + 1);
			}
		}
	}

	/**
	 * If the game is terminated adds all remained stones to Kalah of each
	 * player.
	 *
	 * @param game
	 * @return true if the game is terminated.
	 */
	private boolean gameIsTerminated(Game game) {
		Player player = game.getPlayer();
		List<Integer> pits = player.getPits();
		Map<Integer, Integer> board = game.getScoreBoard();

		boolean playerPitsAreEmpty = pits.stream().map(board::get).allMatch(stoneNumbers -> stoneNumbers == 0);

		boolean oppositePlayerPitsAreEmpty = player.getOppositePlayer().getPits().stream().map(board::get)
				.allMatch(stoneNumbers -> stoneNumbers == 0);

		if (playerPitsAreEmpty || oppositePlayerPitsAreEmpty) {
			addAllRemainedStonesToKalah(player, board);
			addAllRemainedStonesToKalah(player.getOppositePlayer(), board);
			return true;
		}
		return false;
	}

	private void addAllRemainedStonesToKalah(Player player, Map<Integer, Integer> board) {
		player.getPits().forEach(pit -> {
			int amount = board.get(pit);
			if (amount != 0) {
				int kalahId = player.getKalahId();
				board.replace(kalahId, board.get(kalahId) + amount);
				clearPit(pit, board);
			}
		});
	}

	private GameStatus findTheWinner(Game game) {
		Map<Integer, Integer> board = game.getScoreBoard();
		int firstPlayerStones = board.get(Player.FIRST_PLAYER.getKalahId());
		int secondPlayerStones = board.get(Player.SECOND_PLAYER.getKalahId());
		if (firstPlayerStones > secondPlayerStones) {
			return GameStatus.FIRST_PLAYER_WON;
		} else if (firstPlayerStones < secondPlayerStones) {
			return GameStatus.SECOND_PLAYER_WON;
		} else {
			return GameStatus.DRAW;
		}
	}

	private void validatePitNumber(int pitId, Game game) {
		Player player = game.getPlayer();
		/*
		 * if (pitId == player.getKalahId() || pitId ==
		 * player.getOppositePlayer().getKalahId()) { throw new
		 * InvalidIdException("You can not select Kalah Home!"); }
		 */

		/*
		 * if (pitId < FIRST_PIT_INDEX || pitId > LAST_PIT_INDEX) {
		 * System.out.println("Provided pitId is out of bounds..."); throw new
		 * InvalidIdException("Provided pitId is out of bounds..."); }
		 */

		if (!isUserPit(pitId, player)) {
			throw new InvalidIdException(String.valueOf(pitId), "It is not your turn!");
		}
		if (game.getScoreBoard().get(pitId) == 0) {
			throw new InvalidIdException(String.valueOf(pitId), "You can not select empty pit!");
		}
	}

	private boolean lastPitWasOwnEmptyPit(int lastPitId, Game game) {
		Map<Integer, Integer> board = game.getScoreBoard();
		return board.get(lastPitId) == 1 && isUserPit(lastPitId, game.getPlayer());
	}

	private boolean isUserPit(int pitId, Player player) {
		return player.getPits().contains(pitId);
	}

	private int getOppositePit(int pitId) {
		return LAST_PIT_INDEX - pitId;
	}

	private boolean playerHasAnotherTurn(int lastPitId, Player player) {
		return player.getKalahId() == lastPitId;
	}

	private void addStonesToPit(int pitId, Map<Integer, Integer> board, int amount) {
		board.replace(pitId, board.get(pitId) + amount);
	}

	private void clearPit(int pitId, Map<Integer, Integer> board) {
		board.replace(pitId, 0);
	}

}
