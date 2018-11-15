package com.example.musicstructureapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_READ_EXTERNAL_STORAGE = 1;

    @Override
    protected void onStart() {
        super.onStart();
        askForPermission();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //All songs TextView
        TextView allSongs = findViewById(R.id.all_songs);
        // Set a click listener on that View
        allSongs.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link NumbersActivity} & Start the new activity
                Intent songsIntent = new Intent(MainActivity.this, SongActivity.class);
                startActivity(songsIntent);
            }
        });

        //Favourite songs TextView
        TextView favSongs = findViewById(R.id.favorite_songs);
        // Set a click listener on that View
        favSongs.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link NumbersActivity} & Start the new activity
                Intent songsIntent = new Intent(MainActivity.this, SongActivity.class);
                startActivity(songsIntent);
            }
        });

        //Recent songs TextView
        TextView recSongs = findViewById(R.id.recent_played);
        // Set a click listener on that View
        recSongs.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link NumbersActivity} & Start the new activity
                Intent songsIntent = new Intent(MainActivity.this, SongActivity.class);
                startActivity(songsIntent);
            }
        });

    }

    /**
     * Asking for READING EXTERNAL STORAGE to get MUSIC
     */
    private void askForPermission() {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

                //This is called if user has denied the permission before
                //In this case I am just asking the permission again

                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

            } else {

                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }
        }

    }


    /**
     * When the user responds to your app's permission request,
     * the system invokes your app's onRequestPermissionsResult() method,
     * passing it the user response.
     * Your app has to override that method to find out whether the permission was granted.
     * The callback is passed the same request code you passed to requestPermissions().
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_READ_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Intent intent = new Intent(MainActivity.this, NoSongActivity.class);
                    startActivity(intent);

                }

            }


        }
    }

}


