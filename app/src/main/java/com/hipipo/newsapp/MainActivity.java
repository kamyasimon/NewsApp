package com.hipipo.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView newsList;
    ArrayList newsArrayList;
    NewsAdapter newsAdapter;
    String Topic;
    String publicationDate;
    String newsSect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayNews();



    }


    public void displayNews() {
            NewsApi newsApi = new NewsApi();
            // newsApi.apiConnect();
             Topic= String.valueOf(newsApi.contentBuffer);


             //initialise the arraylist object
        newsArrayList = new ArrayList<News>();
        ///add items to the array
        newsArrayList.add(new News(Topic, "12-13-12", "Brief"));

        //attach the custom newsAdapter
        newsAdapter = new NewsAdapter(this, newsArrayList);

        newsList = (ListView) findViewById(R.id.newslist);

        newsList.setAdapter(newsAdapter);


    }


}