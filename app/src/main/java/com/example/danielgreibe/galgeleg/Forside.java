package com.example.danielgreibe.galgeleg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Forside extends AppCompatActivity implements View.OnClickListener {

    /* Variables that work in the scope of the entire intent. */
    Button StartSpil, Indstillinger;
    TextView Overskrift;
    ImageView ForsideBillede;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forside);

        StartSpil = findViewById(R.id.ForsideStartSpilButton);
        Indstillinger = findViewById(R.id.ForsideIndstillingerButton);
        Overskrift = findViewById(R.id.ForsideTextView);
        ForsideBillede = findViewById(R.id.ForsideImage);

        StartSpil.setOnClickListener(this);
        Indstillinger.setOnClickListener(this);


    }

    public void onClick(View v)
    {
        if (v == StartSpil)
        {
            Intent GalgeSpil = new Intent(this, Galgespil.class);
            startActivity(GalgeSpil);
        }
        else if (v == Indstillinger)
        {
            Intent Indstillinger = new Intent(this, Indstillinger.class);
            startActivity(Indstillinger);
        }
    }
}
