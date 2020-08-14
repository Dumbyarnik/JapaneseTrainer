package startScreens.choiceScreens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.japantrainer.R;
import com.google.android.material.card.MaterialCardView;

import gameScreens.ScoreScreen;
import helpClasses.managerClasses.ChoiceManager;
import helpClasses.managerClasses.PointsManager;

public class LetterChoice extends AppCompatActivity implements View.OnClickListener {

    // Manager Variables
    private ChoiceManager choiceManager;
    private PointsManager points;

    // UI Variables
    private TextView textView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choice_letter);

        // Initialization
        choiceManager = new ChoiceManager(this);
        points = new PointsManager(this);


        final MaterialCardView romaji = findViewById(R.id.romaji);
        final MaterialCardView japanese = findViewById(R.id.japanese);
        final Button next = findViewById(R.id.next);

        // Setting Toolbar
        toolbar = findViewById(R.id.homescreen_toolbar);
        setSupportActionBar(toolbar);
        //Setting up the back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Setting points from to the Toolbar
        textView = findViewById(R.id.points);
        textView.setText(String.valueOf(points.getPoints()));

        romaji.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                if (!romaji.isChecked()){
                    romaji.toggle();
                    japanese.setChecked(false);
                    next.setEnabled(true);
                    choiceManager.setRomaji();
                }
            }
        });

        japanese.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                if (!japanese.isChecked()){
                    japanese.toggle();
                    romaji.setChecked(false);
                    next.setEnabled(true);
                    choiceManager.setJapanese();
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Button b = (Button)v;
                b.setBackgroundColor(getResources().getColor(R.color.violet));
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        openGameChoice();
                    }
                }, 30);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.romaji:
                choiceManager.setRomaji();
                openGameChoice();
                break;

            case R.id.japanese:
                choiceManager.setJapanese();
                openGameChoice();
                break;

        }
    }

    // Opens Game Choice (for Button)
    public void openGameChoice(){
        Intent intent = new Intent(this, GameChoice.class);
        startActivity(intent);
    }



    // Functions for the back button
    private void openFontChoiceSyllables(){
        Intent intent = new Intent(this, FontChoiceSyllables.class);
        startActivity(intent);
    }

    private void openFontChoice(){
        Intent intent = new Intent(this, FontChoice.class);
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {

        if (choiceManager.isSyllables()){
            super.onBackPressed();
            openFontChoiceSyllables();
        }
        else {
            super.onBackPressed();
            openFontChoice();
        }



    }
}