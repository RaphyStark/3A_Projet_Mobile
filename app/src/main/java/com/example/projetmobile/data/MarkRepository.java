package com.example.projetmobile.data;
import android.content.SharedPreferences;

import com.example.projetmobile.Constants;
import com.example.projetmobile.presentation.model.Mark;
import com.example.projetmobile.presentation.model.RestMarkResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MarkRepository {
    private MarkApi markApi;
    private SharedPreferences sharedPreferences;
    private Gson gson;

    public MarkRepository(MarkApi markApi, SharedPreferences sharedPreferences, Gson gson)
    {
        this.markApi = markApi;
        this.sharedPreferences = sharedPreferences;
        this.gson = gson;
    }

    public void getMarkResponse(final MarkCallback callback)
    {
     //   System.out.println(callback);
        List<Mark> list = getDataFromCache();
       // System.out.println(list);
        if(list != null)
        {
            callback.onSuccess(list);
        }
        else {
            markApi.getMarkResponse().enqueue(new Callback<RestMarkResponse>()
            {
                @Override
                public void onResponse(Call<RestMarkResponse> call, Response<RestMarkResponse> response)
                {
                    if (response.isSuccessful() && response.body() != null) {
                        callback.onSuccess(response.body().getResults());
                    //  saveList(response.body().getResults());
                    } else
                        callback.onFailed();
                }

                @Override
                public void onFailure(Call<RestMarkResponse> call, Throwable t)
                {
                    callback.onFailed();
                }
            });
        }
    }


    private List<Mark> getDataFromCache()
    {
        String jsonMark = sharedPreferences.getString(Constants.KEY_MARK_LIST, null);
        if (jsonMark == null) {
            return null;
        } else {
            Type listType = new TypeToken<List<Mark>>() {}.getType();
            return gson.fromJson(jsonMark, listType);
        }
    }

/*
    private void saveList(List<Mark> markList)
    {
        String jsonString = gson.toJson(markList);
        sharedPreferences
                .edit()
                .putString(Constants.KEY_MARK_LIST, jsonString)
                .apply();
    }
*/
}