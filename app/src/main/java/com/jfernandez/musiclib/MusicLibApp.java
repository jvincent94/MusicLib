package com.jfernandez.musiclib;

import android.app.Application;

import com.jfernandez.musiclib.data.DataManager;
import com.jfernandez.musiclib.data.SharedPrefsHelper;
/**
 * Application class is a base class that help components
 * communicate with each other
 */
public class MusicLibApp extends Application {

	DataManager dataManager;

	@Override
	public void onCreate() {
		super.onCreate();
		SharedPrefsHelper sharedPrefsHelper = new SharedPrefsHelper(getApplicationContext());
		dataManager = new DataManager(sharedPrefsHelper);

	}

	/**
	 * Call this method to get access on DataManager class
	 * @return datamanager
	 * @see DataManager
	 */
	public DataManager getDataManager() {
		return dataManager;
	}
}
