package com.example.tkixi.googlemaps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Tkixi on 11/5/17.
 */


// json format
// Retrieves Data from Url using HTTPURL Connection and file handling methods
public class DownloadUrl {


    public String readUrl(String myUrl) throws IOException{
        String data = "";
        InputStream inputStream = null;
        HttpURLConnection urlConnection = null;


        try {
            // created url
            URL url = new URL(myUrl);
            // opened connection
            urlConnection = (HttpURLConnection) url.openConnection();
            // connected
            urlConnection.connect();

            //reads data
            inputStream = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer sb = new StringBuffer();

            String line = "";
            while((line = br.readLine()) != null){
                sb.append(line);
            }

            data = sb.toString();
            br.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // try to execute, if throws exception, catch will display it
        // finally will always execute
        finally{
            inputStream.close();
            urlConnection.disconnect();
        }

        return data;

    }
}
