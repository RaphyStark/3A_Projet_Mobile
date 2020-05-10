package com.example.projetmobile;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.projetmobile.data.MarkApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Singletons {
    private static Gson gsonInstance;
    private static MarkApi markApiInstance;
    private static SharedPreferences sharedPreferencesInstance;

    public static Gson getGson() {
        if (gsonInstance == null) {
            gsonInstance = new GsonBuilder()
                    .setLenient()
                    .create();
        }
        return gsonInstance;
    }


    public static MarkApi getMarkApi() {
        if (markApiInstance == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();
            markApiInstance = retrofit.create(MarkApi.class);
        }
        return markApiInstance;
    }

    public static SharedPreferences getSharedPreferences(Context context) {
        if (sharedPreferencesInstance == null) {
            sharedPreferencesInstance = context.getSharedPreferences(Constants.KEY_APP_MARK, Context.MODE_PRIVATE);
        }
        return sharedPreferencesInstance;
    }
}