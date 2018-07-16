package com.example.shivanikoul.newsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView =findViewById(R.id.listView);

        String [] channelName ={"ABC NEWS","TIMES OF INDIA","NATIONAL GEOGRAPHIC","GOOGLE NEWS(INDIA)","HACKER NEWS","TECHCRUNCH(CN)"};
        final String [] channelId  ={"abc-news","the-times-of-india","national-geographic","google-news-in","hacker-news","techcrunch-cn"};

        ArrayAdapter<String>  adapter =new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,channelName);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String ChId =channelId[position];
                Intent intent =new Intent(MainActivity.this,Main2Activity.class);
                intent.putExtra("id",ChId);
                startActivity(intent);



            }
        });
    }
}
