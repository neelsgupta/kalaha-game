package com.game.kalah.mapper;

import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.game.kalah.domain.Game;
import com.game.kalah.domain.GameStatus;
import com.game.kalah.domain.Player;
import com.game.kalah.dto.KalahInitResponse;
import com.game.kalah.dto.KalahMovedResponse;
import com.game.kalah.repository.KalahGame;

@Component
@ConfigurationProperties
public class KalahMapper {

	@Value("${server.port}")
	private String port;

	public Game createGame() {
		Game game = new Game();
		Random rand = new Random();
		String gameId = String.format("%04d", rand.nextInt(10000));
		game.setId(gameId);
		String uri = "http://localhost:" + port + "/games/";
		game.setUri(uri + gameId);
		game.setScoreBoard(KalahGame.setInitScoreBoard());
		game.setGameStatus(GameStatus.IN_PROGRESS);
		game.setPlayer(Player.FIRST_PLAYER);
		return game;
	}

	public KalahInitResponse mapToIntiDto(Game game) {
		KalahInitResponse kalahInit = new KalahInitResponse();
		kalahInit.setId(game.getId());
		kalahInit.setUri(game.getUri());
		return kalahInit;
	}

	public KalahMovedResponse mapToMovedDto(Game game) {
		KalahMovedResponse response = new KalahMovedResponse();
		response.setId(game.getId());
		response.setUri(game.getUri());
		response.setScore(game.getScoreBoard());
		response.setGameStatus(game.getGameStatus());
		response.setNextPlayer(String.valueOf(game.getPlayer().getPlayerId()));
		return response;
	}

}
