package com.example.musicstructureapp;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


public class NowPlayActivity extends AppCompatActivity {


    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_play);


    }


    public void onButtonClick(View view) {


        mediaPlayer = MediaPlayer.create(this, R.raw.metallica_the_day_that_never_comes);
        //TextView textView1= findViewById(R.id.startDuration);
        TextView textView2 = findViewById(R.id.endDuration);
        int songDuration = mediaPlayer.getDuration();
        runTimer(songDuration, textView2);
        mediaPlayer.start();
        mediaPlayer.setScreenOnWhilePlaying(true);


    }


    public void onStopButton(View view) {
        mediaPlayer.stop();


    }

    private void runTimer( int duration, final TextView textView) {



        duration=duration/1000;
        int minutes = ((duration) % 3600) / 60;
        int secs = duration % 60;

        String time = String.format("%02d:%02d",minutes, secs);
        textView.setText(time);

    }

    public void onLoop(View view) {
        mediaPlayer.setLooping(true);
    }

    public void onPause(View view) {
        mediaPlayer.pause();
    }
}
