package com.example.projetmobile.presentation.controller;

import android.content.SharedPreferences;
import android.widget.Toast;
import com.example.projetmobile.Constants;
import com.example.projetmobile.data.MarkApi;
import com.example.projetmobile.presentation.view.MainActivity;
import com.google.gson.Gson;
import java.lang.reflect.Type;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.List;

public class MainController {
    private SharedPreferences sharedPreferences;
    private Gson gson;
    private MainActivity view;


    public MainController(MainActivity mainActivity, Gson gson, SharedPreferences sharedPreferences) {
        this.view = mainActivity;
        this.gson = gson;
        this.sharedPreferences = sharedPreferences;
    }

    public void onStart() {
        List<Mark> markList = getDataFromCache();
        if (markList != null) {
            view.showList(markList);
        } else makeApiCall();
    }


    private void makeApiCall() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        MarkApi markApi = retrofit.create(MarkApi.class);
        Call<RestMarkResponse> call = markApi.getMarkResponse();
        call.enqueue(new Callback<RestMarkResponse>() {
            @Override
            public void onResponse(Call<RestMarkResponse> call, Response<RestMarkResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Mark> markList = response.body().getResults();
                    saveList(markList);
                    view.showList(markList);
                } else view.showError();
            }

            @Override
            public void onFailure(Call<RestMarkResponse> call, Throwable t) {
                view.showError();
            }
        });
    }

    private void saveList(List<Mark> markList) {
        String jsonString = gson.toJson(markList);
        sharedPreferences
                .edit()
                .putString(Constants.KEY_MARK_LIST, jsonString)
                .apply();
        Toast.makeText(getApplicationContext(), "List Saved", Toast.LENGTH_SHORT).show();
    }

    private List<Mark> getDataFromCache() {
        String jsonMark = sharedPreferences.getString(Constants.KEY_MARK_LIST, null);
        if (jsonMark == null) {
            return null;
        } else {
            Type listType = new TypeToken<List<Mark>>() {
            }.getType();
            return gson.fromJson(jsonMark, listType);
        }
    }
}