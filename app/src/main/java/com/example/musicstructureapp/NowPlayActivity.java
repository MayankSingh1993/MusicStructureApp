package com.example.musicstructureapp;


import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;


public class NowPlayActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton playButton;
    private MediaPlayer mediaPlayer;
    private int pauseCurrentPosition;


    SeekBar seekBar;
    Handler handler;
    Runnable runnable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_play);

        //Get song name textview reference
        TextView textView = findViewById(R.id.song_name);
        //Retrieve song name from Intent extra
        String songName = getIntent().getStringExtra("song_name");
        textView.setText(songName);
        //SeekBar reference
        seekBar = findViewById(R.id.seekBar);
        //Play image button reference
        playButton = findViewById(R.id.playButton);
        //forward image button reference
        ImageButton forwardButton = findViewById(R.id.forwardButton);
        //Back image button reference
        ImageButton backButton = findViewById(R.id.backwardButton);
        //EndDuration TextView
        TextView textView2 = findViewById(R.id.endDuration);
        //retrieve song duration from intent extra
        int songDuration = Integer.parseInt(getIntent().getStringExtra("duration"));

        //retrieve song id(for building song uri) from intent extra
        long songId = Long.parseLong(getIntent().getStringExtra("song_id"));
        //build song uri
        final Uri contentUri = ContentUris.withAppendedId(android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, songId);
        // media player instance
        mediaPlayer = new MediaPlayer();
        //set AudioStreamType
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            //set DataSource
            mediaPlayer.setDataSource(getApplicationContext(), contentUri);
            mediaPlayer.prepare();
            seekBar.setMax(songDuration);
            //set duration to textView2
            setFormattedEndDuration(songDuration, textView2);

        } catch (IOException e) {
            e.printStackTrace();
        }

        handler = new Handler();

        playButton.setOnClickListener(this);
        forwardButton.setOnClickListener(this);
        backButton.setOnClickListener(this);

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {


                mediaPlayer.start();
                changeSeekbar();

            }
        });


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }


    private void setFormattedEndDuration(int duration, final TextView textView) {


        duration = duration / 1000;
        int minutes = ((duration) % 3600) / 60;
        int secs = duration % 60;

        @SuppressLint("DefaultLocale") String time = String.format("%02d:%02d", minutes, secs);
        textView.setText(time);

    }


    public void changeSeekbar() {
        seekBar.setProgress(mediaPlayer.getCurrentPosition());
        if (mediaPlayer.isPlaying()) {
            runnable = new Runnable() {
                @Override
                public void run() {
                    changeSeekbar();
                }
            };
            handler.postDelayed(runnable, 1000);
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.playButton:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    pauseCurrentPosition = mediaPlayer.getCurrentPosition();
                    playButton.setImageResource(R.drawable.baseline_play_arrow_black_48dp);
                } else {

                    mediaPlayer.seekTo(pauseCurrentPosition);
                    mediaPlayer.start();
                    playButton.setImageResource(R.drawable.baseline_pause_black_48dp);
                    changeSeekbar();

                }
                break;
            case R.id.forwardButton:
                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() + 5000);
                break;
            case R.id.backwardButton:
                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() - 5000);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mediaPlayer.stop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
    }

}