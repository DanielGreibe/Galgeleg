package com.example.danielgreibe.galgeleg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Indstillinger extends AppCompatActivity implements View.OnClickListener
    {

    ImageButton ButtonEnglish, ButtonDanish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indstillinger);

        ButtonEnglish = findViewById(R.id.ButtonEnglish);
        ButtonDanish = findViewById(R.id.ButtonDanish);

        ButtonEnglish.setOnClickListener(this);
        ButtonDanish.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
        {
            if ( v == ButtonEnglish)
                {
                //TODO Change language of app to English
                }
            if ( v == ButtonDanish)
                {
                //TODO Change language of app to Danish
                }
        }
    }
