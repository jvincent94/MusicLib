package com.jfernandez.musiclib.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Class to check if internet connection is present when requesting in API URl
 */
public class NetworkConnectionInterceptor implements Interceptor {

	private Context mContext;

	/**
	 * Constructor to instantiate network checking interceptor on APIClient Class
	 * @see com.jfernandez.musiclib.data.api.APIClient
	 */
	public NetworkConnectionInterceptor(Context context) {
		mContext = context;
	}

	/**
	 * Override method that returns response on network connectivity
	 * @param chain
	 * @return Response
	 * @throws IOException
	 */
	@Override
	public Response intercept(Chain chain) throws IOException {
		if (!isConnected()) {
			throw new NoConnectivityException();
			// Throwing our custom exception 'NoConnectivityException'
		}

		Request.Builder builder = chain.request().newBuilder();
		return chain.proceed(builder.build());
	}

	/**
	 * Call method to check if device is connected to internet
	 * @return boolean
	 */
	private boolean isConnected(){
		ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
		return (netInfo != null && netInfo.isConnected());
	}

	/**
	 * Custom exception for no internet connection
	 */
	public class NoConnectivityException extends IOException {

		@Override
		public String getMessage() {
			return "No Internet Connection";
			// You can send any message whatever you want from here.
		}
	}
}
