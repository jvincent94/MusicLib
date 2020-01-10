package com.jfernandez.musiclib.data.api;

import com.jfernandez.musiclib.data.model.LibraryResponse;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * This is a collection of API methods
 */
public interface APIInterface {

    @GET("/search?term=star&amp;country=au&amp;media=movie&amp;all")
    Call<LibraryResponse> getLibrary();

}
