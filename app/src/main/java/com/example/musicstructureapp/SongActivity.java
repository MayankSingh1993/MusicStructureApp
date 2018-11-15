package com.example.musicstructureapp;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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
        final ArrayList<Song> songList = listAllSongs();
        final SongAdapter adapter = new SongAdapter(SongActivity.this, songList);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Song songSelectedPath = songList.get(position);
                //Song Name
                String songName = songSelectedPath.getmSongName();
                //Song Duration
                int duration = songSelectedPath.getDuration();
                String songDuration = Integer.toString(duration);
                //Song URI
                long strSelectedPath = songSelectedPath.getmSongId();
                String path = Long.toString(strSelectedPath);
                Intent now_play = new Intent(SongActivity.this, NowPlayActivity.class);
                //Send songName, path, songDuration parameter to NowPlayActivity from this Activity
                now_play.putExtra("song_name", songName);
                now_play.putExtra("song_id", path);
                now_play.putExtra("duration", songDuration);
                //Start NowPlayActivity
                startActivity(now_play);


            }
        });


    }

    // Fetch list of all songs available in External Storage
    private ArrayList<Song> listAllSongs() {
        ArrayList<Song> songs = new ArrayList<>();
        ContentResolver musicResolve = getContentResolver();
        Uri allSongsUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        Cursor cursor = musicResolve.query(allSongsUri, null, null, null, null);
        if (cursor != null && cursor.moveToNext()) {
            do {

                Song song = new Song();

                String data = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                String[] res = data.split("\\.");
                song.setmSongName(res[0]);
                song.setmSongId(cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media._ID)));
                song.setmArtistName(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)));
                song.setDuration(Integer.parseInt(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION))));


                songs.add(song);
            } while (cursor.moveToNext());
        }
        assert cursor != null;
        cursor.close();
        return songs;
    }


}

