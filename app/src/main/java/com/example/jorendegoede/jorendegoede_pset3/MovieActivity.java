package com.example.jorendegoede.jorendegoede_pset3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Joren de Goede on 3-5-2017.
 */

public class MovieActivity extends AppCompatActivity {
    JSONObject data = null;
    TextView title;
    TextView year;
    TextView plot;
    ImageView poster;
    Button addremove;

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putString("title", title.getText().toString());
        savedInstanceState.putString("year", year.getText().toString());
        savedInstanceState.putString("plot", plot.getText().toString());
        savedInstanceState.putString("addremove", addremove.getText().toString());
        try {
            savedInstanceState.putString("poster", data.getString("Poster"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        // movie info
        title = (TextView) findViewById(R.id.titlemovie);
        year = (TextView) findViewById(R.id.year);
        plot = (TextView) findViewById(R.id.plot);
        poster = (ImageView) findViewById(R.id.poster);
        addremove = (Button) findViewById(R.id.addremove);

        Bundle extras = getIntent().getExtras();

        try {
            data = new JSONObject(extras.getString("data"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (savedInstanceState == null) {
            try {
                title.setText(data.getString("Title"));
                year.setText(data.getString("Year"));
                plot.setText(data.getString("Plot"));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else{
            title.setText(savedInstanceState.getString("title"));
            year.setText(savedInstanceState.getString("year"));
            plot.setText(savedInstanceState.getString("plot"));
            addremove.setText(savedInstanceState.getString("addremove"));

        }
    }
}