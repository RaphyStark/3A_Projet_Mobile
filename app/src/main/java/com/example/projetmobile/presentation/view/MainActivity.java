package com.example.projetmobile.presentation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.example.projetmobile.Constants;
import com.example.projetmobile.R;
import com.example.projetmobile.presentation.controller.MainController;
import com.example.projetmobile.presentation.model.Mark;
import com.google.gson.GsonBuilder;
import java.util.List;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.widget.Toast;
import android.content.Context;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity
{
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private MainController controller;
    private ListAdapter mAdapter;

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


    public void showList(List<Mark> markList)
    {
        mAdapter = new ListAdapter(markList, new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Mark item) {
                controller.onItemClick(item);
            }
        });
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(mAdapter);
    }

    public void showError()
    {
        Toast.makeText(getApplicationContext(),"API Error", Toast.LENGTH_SHORT).show();
    }

    public void navigateToDetails(Mark mark)
    {
        Toast.makeText(getApplicationContext(), "TODO NAVIGATE", Toast.LENGTH_SHORT).show();
    }

}