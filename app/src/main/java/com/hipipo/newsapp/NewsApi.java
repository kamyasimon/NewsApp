package com.hipipo.newsapp;


import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class NewsApi extends AsyncTask<String,String,String >{
    private ListView newsList;
    ArrayList newsArrayList;
    NewsAdapter newsAdapter;
    String Topic;
    String publicationDate;
    String newsSect;
    /////////////////
    //String apilink = "https://reqres.in/api/products/3";
   // String apilink = "https://content.guardianapis.com/search?q=debate&tag=politics/politics&from-date=2014-01-01&api-key=test";
    URL url ;
    ///Establish connection
    HttpURLConnection con ;

    BufferedReader Api_bufferresponseReader =null; ///Initailize to NULL as a global variable so its accessed during closing the connection.

    StringBuffer contentBuffer;///the global string buffer is returned to export the content from the class

    @Override
    protected String doInBackground(String... strings) {
        return null;
    }
    ////////////////////////




}
