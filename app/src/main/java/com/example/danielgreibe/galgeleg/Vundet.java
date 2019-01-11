package com.example.danielgreibe.galgeleg;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.shapes.Shape;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Size;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import nl.dionsegijn.konfetti.KonfettiView;

public class Vundet extends AppCompatActivity implements View.OnClickListener
    {
    TextView textViewTitle;
    Button buttonPrøvIgen;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState)
        {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vundet);

        textViewTitle = findViewById(R.id.textViewTitle);
        buttonPrøvIgen = findViewById(R.id.buttonPrøvIgen);
        buttonPrøvIgen.setOnClickListener(this);


        Integer AntalFejl = getIntent().getIntExtra("AntalFejl" , 0);
        String RigtigeOrd = getIntent().getStringExtra("RigtigeOrd" );

        textViewTitle.setText("Ordet var " + RigtigeOrd + ", og du gættede forkert " + AntalFejl + " gange");

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.winsound);
        mp.start();

        //Display confetti and center of screen
        Display display = getWindowManager(). getDefaultDisplay();
        Point size = new Point();
        display. getSize(size);
        int width = size. x;
        int height = size. y;


        final KonfettiView konfettiView = findViewById(R.id.viewKonfetti);
        konfettiView.build()
                    .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                    .setDirection(0.0, 359.0)
                    .setSpeed(1f, 5f)
                    .setFadeOutEnabled(true)
                    .setTimeToLive(2000L)
                    .addShapes(nl.dionsegijn.konfetti.models.Shape.RECT, nl.dionsegijn.konfetti.models.Shape.CIRCLE)
                    .addSizes(new nl.dionsegijn.konfetti.models.Size(12,  5))
                    .setPosition((0), ((float)width), (0), ((float)height))
                    .streamFor(300, 5000L);
        }

    @Override
    public void onClick(View view)
        {
        if ( view == buttonPrøvIgen)
            {
            Intent Galgespil = new Intent(this, Galgespil.class);
            finish();
            startActivity(Galgespil);
            }
        }
    }
