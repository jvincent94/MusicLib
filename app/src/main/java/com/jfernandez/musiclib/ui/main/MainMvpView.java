package com.jfernandez.musiclib.ui.main;

import com.jfernandez.musiclib.data.model.TrackItem;
import com.jfernandez.musiclib.ui.base.MvpView;

import java.util.List;

/**
 * Collection of methods that interacts between presenter and view
 */
public interface MainMvpView extends MvpView {

	/**
	 * Call method to pass data from iTunes API
	 * @param trackItems needed to display data on recyclerView
	 */
	void displayTrackList(List<TrackItem> trackItems);
	/**
	 * Call method to display error message
	 * @param msg needed to display error message on Snackbar
	 */
	void showSnackBarMessage(String msg);
}
