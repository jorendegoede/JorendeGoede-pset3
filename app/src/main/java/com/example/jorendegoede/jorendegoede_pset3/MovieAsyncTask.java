package com.example.jorendegoede.jorendegoede_pset3;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Joren de Goede on 20-4-2017.
 */

public class MovieAsyncTask extends AsyncTask<String, Integer, String> {
    Context context;
    MainActivity mainAct;

    public MovieAsyncTask(MainActivity main) {
        this.mainAct = main;
        this.context = this.mainAct.getApplicationContext();
    }

    @Override
    protected void onPreExecute() {
        Toast.makeText(context, "searching for movies...", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected String doInBackground(String... params) {
        return HttpRequestHelper.downloadFromServer(params);
    }

    @Override
    protected void onPostExecute(String result) {
        Log.d("Result", result);
        super.onPostExecute(result);

        ArrayList<String> movieNames = null;
        ArrayList<String> movieData = null;

        try {
            JSONObject movieStreamObj = new JSONObject(result);

            // catch possible errors.
            String test = movieStreamObj.getString("Response");
            if (test.equals("True")){

                // read JSON into relevant lists.
                JSONArray resultsArr = movieStreamObj.getJSONArray("Search");
                movieNames = new ArrayList<String>();
                movieData = new ArrayList<String>();
                for (int i=0; i < resultsArr.length(); i++){
                    JSONObject object = resultsArr.getJSONObject(i);
                    String title = object.getString("Title");

                    movieNames.add(title);
                }

                // go to next intent if successful.
                assert movieNames != null;
                this.mainAct.movieStartIntent(movieNames, movieData);
            }
            else{
                Toast.makeText(context, "No results", Toast.LENGTH_SHORT).show();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
