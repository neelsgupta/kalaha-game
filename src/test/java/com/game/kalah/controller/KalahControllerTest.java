package com.game.kalah.controller;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.game.kalah.domain.Game;
import com.game.kalah.dto.KalahInitResponse;
import com.game.kalah.dto.KalahMovedResponse;
import com.game.kalah.exception.InvalidIdException;
import com.game.kalah.mapper.KalahMapper;
import com.game.kalah.service.KalahService;
import com.game.kalah.validator.KalahValidator;

//@RunWith(MockitoJUnitRunner.class)
@RunWith(SpringRunner.class)
public class KalahControllerTest {

	@InjectMocks
	private KalahController kalahController;

	@Mock
	private KalahValidator kalahValidator;

	@Mock
	private KalahService kalahService;

	@Mock
	private KalahMapper kalahMapper;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCreateGame() {
		Game game = new Game();
		KalahInitResponse kalahInitResponse = new KalahInitResponse();
		when(kalahService.create()).thenReturn(game);
		when(kalahMapper.mapToIntiDto(game)).thenReturn(kalahInitResponse);
		ResponseEntity<KalahInitResponse> responseEntity = kalahController.createGame();

		verify(kalahService).create();
		verify(kalahMapper).mapToIntiDto(game);
		assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
		assertEquals(responseEntity.getBody(), kalahInitResponse);
	}

	@Test
	public void testPlayGame() throws Exception {
		Game game = new Game();
		KalahMovedResponse kalahMovedResponse = new KalahMovedResponse();
		doNothing().when(kalahValidator).validate(anyString(), anyInt());
		when(kalahService.play(anyString(), anyInt())).thenReturn(game);
		when(kalahMapper.mapToMovedDto(game)).thenReturn(kalahMovedResponse);
		ResponseEntity<KalahMovedResponse> responseEntity = kalahController.playGame(anyString(), anyInt());

		verify(kalahValidator).validate(anyString(), anyInt());
		verify(kalahService).play(anyString(), anyInt());
		verify(kalahMapper).mapToMovedDto(game);
		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
		assertEquals(responseEntity.getBody(), kalahMovedResponse);
	}

	@Test(expected = InvalidIdException.class)
	public void testPlayGameThrowExceptionIfInvalidGameId() throws Exception {
		Game game = new Game();
		doThrow(InvalidIdException.class).when(kalahValidator).validate(anyString(), anyInt());
		kalahController.playGame(anyString(), anyInt());

		verify(kalahValidator).validate(anyString(), anyInt());
		verify(kalahService, never()).play(anyString(), anyInt());
		verify(kalahMapper, never()).mapToMovedDto(game);
	}

}
