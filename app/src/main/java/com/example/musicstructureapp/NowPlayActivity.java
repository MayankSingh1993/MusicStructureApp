package com.example.musicstructureapp;


import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


public class NowPlayActivity extends AppCompatActivity {


    private MediaPlayer mediaPlayer;
    private int pauseCurrentPosition;
    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_play);
        seekBar=findViewById(R.id.seekBar2);


    }


    public void onButtonClick(View view) {


        if(mediaPlayer==null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.metallica_the_day_that_never_comes);
            //TextView textView1= findViewById(R.id.startDuration);
            TextView textView2 = findViewById(R.id.endDuration);
            int songDuration = mediaPlayer.getDuration();
            runTimer(songDuration, textView2);
            mediaPlayer.start();
            mediaPlayer.setScreenOnWhilePlaying(true);
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    Toast.makeText(NowPlayActivity.this,"I'm done",Toast.LENGTH_SHORT).show();
                }
            });
        }
        else if(!mediaPlayer.isPlaying()){

            mediaPlayer.seekTo(pauseCurrentPosition);
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    Toast.makeText(NowPlayActivity.this,"I'm done",Toast.LENGTH_SHORT).show();
                }
            });

        }


    }


    public void onStopButton(View view) {
        if (mediaPlayer!=null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer=null;
        }

    }

    private void runTimer( int duration, final TextView textView) {



        duration=duration/1000;
        int minutes = ((duration) % 3600) / 60;
        int secs = duration % 60;

        @SuppressLint("DefaultLocale") String time = String.format("%02d:%02d",minutes, secs);
        textView.setText(time);

    }

    public void onLoop(View view) {
        if(!mediaPlayer.isLooping()) {
            mediaPlayer.setLooping(true);

        }
    }

    public void onPause(View view)
    {
        if(mediaPlayer!=null) {
            mediaPlayer.pause();
            pauseCurrentPosition=mediaPlayer.getCurrentPosition();
        }
    }


}
