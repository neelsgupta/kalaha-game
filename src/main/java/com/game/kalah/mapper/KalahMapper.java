package com.game.kalah.mapper;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private Logger log = LoggerFactory.getLogger(this.getClass());

	public Game createGame() {
		log.info("createGame method started.");
		Game game = new Game();
		Random rand = new Random();
		String gameId = String.format("%04d", rand.nextInt(10000));
		game.setId(gameId);
		String uri = "http://localhost:" + port + "/games/";
		game.setUri(uri + gameId);
		game.setScoreBoard(KalahGame.setInitScoreBoard());
		game.setGameStatus(GameStatus.IN_PROGRESS);
		game.setPlayer(Player.FIRST_PLAYER);
		log.info("createGame method ended.");
		return game;
	}

	public KalahInitResponse mapToIntiDto(Game game) {
		log.debug("mapToIntiDto method started.");
		KalahInitResponse kalahInit = new KalahInitResponse();
		kalahInit.setId(game.getId());
		kalahInit.setUri(game.getUri());
		log.debug("mapToIntiDto method ended.");
		return kalahInit;
	}

	public KalahMovedResponse mapToMovedDto(Game game) {
		log.debug("mapToMovedDto method started.");
		KalahMovedResponse response = new KalahMovedResponse();
		response.setId(game.getId());
		response.setUri(game.getUri());
		response.setScore(game.getScoreBoard());
		response.setGameStatus(game.getGameStatus());
		response.setNextPlayer(String.valueOf(game.getPlayer().getPlayerId()));
		log.debug("mapToMovedDto method ended.");
		return response;
	}

}
