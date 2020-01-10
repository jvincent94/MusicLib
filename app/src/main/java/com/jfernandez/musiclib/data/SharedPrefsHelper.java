package com.jfernandez.musiclib.data;

import android.content.Context;
import android.content.SharedPreferences;

/**
 *  A class to save data in Sharedpreferences
 */
public class SharedPrefsHelper {
    public static final String MY_PREFS = "MUSICLIBS_PREFS";
    public static final String CACHE_REF = "CACHE_REF";


    SharedPreferences mSharedPreferences;

    /**
     * Call constructor to initialize shared pre
     * @param context context needed to initialize
     */
    public SharedPrefsHelper(Context context) {
        mSharedPreferences = context.getSharedPreferences(MY_PREFS, Context.MODE_PRIVATE);
    }

    /**
     * Call method to clear cache
     */
    public void clear() {
        mSharedPreferences.edit().clear().apply();
    }

    /**
     * Call method to save string data to shared pref
     * @param list string value to save in shared pref
     */
    public void putCache(String list){
        mSharedPreferences.edit().putString(CACHE_REF, list).apply();
    }

    /**
     * Call constructor to initialize shared pre
     * @return String value
     */
    public String getCache(){
        return mSharedPreferences.getString(CACHE_REF, null);
    }


}
