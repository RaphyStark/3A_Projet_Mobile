package com.example.projetmobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://raw.githubusercontent.com";

    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        makeApiCall();
    }

    private void showList(List<Mark> markList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // define an adapter
        mAdapter = new ListAdapter(markList);
        recyclerView.setAdapter(mAdapter);
    }

    private void makeApiCall() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        MarkApi markApi = retrofit.create(MarkApi.class);

        Call<RestMarkResponse> call = markApi.getMarkResponse();

        System.out.println(markApi.getMarkResponse().request().url());

        call.enqueue(new Callback<RestMarkResponse>() {
            @Override
            public void onResponse(Call<RestMarkResponse> call, Response<RestMarkResponse> response) {
                System.out.println("response body: "+ response.body());
                if (response.isSuccessful() && response.body() != null) {
                    List<Mark> markList = response.body().getResults();
                    showList(markList);
                    Toast.makeText(getApplicationContext(), "API Success", Toast.LENGTH_SHORT).show();
                } else showError();
            }

            @Override
            public void onFailure(Call<RestMarkResponse> call, Throwable t) {
                showError();
            }
        });
    }

    private void showError() {
        Toast.makeText(getApplicationContext(),"API Error", Toast.LENGTH_SHORT).show();
    }
}
