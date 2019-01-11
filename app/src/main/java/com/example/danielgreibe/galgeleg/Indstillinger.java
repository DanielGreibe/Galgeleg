package com.example.danielgreibe.galgeleg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Indstillinger extends AppCompatActivity implements View.OnClickListener
    {

    TextView textViewGrøn, textViewRød, textViewOrange, textViewHvid, textViewGul;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indstillinger);

        textViewGrøn = findViewById(R.id.textViewGrøn);
        textViewRød = findViewById(R.id.textViewRød);
        textViewOrange = findViewById(R.id.textViewOrange);
        textViewHvid = findViewById(R.id.textViewHvid);
        textViewGul = findViewById(R.id.textViewGul);

        textViewGrøn.setOnClickListener(this);
        textViewRød.setOnClickListener(this);
        textViewOrange.setOnClickListener(this);
        textViewHvid.setOnClickListener(this);
        textViewGul.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
        {
            if (v == textViewGrøn)
                {
                Galgespil.setColor("Green");
                Toast.makeText(this, "Du har valgt Grøn!", Toast.LENGTH_SHORT).show();

                }
        else if (v == textViewRød)
                {
                Galgespil.setColor("Red");
                Toast.makeText(this, "Du har valgt Rød!", Toast.LENGTH_SHORT).show();
                }
            else if (v == textViewOrange)
                {
                Galgespil.setColor("Orange");
                Toast.makeText(this, "Du har valgt Orange!", Toast.LENGTH_SHORT).show();
                }
            else if (v == textViewHvid)
                {
                Galgespil.setColor("White");
                Toast.makeText(this, "Du har valgt Hvid!", Toast.LENGTH_SHORT).show();
                }
            else if (v == textViewGul)
                {
                Galgespil.setColor("Yellow");
                Toast.makeText(this, "Du har valgt Gul!", Toast.LENGTH_SHORT).show();
                } }
        }

