package com.example.jorendegoede.jorendegoede_pset3;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Joren de Goede on 20-4-2017.
 */

public class HttpRequestHelper {

    protected  static synchronized String downloadFromServer(String... params){
        String result = "";
        String chosenTags = params[0];

        // url for moviedatabase
        URL resultURL = null;
        String urlPlaceholder = "http://omdbapi.com/?s=" + chosenTags;

        if (params.length > 1) {
            String plus = "+";
            for (int i = 1; i < params.length; i++) {
                urlPlaceholder += plus;
                urlPlaceholder += params[i];
            }
        }

        try {
            resultURL = new URL(urlPlaceholder);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // connect to new url
        HttpURLConnection connect;

        if (resultURL != null) {
            try {
                connect = (HttpURLConnection) resultURL.openConnection();
                connect.setRequestMethod("GET");
                connect.connect();

                Integer responseCode = connect.getResponseCode();
                if (responseCode >= 200 && responseCode < 300) {
                    BufferedReader bReader = new BufferedReader(new InputStreamReader(connect.getInputStream()));
                    String line;
                    while ((line = bReader.readLine()) != null) {
                        result += line;
                    }
                }
                else {
                    result = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
