package com.example.musicstructureapp;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


public class NowPlayActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton playButton, forwardButton, backButton;
    private MediaPlayer mediaPlayer;
    private int pauseCurrentPosition;
    SeekBar seekBar;
    Handler handler;
    Runnable runnable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_play);

        seekBar = findViewById(R.id.seekBar);

        mediaPlayer = MediaPlayer.create(this, R.raw.metallica_the_day_that_never_comes);

        playButton = findViewById(R.id.playButton);

        forwardButton = findViewById(R.id.forwardButton);

        backButton = findViewById(R.id.backwardButton);

        TextView textView2 = findViewById(R.id.endDuration);

        final int songDuration = mediaPlayer.getDuration();

        runTimer(songDuration, textView2);

        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        handler = new Handler();

        playButton.setOnClickListener(this);
        forwardButton.setOnClickListener(this);
        backButton.setOnClickListener(this);

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                seekBar.setMax(songDuration);

                mediaPlayer.start();
                changeSeekbar();
            }
        });

        //mediaPlayer.setScreenOnWhilePlaying(true);
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


    private void runTimer(int duration, final TextView textView) {


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
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            Toast.makeText(NowPlayActivity.this, "I'm done", Toast.LENGTH_SHORT).show();
                        }
                    });
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

    public void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
            mediaPlayer.stop();
      }



}