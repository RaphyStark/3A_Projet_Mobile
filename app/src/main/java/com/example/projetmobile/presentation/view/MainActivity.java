package com.example.projetmobile.presentation.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetmobile.Constants;
import com.example.projetmobile.R;
import com.example.projetmobile.Singletons;
import com.example.projetmobile.data.MarkRepository;
import com.example.projetmobile.presentation.controller.MainController;
import com.example.projetmobile.presentation.model.Mark;
import java.util.List;


public class MainActivity extends AppCompatActivity
{
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private MainController controller;
    private ListAdapter mAdapter;
    private MarkRepository markRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        controller = new MainController(
                this,
                markRepository);
                /*
                this,
                Singletons.getGson(),
                Singletons.getSharedPreferences(getApplicationContext()));
                */
      //  controller.onStart();
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
        Intent myIntent = new Intent(MainActivity.this, DetailActivity.class);
        myIntent.putExtra("markKey", Singletons.getGson().toJson(mark));
        MainActivity.this.startActivity(myIntent);
        /*
        String pathName = Constants.BASE_URL2;
        String picName = mark.getUrl();
        String allPath = pathName+picName+".jpg"
        myIntent.putExtra("markKeyPic", allPath);
        */
    }

}