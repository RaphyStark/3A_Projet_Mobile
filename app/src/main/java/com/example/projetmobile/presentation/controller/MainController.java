package com.example.projetmobile.presentation.controller;

import android.content.SharedPreferences;
import com.example.projetmobile.Constants;
import com.example.projetmobile.Singletons;
import com.example.projetmobile.presentation.model.Mark;
import com.example.projetmobile.presentation.model.RestMarkResponse;
import com.example.projetmobile.presentation.view.MainActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainController
{
    private MainActivity view;
    private Gson gson;
    private SharedPreferences sharedPreferences;

    public MainController(MainActivity mainActivity, Gson gson, SharedPreferences sharedPreferences)
    {
        this.view = mainActivity;
        this.gson = gson;
        this.sharedPreferences = sharedPreferences;
    }

    public void onStart()
    {
        List<Mark> markList = getDataFromCache();
        if (markList != null)
        {
            view.showList(markList);
        }
        else makeApiCall();
    }


    private void makeApiCall()
    {
        Call<RestMarkResponse> call = Singletons.getMarkApi().getMarkResponse();
        call.enqueue(new Callback<RestMarkResponse>()
        {
            @Override
            public void onResponse(Call<RestMarkResponse> call, Response<RestMarkResponse> response)
            {
                if (response.isSuccessful() && response.body() != null)
                {
                    List<Mark> markList = response.body().getResults();
                    saveList(markList);
                    view.showList(markList);
                }
                else view.showError();
            }

            @Override
            public void onFailure(Call<RestMarkResponse> call, Throwable t)
            {
                view.showError();
            }
        });
    }

    private void saveList(List<Mark> markList)
    {
        String jsonString = gson.toJson(markList);
        sharedPreferences
                .edit()
                .putString(Constants.KEY_MARK_LIST, jsonString)
                .apply();
    }

    private List<Mark> getDataFromCache()
    {
        String jsonMark = sharedPreferences.getString(Constants.KEY_MARK_LIST, null);
        if (jsonMark == null)
        {
            return null;
        }
        else {
            Type listType = new TypeToken<List<Mark>>() {
            }.getType();
            return gson.fromJson(jsonMark, listType);
        }
    }

    public void onItemClick(Mark mark)
    {
        view.navigateToDetails(mark);
    }

}