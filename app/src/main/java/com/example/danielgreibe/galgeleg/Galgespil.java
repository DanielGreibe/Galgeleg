package com.example.danielgreibe.galgeleg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Galgespil extends AppCompatActivity implements View.OnClickListener {

    EditText Svar;
    Galgelogik Spil;
    TextView Resultat;
    Button GuessAnswer;
    ImageView GalgeBillede;
    TextView BrugteBogstaver;
    String BrugteBogstaverString = "Brugte Bogstaver: ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galgespil);

        Svar = findViewById(R.id.Svar);
        Spil = new Galgelogik();
        Resultat = findViewById(R.id.Resultat);
        GuessAnswer = findViewById(R.id.GuessAnswer);
        GalgeBillede = findViewById(R.id.GalgeBillede);
        BrugteBogstaver = findViewById(R.id.BrugteBogstaver);

        Svar.setHint("Indtast bogstav her");
        GuessAnswer.setOnClickListener(this);

        //Svar.setOnEditorActionListener(this);
    }

    @Override
    public void onClick(View v)
    {
        if(v == GuessAnswer)
        {

            //Ser om bogstavet er en del af ordet
            Spil.gætBogstav(Svar.getText().toString());

            /* Gennemløber alle brugte bogstaver og udskriver dem til TextViewet BrugteBogstaver */
            for(int i = 0; i < Spil.getBrugteBogstaver().size(); i++)
            {

                ArrayList<String> BrugteBogstaverArrayList =  Spil.getBrugteBogstaver();
                BrugteBogstaverString = BrugteBogstaverString + BrugteBogstaverArrayList.get(i) + " " ;
                this.BrugteBogstaver.setText(BrugteBogstaverString);

            }
            BrugteBogstaver.setText(BrugteBogstaverString);
            BrugteBogstaverString = "Brugte Bogstaver: ";

            int NumberOfErrors = Spil.getAntalForkerteBogstaver();

            //Ændrer billede afhængigt af Fejl's værdi
            switch (NumberOfErrors)
            {
                case 0:
                break;

                case 1: GalgeBillede.setImageResource(R.drawable.forkert1);
                break;

                case 2: GalgeBillede.setImageResource(R.drawable.forkert2);
                break;

                case 3: GalgeBillede.setImageResource(R.drawable.forkert3);
                break;

                case 4: GalgeBillede.setImageResource(R.drawable.forkert4);
                break;

                case 5: GalgeBillede.setImageResource(R.drawable.forkert5);
                break;

                case 6: GalgeBillede.setImageResource(R.drawable.forkert6);
                break;
            }
            Resultat.setText(Spil.getSynligtOrd());

            if(Spil.erSpilletSlut())
            {
                if (Spil.erSpilletVundet() == true)
                {
                    Resultat.setText("Du vandt, Tillykke!");
                }
                else
                {
                    Log.e("Debug", "SpillerErTabt");
                    Resultat.setText("Desværre, du tabte. Bedre held næste gang!");
                }
            }
        }
    }











    /*@Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if(i == EditorInfo.IME_ACTION_SEND || i == EditorInfo.IME_NULL || keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN)
        {
            Resultat.setText("Test123");
            return true;
        }
        return false;
    }
    */
}
