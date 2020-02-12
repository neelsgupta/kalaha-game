package com.game.kalah.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.game.kalah.domain.Game;
import com.game.kalah.dto.KalahInitResponse;
import com.game.kalah.dto.KalahMovedResponse;
import com.game.kalah.mapper.KalahMapper;
import com.game.kalah.service.KalahService;
import com.game.kalah.validator.KalahValidator;

@RestController
public class KalahController {

	private static final String GAMES = "/games";
	private static final String PLAY_GAME = "/games/{gameId}/pits/{pitId}";

	@Autowired
	public KalahValidator kalahValidator;

	@Autowired
	public KalahService kalahService;

	@Autowired
	public KalahMapper kalahMapper;

	@PostMapping(GAMES)
	public ResponseEntity<KalahInitResponse> createGame() {
		Game game = kalahService.create();
		KalahInitResponse kalahInitResponse = kalahMapper.mapToIntiDto(game);
		return ResponseEntity.status(HttpStatus.CREATED).body(kalahInitResponse);
	}

	@PutMapping(PLAY_GAME)
	public ResponseEntity<KalahMovedResponse> playGame(@PathVariable("gameId") String gameId,
			@PathVariable("pitId") Integer pitId) throws Exception {
		kalahValidator.validate(gameId, pitId);
		Game game = kalahService.play(gameId, pitId);
		KalahMovedResponse kalahMovedResponse = kalahMapper.mapToMovedDto(game);
		return ResponseEntity.status(HttpStatus.OK).body(kalahMovedResponse);
	}

}
