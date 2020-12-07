package com.example.projetsudoko;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    private Chronometer chronometer;
    private boolean running;
    public int a=0;
    Button af;
    //public static int x=Vue_Jeux.verifier( );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button ButonQ = (Button) findViewById(R.id.button2);
        ButonQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

               System.exit(0);

            }

        });

        chronometer=findViewById(R.id.chronometer);
        chronometer.setBase(SystemClock.elapsedRealtime());

        if(Vue_Jeux.verifier()==0)
            chronometer.start();

        else  if (Vue_Jeux.verifier()==1)
        {
            System.exit(1);
            chronometer.stop();
        }




    }


}
