package com.example.projetmobile.presentation.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetmobile.Constants;
import com.example.projetmobile.R;
import com.example.projetmobile.Singletons;
import com.example.projetmobile.presentation.controller.DetailController;
import com.example.projetmobile.presentation.model.Mark;
import com.squareup.picasso.Picasso;


public class DetailActivity extends AppCompatActivity {
    private DetailController controller;
    private TextView txtDetail;
    private ImageView imageDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        controller = new DetailController(
                this,
                Singletons.getGson(),
                Singletons.getSharedPreferences(getApplicationContext()));

        controller.onStart();
    }

    public void showDetail()
    {
        txtDetail = findViewById(R.id.description_mark);
        imageDetail = findViewById(R.id.image_mark);
        Intent intent = getIntent();
        String markJson = intent.getStringExtra("markKey");
        Mark mark = Singletons.getGson().fromJson(markJson, Mark.class);
        txtDetail.setText(mark.getName());

        String pathName = Constants.BASE_URL2;
        String picName = mark.getUrl();
        String allPath = pathName+picName+".jpg";
        Picasso.get()
                .load(allPath)
                .into(imageDetail);
    }

    public void showError()
    {
        Toast.makeText(getApplicationContext(),"API Error", Toast.LENGTH_SHORT).show();
    }
}