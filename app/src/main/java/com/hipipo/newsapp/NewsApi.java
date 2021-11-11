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

    //String apilink = "https://reqres.in/api/products/3";
   // String apilink = "https://content.guardianapis.com/search?q=debate&tag=politics/politics&from-date=2014-01-01&api-key=test";
    URL url ;
    ///Establish connection
    HttpURLConnection con ;

    BufferedReader Api_bufferresponseReader =null; ///Initailize to NULL as a global variable so its accessed during closing the connection.

    StringBuffer contentBuffer;///the global string buffer is returned to export the content from the class
    ////////////////////////
    @Override
    protected String doInBackground(String... params) {

        try {
            ////establish the URL link for the API
            url = new URL(params[0]);
            ////Open a connection to the HTTP SERVER
            con= (HttpURLConnection) url.openConnection();
            /////Connect to the Server OPen DIRECTLY
            con.connect();
            ///Create an input stream to store the Data
            InputStream Api_response = con.getInputStream();
            //Create a Buffer Reader to be able to READ through the API RESPONSE STREAM;
            ///BufferedReader Api_bufferresponse = new BufferedReader(new InputStreamReader(Api_response));///remember the global variable up
            Api_bufferresponseReader = new BufferedReader(new InputStreamReader(Api_response));

            ///Create a string buffer object that will buffer the buffer response into strings
            contentBuffer = new StringBuffer();
            //Now establish the content to a string and loop through it.

            String data="";

            //while loop to read the Api_bufferresponse and check if its not empty
            while ((data = Api_bufferresponseReader.readLine()) != null){
                //append the buffer response to data
                contentBuffer.append(data);

            }


            //Return the buffer to be accessed by the PostExecute.
            return contentBuffer.toString();



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ////close the connections
        finally {
            //check if the connection is open
            if(con != null){
                ///close the connection
                con.disconnect();
            }

            ///use the Api_bufferResponseReader global variable and surround with a try/catch
            try {
                ///check if the Api_bufferResponseReader is not null
                if(Api_bufferresponseReader != null) {
                    Api_bufferresponseReader.close();
                } //ending brace for the bufferReader if statement
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        return null;
    }

    @Override
    protected void onPostExecute(String apiResult) {
        super.onPostExecute(apiResult);
        ///Create a string variable for the buffer returned
        String finalNewsJson = apiResult;

        ///Access into the JSON object Make sure you create a JsonException in the catch
        JSONObject newsRoot = null;
        try {
            newsRoot = new JSONObject(finalNewsJson);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Acces JsonObject of reponse
        JSONObject newsResponse = null;
        try {
            newsResponse = newsRoot.getJSONObject("response");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Acces JsonArray of results
        JSONArray newsResults = null;
        try {
            newsResults = newsResponse.getJSONArray("results");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //create another string buffer to APPEND the Json Results
        StringBuffer finalNewsBuffer = new StringBuffer();

        //Access the multiple ojects next, start TEST a single object[0]
        for(int i=0;i < newsResults.length();i++){
            JSONObject actualNews = null;
            try {
                actualNews = newsResults.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            //Access the actualNews Keys, begining with sectionName
            String sectionName = null;
            try {
                sectionName = actualNews.getString("sectionName");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String webTitle = null;
            try {
                webTitle = actualNews.getString("webTitle");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String time = null;
            try {
                time = actualNews.getString("webPublicationDate");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            MainActivity mainActivity = new MainActivity();
            mainActivity.displayNews(sectionName,webTitle,time);

            finalNewsBuffer.append(sectionName + "," + webTitle + ","+ time);
        }


    }

}
