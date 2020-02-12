/*package com.game.kalah.repository;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.game.kalah.domain.Kalah;
import com.game.kalah.mapper.KalahMapper;
import com.game.kalah.repository.KalahRepositoryImpl;

@RunWith(MockitoJUnitRunner.class)

public class KalahRepositoryTest {

	@InjectMocks
	private KalahRepositoryImpl kalahRepository;

	@Mock
	private KalahMapper kalahMapper;
	
	private Map<String, Kalah> game;

	@Before
	public void setUp() throws Exception {
		game = new HashMap<>();
		String gameId = "1234";
		Kalah kalah = new Kalah();
		kalah.setId(gameId); 
		int [] init = new int[10];
		kalah.setStatus(init);
		game.put(gameId, kalah);
	}

	@Test
	public void testCreate() {
		Kalah kalah = new Kalah();
		when(kalahMapper.mapToKalah()).thenReturn(kalah);
		kalahRepository.create();
		verify(kalahMapper).mapToKalah();
	}

	@Test
	public void testPlay() {
		Kalah kalah = new Kalah();
		//TODO complete this
		//kalahRepository.play("1234", "4");
	}

}
*/