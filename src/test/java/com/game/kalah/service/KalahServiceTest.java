package com.game.kalah.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.game.kalah.dto.Kalah;
import com.game.kalah.repository.KalahRepository;
import com.game.kalah.service.KalahServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class KalahServiceTest {

	@InjectMocks
	private KalahServiceImpl kalahService;

	@Mock
	public KalahRepository repository;

	@Test
	public void testCreateGame() {
		Kalah kalah = new Kalah();
		when(repository.create()).thenReturn(kalah);
		kalahService.createGame();
		verify(repository).create();
	}

	@Test
	public void testPlayGame() {
		Kalah kalah = new Kalah();
		when(repository.play(Mockito.anyString(), Mockito.anyString())).thenReturn(kalah);
		kalahService.playGame(Mockito.anyString(), Mockito.anyString());
		verify(repository).play(Mockito.anyString(), Mockito.anyString());
	}

}
