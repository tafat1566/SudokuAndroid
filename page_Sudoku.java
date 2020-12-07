package com.example.projetsudoko;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.style.BackgroundColorSpan;
import android.view.View;
import android.widget.Button;
import java.util.Timer;
import androidx.appcompat.app.AppCompatActivity;




public class page_Sudoku extends AppCompatActivity {

        public static int timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.pagesudoku);
        final Timer timer = new Timer();


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable()
        {
            public void run()
            {
                Intent intent = new Intent(page_Sudoku.this, intmenu.class);
                startActivity(intent);
            }
        }, 2000L);

    }
}
