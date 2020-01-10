package com.jfernandez.musiclib.data.model;

import java.util.List;

/**
 * POJO class to handle JSON Response from API
 */
public class LibraryResponse {
	public int resultCount;
	public List<TrackItem> results;

	public int getResultCount() {
		return resultCount;
	}

	public void setResultCount(int resultCount) {
		this.resultCount = resultCount;
	}

	public List<TrackItem> getResults() {
		return results;
	}

	public void setResults(List<TrackItem> results) {
		this.results = results;
	}

}
