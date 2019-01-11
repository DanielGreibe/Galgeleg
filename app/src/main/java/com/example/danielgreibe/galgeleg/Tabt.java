package com.example.danielgreibe.galgeleg;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Tabt extends AppCompatActivity implements View.OnClickListener
    {
    TextView textViewTitle;
    Button buttonPrøvIgen;
    @Override
    protected void onCreate(Bundle savedInstanceState)
        {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabt);


        textViewTitle = findViewById(R.id.textViewTitle);
        buttonPrøvIgen = findViewById(R.id.buttonPrøvIgen);
        buttonPrøvIgen.setOnClickListener(this);

        Integer AntalFejl = getIntent().getIntExtra("AntalFejl" , 0);
        String RigtigeOrd = getIntent().getStringExtra("RigtigeOrd" );

        textViewTitle.setText("Ordet var " + RigtigeOrd + ", og du gættede forkert " + AntalFejl + " gange");

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.xpshutdown);
        mp.start();
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
