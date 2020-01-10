package com.jfernandez.musiclib.ui.main;

import com.jfernandez.musiclib.ui.base.MvpPresenter;

/**
 * Collection of methods that interacts between view and presenter
 */
public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {
	void getTracks();
}
