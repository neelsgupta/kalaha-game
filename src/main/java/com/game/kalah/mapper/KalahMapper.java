package com.game.kalah.mapper;

import java.util.UUID;

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
import com.game.kalah.repository.KalahGameUtil;

@Component
@ConfigurationProperties
public class KalahMapper {

	@Value("${server.port}")
	private String port;

	private Logger log = LoggerFactory.getLogger(this.getClass());

	public Game createGame() {
		log.info("createGame method started.");
		Game game = new Game();
		String gameId = UUID.randomUUID().toString();
		game.setId(gameId);
		game.setScoreBoard(KalahGameUtil.setInitScoreBoard());
		game.setGameStatus(GameStatus.IN_PROGRESS);
		game.setPlayer(Player.FIRST_PLAYER);
		log.info("createGame method ended.");
		return game;
	}

	public KalahInitResponse mapToIntiDto(Game game) {
		log.debug("mapToIntiDto method started.");
		KalahInitResponse kalahInit = new KalahInitResponse();
		String gameId = game.getId();
		kalahInit.setId(gameId);
		kalahInit.setUri(createGameURL(gameId));
		log.debug("mapToIntiDto method ended.");
		return kalahInit;
	}

	public KalahMovedResponse mapToMovedDto(Game game) {
		log.debug("mapToMovedDto method started.");
		KalahMovedResponse response = new KalahMovedResponse();
		String gameId = game.getId();
		response.setId(gameId);
		response.setUri(createGameURL(gameId));
		response.setScore(game.getScoreBoard());
		response.setGameStatus(game.getGameStatus());
		response.setNextPlayer(String.valueOf(game.getPlayer().getPlayerId()));
		log.debug("mapToMovedDto method ended.");
		return response;
	}

	private String createGameURL(String gameId) {
		String url = "http://localhost:" + port + "/games/" + gameId;
		return url;
	}

}
