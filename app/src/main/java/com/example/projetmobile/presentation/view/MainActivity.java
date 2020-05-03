package com.example.projetmobile.presentation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;
import com.example.projetmobile.Constants;
import com.example.projetmobile.R;
import com.example.projetmobile.presentation.controller.MainController;
import com.google.gson.GsonBuilder;


public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private MainController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = new MainController
                (
                this,
                new GsonBuilder()
                    .setLenient()
                    .create(),
                getSharedPreferences(Constants.KEY_APP_MARK, Context.MODE_PRIVATE)
                );

        controller.onStart();
    }

    public void showList(List<Mark> markList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new ListAdapter(markList);
        recyclerView.setAdapter(mAdapter); }

    public void showError() {
        Toast.makeText(getApplicationContext(),"API Error", Toast.LENGTH_SHORT).show(); }
}