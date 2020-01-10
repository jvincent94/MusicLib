package com.jfernandez.musiclib.ui.base;
import com.jfernandez.musiclib.data.DataManager;

/**
 * Base class consist of methods that will be used in Presenter Class
 */
public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private V mMvpView;
    DataManager mDataManager;

    /**
     * Constructor used to instantiate to another class
     * @param dataManager needed to pass shared pref
     */
    public BasePresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    /**
     * Call this method to include methods View Class
     * @param mvpView
     */
    @Override
    public void onAttach(V mvpView) {
        mMvpView = mvpView;
    }

    /**
     * Call this method to get access on MvpView class
     * @return V
     * @see MvpView
     */
    public V getMvpView() {
        return mMvpView;
    }

    /**
     * Call this method to get access on DataManager class
     * @return datamanager
     * @see DataManager
     */
    public DataManager getDataManager() {
        return mDataManager;
    }
}