package com.game.kalah.mapper;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.game.kalah.dto.Kalah;
import com.game.kalah.dto.KalahInitResponse;
import com.game.kalah.dto.KalahMovedResponse;
import com.game.kalah.repository.KalahGame;

@Component
@ConfigurationProperties
public class KalahMapper {
	
	@Value("${server.port}")
	private String port;

	public Kalah mapToKalah() {
		Kalah kalah = new Kalah();
		Random rand = new Random();
		String gameId = String.format("%04d", rand.nextInt(10000));
		kalah.setId(gameId);
		String uri = "http://localhost:"+port+"/games/";
		kalah.setUri(uri + gameId);
		kalah.setStatus(KalahGame.initGame());
		return kalah;
	}

	public KalahInitResponse mapToIntiDto(Kalah kalah) {
		KalahInitResponse kalahInit = new KalahInitResponse();
		kalahInit.setId(kalah.getId());
		kalahInit.setUri(kalah.getUri());
		return kalahInit;
	}

	public KalahMovedResponse mapToMovedDto(Kalah kalah) {
		KalahMovedResponse response = new KalahMovedResponse();
		response.setId(kalah.getId());
		response.setUrl(kalah.getUri());
		Map<String, String> status = new LinkedHashMap<String, String>();
		int[] values = kalah.getStatus();
		for (int i = 0; i < values.length; i++) {
			status.put(Integer.toString(i + 1), Integer.toString(values[i]));
		}
		response.setStatus(status);
		return response;
	}

}
