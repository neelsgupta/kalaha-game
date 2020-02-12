package com.game.kalah.dto;

public class KalahInitResponse {

	private String id;
	private String uri;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	@Override
	public String toString() {
		return "KalahInitResponse [id=" + id + ", uri=" + uri + "]";
	}

}
