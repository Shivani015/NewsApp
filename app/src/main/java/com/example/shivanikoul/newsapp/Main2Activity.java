package com.example.shivanikoul.newsapp;

import android.content.Intent;
import android.net.Network;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.networkutil.NetworkUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class Main2Activity extends AppCompatActivity {
    RecyclerView recyclerView;
    String id;
    String API_KEY = "245a1fc7a8414ed1b4fc1ef6ffb86356";
    String URL = "https://newsapi.org/v2/top-headlines?sources=";

    String data;
    Adapter adapter;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recyclerView = findViewById(R.id.recyclerView);
        progressBar =findViewById(R.id.progressBar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        adapter = new Adapter(this);
        recyclerView.setAdapter(adapter);

        new FetchNews().execute();

    }

    class FetchNews extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            String finalUrl = URL+id+"&apiKey=245a1fc7a8414ed1b4fc1ef6ffb86356";
            data = NetworkUtil.makeServiceCall(finalUrl);
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(VISIBLE);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar.setVisibility(INVISIBLE);
            ArrayList<News> newsArrayList = new ArrayList<>();

            if (data == null) {
                Toast.makeText(Main2Activity.this, "No data returned", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    JSONObject jasonData = new JSONObject(data);
                    JSONArray articleArray = jasonData.getJSONArray("articles");

                    for (int i = 0; i < articleArray.length(); i++) {
                        JSONObject news = articleArray.getJSONObject(i);

                        String title = news.getString("title");
                        String description = news.getString("description");
                        String imgUrl = news.getString("urlToImage");

                        newsArrayList.add(new News(title, description, imgUrl));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            adapter.swap(newsArrayList);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(0);
        }
    }

}