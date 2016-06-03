package com.example.android.musicplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        RecyclerView songsRecyclerView = (RecyclerView) findViewById(R.id.list_view);

        if (songsRecyclerView != null) {

            List<Song> songs = Songs.get(getApplicationContext());

            SimpleSongsViewAdapter songsAdapter = new SimpleSongsViewAdapter(getApplicationContext(), songs);

            songsRecyclerView.setAdapter(songsAdapter);

            songsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
    }

}
