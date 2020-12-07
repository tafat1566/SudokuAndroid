package com.example.projetsudoko;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class niveau_d extends AppCompatActivity {

    public static int x=0;
   public static Niveau_Diff  k;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.niveau_d);


        final Niveau_Diff FACILE, MOYEN, DUR;
        Button Facile = (Button) findViewById(R.id.Facile);

        Facile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i=new Intent(niveau_d.this,MainActivity.class);

                x=0;
                k=Niveau_Diff.FACILE;
                startActivity(i);


            }

        });

        Button Moyen = (Button) findViewById(R.id.Moyen);
        Moyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent ii=new Intent(niveau_d.this,MainActivity.class);
                x=1;
                k=Niveau_Diff.MOYEN;
               // Intent m=new Intent(niveau_d.this,MainActivity.class);

                startActivity(ii);

            }


        });

        Button Dur = (Button) findViewById(R.id.Dur);
        Dur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                Intent iii=new Intent(niveau_d.this,MainActivity.class);
                k=Niveau_Diff.DUR;
                startActivity(iii);
            }

        });
    }

    public static int getX( )
    {
      return x;
    }
    public static Niveau_Diff getY( )
    {
        return k;
    }

}
