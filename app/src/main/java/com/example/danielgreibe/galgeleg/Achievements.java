package com.example.danielgreibe.galgeleg;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Achievements extends AppCompatActivity implements View.OnClickListener
    {
    Integer AntalVundneIStreg;
    Integer AntalVundneTotal;
    Integer BestWinStreak;

    TextView AchievementsAntalVundne;
    TextView AchievementsWinstreak;
    TextView AchievementsLængsteStreak;

    int CurrentWinStreak;
    int AntalVundneSpil;

    Button resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
        {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievements);
        loadData();

        AchievementsAntalVundne = findViewById(R.id.AchievementsAntalVundne);
        AchievementsWinstreak = findViewById(R.id.AchievementsWinstreak);
        AchievementsLængsteStreak = findViewById(R.id.AchievementsLængsteStreak);
        resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(this);

        opdaterTekstfelter();

        }


    public void loadData()
        {

        SharedPreferences settings = getSharedPreferences("PREFS",0);

        AntalVundneIStreg = settings.getInt("AntalVundetIStreg", 0);
        AntalVundneTotal = settings.getInt("AntalVundneSpil", 0);
        BestWinStreak = settings.getInt("BestWinStreak", 0);


        Log.d("listItems", "" + AntalVundneIStreg);
        Log.d("listItems", "" + AntalVundneTotal);
        }
    public void opdaterTekstfelter()
        {
            AchievementsAntalVundne.setText("Du har vundet " + AntalVundneTotal + " spil ialt!");
            AchievementsWinstreak.setText("Din nuværende Win Streak er på " + AntalVundneIStreg);
            AchievementsLængsteStreak.setText("Din bedste Win Streak nogensinde er på " + BestWinStreak);
        }

    @Override
    public void onClick(View v)
        {
        if ( v == resetButton)
            {
            SharedPreferences settings = getSharedPreferences("PREFS",0);
            SharedPreferences.Editor editor = settings.edit();

            editor.putInt("AntalVundneSpil", 0);
            editor.putInt("AntalVundetIStreg", 0);
            editor.putInt("BestWinStreak", 0);
            editor.commit();

            AntalVundneIStreg =  settings.getInt("AntalVundetIStreg",0);
            AntalVundneTotal = settings.getInt("AntalVundneSpil", 0);
            BestWinStreak = settings.getInt("BestWinStreak", 0);
            opdaterTekstfelter();
            }
        }
    }
