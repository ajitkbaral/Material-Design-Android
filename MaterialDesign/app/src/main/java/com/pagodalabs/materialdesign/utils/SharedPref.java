package com.pagodalabs.materialdesign.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Ajit Kumar Baral on 6/13/2015.
 */
public class SharedPref {
    //SharedPreferences

    public static void saveToPreferences(Context context, String fileName, String preferenceName, String preferenceValue){
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(preferenceName, preferenceValue);
        editor.apply();

    }

    public static String readFromPreferences(Context context, String fileName, String preferenceName, String defaultValue){
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return sharedPreferences.getString(preferenceName, defaultValue);

    }
}
