package com.jfernandez.musiclib.ui.base;

/**
 * Interface that consist of abstract method for MVP framework
 */
public interface MvpPresenter <V extends MvpView> {

    /**
     * Call this method to include methods View Class
     * @param mvpView
     */
    void onAttach(V mvpView);
}