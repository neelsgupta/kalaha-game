package com.game.kalah.service;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.game.kalah.domain.Game;
import com.game.kalah.domain.GameStatus;
import com.game.kalah.exception.GameEndedException;
import com.game.kalah.exception.InvalidIdException;
import com.game.kalah.mapper.KalahMapper;
import com.game.kalah.repository.KalahRepository;

@RunWith(MockitoJUnitRunner.class)
public class KalahServiceTest {

	@InjectMocks
	private KalahServiceImpl kalahService;

	@Mock
	private KalahMapper kalahMapper;

	@Mock
	private KalahRepository repository;

	@Mock
	private KalahHandler gameService;

	private String gameId = "anyGameId";
	private Integer pitId = 1;

	@Test
	public void testCreateGame() {
		Game game = new Game();
		when(kalahMapper.createGame()).thenReturn(game);
		doNothing().when(repository).create(game);
		kalahService.create();

		verify(kalahMapper).createGame();
		verify(repository).create(game);
	}

	@Test
	public void testPlayGame() throws Exception {
		Game game = new Game();
		game.setGameStatus(GameStatus.IN_PROGRESS);
		when(repository.get(gameId)).thenReturn(game);
		doNothing().when(gameService).makeMove(game, pitId);
		doNothing().when(repository).save(gameId, game);
		kalahService.play(gameId, pitId);

		verify(repository).get(gameId);
		verify(gameService).makeMove(game, pitId);
		verify(repository).save(gameId, game);
		verify(repository, never()).delete(gameId);
	}

	@Test(expected = InvalidIdException.class)
	public void testPlayGameThrowExceptionIfGameNotExisted() throws Exception {
		Game game = new Game();
		doThrow(InvalidIdException.class).when(repository).get(gameId);
		kalahService.play(gameId, pitId);

		verify(repository).get(gameId);
		verify(gameService, never()).makeMove(game, pitId);
		verify(repository, never()).save(gameId, game);
		verify(repository, never()).delete(gameId);
	}

	@Test(expected = GameEndedException.class)
	public void testPlayGameThrowExceptionIfGameNotInProgress() throws Exception {
		Game game = new Game();
		// doNothing().when(repository).delete(gameId);
		when(repository.get(gameId)).thenReturn(game);
		kalahService.play(gameId, pitId);

		verify(repository).get(gameId);
		verify(repository).delete(gameId);
		verify(gameService, never()).makeMove(game, pitId);
		verify(repository, never()).save(gameId, game);
	}

}
