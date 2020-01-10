package com.jfernandez.musiclib.ui.main;

import android.content.Context;

import com.jfernandez.musiclib.data.DataManager;
import com.jfernandez.musiclib.data.api.APIClient;
import com.jfernandez.musiclib.data.api.APIInterface;
import com.jfernandez.musiclib.data.model.LibraryResponse;
import com.jfernandez.musiclib.data.model.TrackItem;
import com.jfernandez.musiclib.ui.base.BasePresenter;
import com.jfernandez.musiclib.utils.NetworkConnectionInterceptor;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Presenter class which handles the API call results from iTunes
 * It is responsible to act as the middle man between View and Model.
 * It retrieves data from the Model and returns it formatted to the View.
 * Tt also decides what happens when you interact with the View.
 * @param <V>
 */
public class MainPresenter <V extends MainMvpView> extends BasePresenter<V> implements MainMvpPresenter<V>  {

	/**
	 * Instantiating APIInterface class
	 */
	private APIInterface apiInterface;

	/**
	 * Call constructor to initialize APIInterface class
	 * @param dataManager needed to access datamanager methods
	 * @param context needed to instantiate in activity
	 */
	public MainPresenter(DataManager dataManager, Context context) {
		super(dataManager);
		apiInterface = APIClient.getClient(dataManager, context).create(APIInterface.class);
	}

	/**
	 * Call method to get all tracks from iTunes API URL
	 */
	@Override
	public void getTracks() {
		getMvpView().showProgressDialog();
		Call<LibraryResponse> call = apiInterface.getLibrary();
		call.enqueue(new Callback<LibraryResponse>() {
			@Override
			public void onResponse(Call<LibraryResponse> call, Response<LibraryResponse> response) {
				getMvpView().hideProgressDialog();
				if(response != null && response.isSuccessful()){
					List<TrackItem> trackItems = new ArrayList<>();
					trackItems.addAll(response.body().results);
					getDataManager().setCache(trackItems);
					getMvpView().displayTrackList(trackItems);
				} else {
					getMvpView().showMessage("Error loading tracks");
				}
			}

			@Override
			public void onFailure(Call<LibraryResponse> call, Throwable t) {
				getMvpView().hideProgressDialog();
				call.cancel();
				if(t instanceof NetworkConnectionInterceptor.NoConnectivityException) {
					// show No Connectivity message to user or do whatever you want.
					getMvpView().showMessage("Please check your internet connection");
					if(getDataManager().getCache().size() > 0){
						getMvpView().displayTrackList(getDataManager().getCache());
						getMvpView().showSnackBarMessage("Cache data loaded.");
					}
				} else {
					getMvpView().showMessage("Error loading data");
				}
			}
		});
	}
}
