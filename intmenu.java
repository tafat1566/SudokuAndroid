package com.example.projetsudoko;

import android.content.Intent;
import android.drm.ProcessedData;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;



public class intmenu extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intmenu);

        Button Joeur = (Button) findViewById(R.id.Joeur);
        Joeur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i=new Intent(intmenu.this,niveau_d.class);

                startActivity(i);
            }

        });

        Button Son = (Button) findViewById(R.id.Son);
        Son.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i=new Intent(intmenu.this,son.class);

                startActivity(i);
            }

        });


        Button Aide = (Button) findViewById(R.id.Aide);
        Aide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i=new Intent(intmenu.this,Aide.class);

                startActivity(i);
            }

        });
        Button Quitter = (Button) findViewById(R.id.Quiter);
        Quitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                finish();
               // System.exit(0);

            }

        });

    }
}
