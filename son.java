package com.example.projetsudoko;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class son extends AppCompatActivity {



    private MediaPlayer mediaPlayer;
    private SeekBar seekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.son);
        this.seekBar=(SeekBar)findViewById(R.id.sound_bar);

        this.mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.my_sound);

        Timer timer= new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                seekBar.setProgress(mediaPlayer.getCurrentPosition()/1000);

            }
        },1000,1000);


    }

    public void activer(View view) {


        Button button=(Button)view;
       if(mediaPlayer.isPlaying())
       {
           mediaPlayer.pause();
           button.setText((getString(R.string.jour_music_btn)));
       }
       else {
           mediaPlayer.start();
           button.setText(getString(R.string.pause_music_btn));
       }
       }
    }


