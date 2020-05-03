package com.example.projetmobile;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.SharedPreferences;
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
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;


public class MainActivity extends AppCompatActivity {
    private static final String BASE_URL = "https://raw.githubusercontent.com";
    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private SharedPreferences sharedPreferences;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences(Constants.KEY_APP_MARK, Context.MODE_PRIVATE);
        gson = new GsonBuilder()
                .setLenient()
                .create();

        List<Mark> markList = getDataFromCache();
        if(markList != null)
        {
            showList(markList);
        }
        else makeApiCall();
    }

    private List<Mark> getDataFromCache()
    {
        String jsonMark = sharedPreferences.getString(Constants.KEY_MARK_LIST,null);
        if(jsonMark == null)
        {
            return null;
        }

        else
        {
            Type listType = new TypeToken<List<Mark>>(){}.getType();
            return gson.fromJson(jsonMark, listType);
        }
    }

    private void makeApiCall() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        MarkApi markApi = retrofit.create(MarkApi.class);
        Call<RestMarkResponse> call = markApi.getMarkResponse();
        call.enqueue(new Callback<RestMarkResponse>() {
            @Override
            public void onResponse(Call<RestMarkResponse> call, Response<RestMarkResponse> response)
            {
                if (response.isSuccessful() && response.body() != null) {
                    List<Mark> markList = response.body().getResults();
                    saveList(markList);
                    showList(markList);
                } else showError();
            }
            @Override
            public void onFailure(Call<RestMarkResponse> call, Throwable t)
            {
                showError();
            }
        });
    }



    private void showList(List<Mark> markList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new ListAdapter(markList);
        recyclerView.setAdapter(mAdapter); }



    private void saveList(List<Mark> markList) {
        String jsonString = gson.toJson(markList);
        sharedPreferences
                .edit()
                .putString(Constants.KEY_MARK_LIST, jsonString)
                .apply();
        Toast.makeText(getApplicationContext(),"List Saved", Toast.LENGTH_SHORT).show();
    }



    private void showError() {
        Toast.makeText(getApplicationContext(),"API Error", Toast.LENGTH_SHORT).show(); }
}