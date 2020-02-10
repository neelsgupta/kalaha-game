package com.game.kalah.mapper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.game.kalah.dto.Kalah;
import com.game.kalah.dto.KalahInitResponse;
import com.game.kalah.dto.KalahMovedResponse;
import com.game.kalah.mapper.KalahMapper;

@RunWith(MockitoJUnitRunner.class)
public class KalahMapperTest {

	@InjectMocks
	private KalahMapper kalahMapper;

	private String gameId = "anyId";
	private String gameUri = "anyUri";

	@Test
	public void testMapToKalah() {
		Kalah kalah = kalahMapper.mapToKalah();
		Assert.assertNotNull(kalah.getId());
		Assert.assertNotNull(kalah.getUri());
		Assert.assertNotNull(kalah.getStatus());
	}

	@Test
	public void testMapToIntiDto() {
		KalahInitResponse kalahInit = kalahMapper.mapToIntiDto(getKalah());
		Assert.assertEquals(gameId, kalahInit.getId());
		Assert.assertEquals(gameUri, kalahInit.getUri());
	}

	@Test
	public void testMapToMovedDto() {
		Kalah kalah = new Kalah();
		kalah.setId("anyId");
		kalah.setUri("anyUri");
		KalahMovedResponse kalahMove = kalahMapper.mapToMovedDto(getKalah());
		Assert.assertEquals(gameId, kalahMove.getId());
		Assert.assertEquals(gameUri, kalahMove.getUrl());
		Assert.assertNotNull(kalahMove.getStatus());
	}

	private Kalah getKalah() {
		Kalah kalah = new Kalah();
		kalah.setId(gameId);
		kalah.setUri(gameUri);
		int status[] = new int[14];
		for (int i = 0; i < status.length; i++) {
			if (i != 6 || i != 13) {
				status[i] = 6;
			}
		}
		kalah.setStatus(status);
		return kalah;
	}

}
