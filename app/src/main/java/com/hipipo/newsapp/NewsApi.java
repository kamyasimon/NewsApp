package com.hipipo.newsapp;


import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NewsApi extends AsyncTask<URL,String,String> {
    String apilink = "http://content.guardianapis.com/search?q=debates&api-key=test";
    URL url ;
    ///Establish connection
    HttpURLConnection con ;

    BufferedReader Api_bufferresponseReader =null; ///Initailize to NULL as a global variable so its accessed during closing the connection.

    StringBuffer contentBuffer;///the global string buffer is returned to export the content from the class
    public String topicString(){
        return apilink;
    }

    public String apiConnect(){

        return contentBuffer.toString();
    }


    @Override
    protected String doInBackground(URL... urls) {

        try {
            ////establish the URL link for the API
            url = new URL(apilink);
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
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
