package com.example.musicstructureapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class SongActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_list);

        ArrayList<Song> songs = new ArrayList<>();
//todo: Mayank fetch the song details in arraylist
// todo:dynamically from raw song SongName,ArtistName




        songs.add(new Song("Shab Tum Ho",            "Darsan Rawal"));
        songs.add(new Song("Tera Zikr",              "Darsan Rawal"));
        songs.add(new Song("Bol Do Na Zara",         "Armaan Malik"));
        songs.add(new Song("Kaun Tujhe",             "Armaan Malik"));
        songs.add(new Song("Kuch To Hai",            "Armaan Malik"));
        songs.add(new Song("Mujhko Barsaat Bana Lo", "Armaan Malik"));
        songs.add(new Song("Aaj Se Teri",            "Arijit Singh"));
        songs.add(new Song("Sanam Re",               "Arijit Singh"));
        songs.add(new Song("Tum Hi Ho",              "Arijit Singh"));
        songs.add(new Song("Zalima",                 "Arijit Singh"));
        songs.add(new Song("O Saathi ",              "Atif Aslam"));
        songs.add(new Song("Tere Sang Yaara ",       "Atif Aslam"));

        SongAdapter adapter = new SongAdapter(this, songs);

        ListView listView = findViewById(R.id.list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(SongActivity.this, NowPlayActivity.class);
                startActivity(intent);


            }
        });


    }

}

