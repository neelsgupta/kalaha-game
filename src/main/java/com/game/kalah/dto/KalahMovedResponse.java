package com.game.kalah.dto;

import java.util.Map;

public class KalahMovedResponse {

	private String id;

	private String uri;

	private Map<String, String> status;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return uri;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.uri = url;
	}

	/**
	 * @return the status
	 */
	public Map<String, String> getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Map<String, String> status) {
		this.status = status;
	}

}
