package com.example.danielgreibe.galgeleg;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;
import static android.graphics.Color.rgb;

public class Galgespil extends AppCompatActivity implements View.OnClickListener {

    EditText Svar;
    Galgelogik Spil;
    TextView Resultat;
    Button GuessAnswer;
    ImageView GalgeBillede;
    TextView BrugteBogstaver;
    String BrugteBogstaverString = "Brugte Bogstaver: ";
    String[] HighscoreList = new String[10];
    int AntalVundneSpil;
    int CurrentWinStreak;
    int BestWinStreak;
    List<String> NyeOrd;
    static String Color = "White";

public static void setColor(String color)
    {
    Color = color;
    }




@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galgespil);
        farvevalg();

        getSavedData();
        Svar = findViewById(R.id.Svar);
        Spil = new Galgelogik();
        Resultat = findViewById(R.id.Resultat);
        GuessAnswer = findViewById(R.id.GuessAnswer);
        GalgeBillede = findViewById(R.id.GalgeBillede);
        BrugteBogstaver = findViewById(R.id.BrugteBogstaver);

        Svar.setHint("Indtast bogstav her");
        GuessAnswer.setOnClickListener(this);

        NyeOrd = new ArrayList<>();


    Toast toast = Toast.makeText(Galgespil.this, "Henter ord fra DR!", Toast.LENGTH_LONG);
    View view = toast.getView();

    //Gets the TextView from the Toast so it can be editted
    TextView text = view.findViewById(android.R.id.message);
    toast.show();
    //Toast.makeText(getApplicationContext(),"Fetching words from server",Toast.LENGTH_LONG).show();


new AsyncTask()
        {
        @Override
        protected Object doInBackground(Object... arg0)
            {
            try
                {
                Spil.hentOrdFraDr();
                return "Færdig med at hente. Spillet er nu klar";
                } catch (Exception e)
                {
                e.printStackTrace();
                return "Noget gik galt, prøv igen senere: " + e;
                }
            }

        @Override
        protected void onPostExecute(Object resultat)
            {
            Toast toast1 = Toast.makeText(Galgespil.this, "Progress: \n" + resultat, Toast.LENGTH_SHORT);
            View view = toast1.getView();

            //Gets the TextView from the Toast so it can be editted
            TextView text = view.findViewById(android.R.id.message);
            toast1.show();

            //Toast.makeText(getApplicationContext(),"Progress: \n" + resultat,Toast.LENGTH_SHORT).show();
            Resultat.setText(Spil.getSynligtOrd());
            //Svar.setText(Spil.getOrdet());
            }
        }.execute();

    }

private void farvevalg()
    {
    switch (this.Color)
        {
        case "Green": getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.Green));
        break;

        case "Red": getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.Red));
        break;

        case "Orange": getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.Orange));
            break;

        case "White": getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.White));
            break;

        case "Yellow": getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.Yellow));
            break;

        default: getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.White));
        break;
        }
    }

@Override
    public void onClick(View v)
    {
        if(v == GuessAnswer && !Spil.erSpilletSlut())
        {

        opdaterUI();

        if(Spil.erSpilletSlut())
            {
                if (Spil.erSpilletVundet() == true)
                {
                VundetSpil();

                }
                else
                {
                TabtSpil();
                }
            }
        }
    }

private void opdaterUI()
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
    BrugteBogstaver.setText(BrugteBogstaverString.toUpperCase());
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
    Svar.setText("");
    }

private void VundetSpil()
    {
    AntalVundneSpil++;
    CurrentWinStreak++;
    saveHighscore();

    Intent Vundet = new Intent(this, com.example.danielgreibe.galgeleg.Vundet.class);
    Vundet.putExtra("AntalFejl", Spil.getAntalForkerteBogstaver());
    Vundet.putExtra("RigtigeOrd", Spil.getOrdet());
    finish();
    startActivity(Vundet);
    }

private void TabtSpil()
    {
    CurrentWinStreak = 0;
    String Highscore = "Ordlænge: " + Spil.getOrdet().length() + "    Antal fejl: " + Spil.getAntalForkerteBogstaver();
    NyeOrd.add(Highscore);
    saveHighscore();

    Intent Tabt = new Intent(this, com.example.danielgreibe.galgeleg.Tabt.class);
    Tabt.putExtra("AntalFejl", Spil.getAntalForkerteBogstaver());
    Tabt.putExtra("RigtigeOrd", Spil.getOrdet());
    finish();
    startActivity(Tabt);
    }

public void saveHighscore()
    {

        SharedPreferences settings = getSharedPreferences("PREFS",0);
        SharedPreferences.Editor editor = settings.edit();

        editor.putInt("AntalVundneSpil", AntalVundneSpil);
        editor.putInt("AntalVundetIStreg", CurrentWinStreak);

        if (CurrentWinStreak > BestWinStreak)
            {
            BestWinStreak = CurrentWinStreak;
            editor.putInt("BestWinStreak", BestWinStreak);
            }
        editor.commit();
    }

public void getSavedData()
    {
    SharedPreferences settings = getSharedPreferences("PREFS",0);
    CurrentWinStreak =  settings.getInt("AntalVundetIStreg",0);
    AntalVundneSpil = settings.getInt("AntalVundneSpil", 0);
    }
}
