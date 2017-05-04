package com.example.jorendegoede.jorendegoede_pset3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText etMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // searchbox
        etMovie = (EditText) findViewById(R.id.etMovie);
        assert etMovie != null;
    }

    // allows user to search
    public void movieSearch(View view) {
        // user input.
        String movieSearch = etMovie.getText().toString();

        // call AsyncTask
        MovieAsyncTask asyncTask = new MovieAsyncTask(this);
        asyncTask.execute(movieSearch);

        // clear searchbox.
        etMovie.getText().clear();
    }

    // got to search results/ data activity
    public void movieStartIntent(ArrayList<String> movieNames, ArrayList<String> movieData) {

        if (movieNames != null && movieData != null) {
            Intent dataIntent = new Intent(this, DataActivity.class);

            dataIntent.putExtra("names", movieNames);
            dataIntent.putExtra("data", movieData);
            this.startActivity(dataIntent);
        }
    }
}
