package com.hipipo.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
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

public class MainActivity extends AppCompatActivity {
    ListView newsList;
    ArrayList newsArrayList;
    NewsAdapter newsAdapter;
    String Topic;
    String publicationDate;
    String newsSect;
    /////////////////
    private  TextView tv;


    String apilink = "https://reqres.in/api/products/3";
    URL url ;
    ///Establish connection
    HttpURLConnection con ;

    BufferedReader Api_bufferresponseReader =null; ///Initailize to NULL as a global variable so its accessed during closing the connection.

    StringBuffer contentBuffer;///the global string buffer is returned to export the content from the class
    ////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayNews();
        new JsonTask().execute(apilink);
        tv= (TextView)findViewById(R.id.apitext) ;


    }


    public void displayNews() {


             //initialise the arraylist object
        newsArrayList = new ArrayList<News>();
        ///add items to the array
        newsArrayList.add(new News(Topic, "12-13-12", "Brief"));

        //attach the custom newsAdapter
        newsAdapter = new NewsAdapter(this, newsArrayList);

        newsList = (ListView) findViewById(R.id.newslist);

        newsList.setAdapter(newsAdapter);


    }

    /////////RUN THE ASYNC TASK
    private class JsonTask extends AsyncTask<String,String,String >{

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
            //Topic = apiResult;
            Log.i("API", "onPostExecute: " + apiResult);
            tv.setText(apiResult);
        }
    }


}