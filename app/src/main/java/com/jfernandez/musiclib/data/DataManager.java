package com.jfernandez.musiclib.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jfernandez.musiclib.data.model.TrackItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class use to save temporary data
 */
public class DataManager {
    SharedPrefsHelper mSharedPrefsHelper;

    public DataManager(SharedPrefsHelper sharedPrefsHelper) {
        mSharedPrefsHelper = sharedPrefsHelper;
    }

    /**
     * Call method to clear cache
     */
    public void clear() {
        mSharedPrefsHelper.clear();
    }

    /**
     * Call method to set data to cache
     * @param trackItemList data from API list
     */
    public void setCache(List<TrackItem> trackItemList){
        String list = new Gson().toJson(trackItemList);
        mSharedPrefsHelper.putCache(list);
    }

    /**
     * Call method to set data to cache
     * @return list data converted from json form
     */
    public List<TrackItem> getCache(){
        String list = mSharedPrefsHelper.getCache();
        if(list != null){
            return new Gson().fromJson(list, new TypeToken<List<TrackItem>>(){}.getType());
        }
        return new ArrayList<TrackItem>(){};
    }

}
