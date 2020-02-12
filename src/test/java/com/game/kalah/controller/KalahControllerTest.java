/*package com.game.kalah.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.game.kalah.controller.KalahController;
import com.game.kalah.domain.Kalah;
import com.game.kalah.dto.KalahInitResponse;
import com.game.kalah.dto.KalahMovedResponse;
import com.game.kalah.exception.InvalidIdException;
import com.game.kalah.mapper.KalahMapper;
import com.game.kalah.service.KalahService;
import com.game.kalah.validator.KalahValidator;

@RunWith(MockitoJUnitRunner.class)
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
		Kalah kalah = new Kalah();
		KalahInitResponse kalahInitResponse = new KalahInitResponse();
		when(kalahService.createGame()).thenReturn(kalah);
		when(kalahMapper.mapToIntiDto(kalah)).thenReturn(kalahInitResponse);
		ResponseEntity<KalahInitResponse> responseEntity = kalahController.createGame();

		verify(kalahService).createGame();
		verify(kalahMapper).mapToIntiDto(kalah);
		Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
		Assert.assertEquals(responseEntity.getBody(), kalahInitResponse);
	}

	@Test
	public void testPlayGame() throws NumberFormatException, Exception {
		Kalah kalah = new Kalah();
		KalahMovedResponse kalahMovedResponse = new KalahMovedResponse();
		doNothing().when(kalahValidator).validate(Mockito.anyString(), Mockito.anyString());
		when(kalahService.playGame(Mockito.anyString(), Mockito.anyString())).thenReturn(kalah);
		when(kalahMapper.mapToMovedDto(kalah)).thenReturn(kalahMovedResponse);
		ResponseEntity<KalahMovedResponse> responseEntity = kalahController.playGame(Mockito.anyString(), Mockito.anyString());
		
		verify(kalahValidator).validate(Mockito.anyString(), Mockito.anyString());
		verify(kalahService).playGame(Mockito.anyString(), Mockito.anyString());
		verify(kalahMapper).mapToMovedDto(kalah);
		Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
		Assert.assertEquals(responseEntity.getBody(), kalahMovedResponse);
	}
	
	@Test
	public void testPlayGameThrowExceptionIfInvalidGameId() throws NumberFormatException, Exception {
		Kalah kalah = new Kalah();
		KalahMovedResponse kalahMovedResponse = new KalahMovedResponse();
		doNothing().when(kalahValidator).validate(Mockito.anyString(), Mockito.anyString());
		when(kalahService.playGame(Mockito.anyString(), Mockito.anyString())).thenReturn(kalah);
		when(kalahMapper.mapToMovedDto(kalah)).thenReturn(kalahMovedResponse);
		ResponseEntity<KalahMovedResponse> responseEntity = kalahController.playGame(Mockito.anyString(), Mockito.anyString());
		
		verify(kalahValidator).validate(Mockito.anyString(), Mockito.anyString());
		verify(kalahService).playGame(Mockito.anyString(), Mockito.anyString());
		verify(kalahMapper).mapToMovedDto(kalah);
		Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
		Assert.assertEquals(responseEntity.getBody(), kalahMovedResponse);
	}

}
*/