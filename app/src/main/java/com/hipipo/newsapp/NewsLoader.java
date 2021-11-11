package com.hipipo.newsapp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

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
import java.util.List;

public class NewsLoader extends AsyncTaskLoader<String> {
   private String newsUrl;


    public NewsLoader(Context context,String url) {
        super(context);
        newsUrl = url;

    }

    @Override
    protected void onStartLoading() {
       forceLoad();
    }

    @Override
    public String loadInBackground() {

            return null;
        }



}
