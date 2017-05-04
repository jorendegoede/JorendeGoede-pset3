package com.example.jorendegoede.jorendegoede_pset3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Joren de Goede on 20-4-2017.
 */

public class DataActivity extends AppCompatActivity {
    ListView lvItems;
    ArrayList<String> movieNames;
    ArrayList<String> movieData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        // listview of results
        lvItems = (ListView) findViewById(R.id.listViewID);

        // retrieved data
        Bundle extras = getIntent().getExtras();
        movieNames = (ArrayList<String>) extras.getSerializable("names");
        movieData = (ArrayList<String>) extras.getSerializable("data");

        // call movie adapter.
        makeMovieAdapter();
    }

    // movie adapter
    public void makeMovieAdapter() {
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, android.R.id.text1, movieNames);
        lvItems = (ListView) findViewById(R.id.listViewID);
        assert lvItems != null;
        lvItems.setAdapter(arrayAdapter);
    }
}