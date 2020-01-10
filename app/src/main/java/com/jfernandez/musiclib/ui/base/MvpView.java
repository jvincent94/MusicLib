package com.jfernandez.musiclib.ui.base;

/**
 * Interface that can contains common abstract method
 * that can be use all over the app
 */
public interface MvpView {

    /**
     * Call method to display error message
     * @param error needed to display error message on toast
     */
    void showMessage(String error);


    /**
     * Call method to display progress dialog
     */
    void showProgressDialog();

    /**
     * Call method to hide progress dialog
     */
    void hideProgressDialog();
}
