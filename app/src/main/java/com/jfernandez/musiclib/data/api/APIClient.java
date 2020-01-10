package com.jfernandez.musiclib.data.api;

import android.content.Context;
import com.jfernandez.musiclib.data.DataManager;
import com.jfernandez.musiclib.utils.NetworkConnectionInterceptor;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * APIClient is a helper class to initialize Retrofit for
 * consuming RESTful APO
 */
public class APIClient {
    private static Retrofit retrofit = null;
    public static String liveBaseUrl = "https://itunes.apple.com";
    public static String baseUrl = liveBaseUrl;


    /**
     * Call static method to initialize API calls
     * @param mDataManager needed to access Datamanager class methods
     * @param context needed to initialize on Activity
     */
    public static Retrofit getClient(final DataManager mDataManager, final Context context) {
        // Define the interceptor
        Interceptor headersInterceptor;
        headersInterceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder().
                        addHeader("Accept-Language", "en").
                        build();
                return chain.proceed(newRequest);
            }
        };

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient
                .Builder()
                .connectTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES);

        OkHttpClient client = httpClient.addInterceptor(headersInterceptor)
                .addInterceptor(new NetworkConnectionInterceptor(context))
                .addInterceptor(interceptor).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();


        return retrofit;
    }

}
