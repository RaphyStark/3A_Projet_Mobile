package com.example.projetmobile.presentation.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.projetmobile.R;
import com.example.projetmobile.Singletons;
import com.example.projetmobile.presentation.model.Mark;


public class DetailActivity extends AppCompatActivity
{
    private TextView txtDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        txtDetail = findViewById(R.id.description_mark);
        Intent intent = getIntent();
        String markJson = intent.getStringExtra("markKey");
        Mark mark = Singletons.getGson().fromJson(markJson, Mark.class);
        showDetail(mark);
    }

    private void showDetail(Mark mark)
    {
        txtDetail.setText(mark.getName());
    }
}