package com.example.projetmobile.presentation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.projetmobile.Constants;
import com.example.projetmobile.R;
import com.example.projetmobile.presentation.controller.MainController;
import com.example.projetmobile.presentation.model.Mark;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.util.List;


public class MainActivity extends AppCompatActivity
{
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private MainController controller;
    private ListAdapter mAdapter;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //creating a controller for this view :
        controller = new MainController
                (
                this,
                new GsonBuilder()
                    .setLenient()
                    .create(),
                getSharedPreferences(Constants.KEY_APP_MARK, Context.MODE_PRIVATE)
                );

        //get data from cache or make a Api Call :
        controller.onStart();
        imageView = findViewById(R.id.icon);
        //Picasso.get()
                //.load("drawable/mark_1.jpg").into(imageView);
                //.load("http://i.imgur.com/DvpvklR.png").into(imageView);

    }


    public void showList(List<Mark> markList)
    {
        mAdapter = new ListAdapter(markList);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
    }

    public void showError() {
        Toast.makeText(getApplicationContext(),"API Error", Toast.LENGTH_SHORT).show(); }
}