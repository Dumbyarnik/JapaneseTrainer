
// Screen for choosing the font for Syllable mode

package startScreens.choiceScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.example.japantrainer.R;
import com.google.android.material.card.MaterialCardView;

import helpClasses.managerClasses.ChoiceManager;
import helpClasses.managerClasses.PointsManager;
import helpClasses.managerClasses.WordsManager;

public class FontChoiceSyllables extends AppCompatActivity {

    // Variable for setting all IDs
    private WordsManager words;
    private PointsManager points;
    private ChoiceManager choiceManager;
    // UI Variables
    private TextView textView;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choice_font_syllables);

        // Initialization
        choiceManager = new ChoiceManager(this);
        words = new WordsManager(this);
        points = new PointsManager(this);

        final MaterialCardView katakana = findViewById(R.id.katakana);
        final MaterialCardView hiragana = findViewById(R.id.hiragana);
        final Button next = findViewById(R.id.next);

        // Setting Toolbar
        toolbar = findViewById(R.id.homescreen_toolbar);
        setSupportActionBar(toolbar);
        //Setting up the back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Setting points from to TextView
        textView = findViewById(R.id.points);
        textView.setText(String.valueOf(points.getPoints()));

        katakana.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                if (!katakana.isChecked()){
                    katakana.toggle();
                    hiragana.setChecked(false);
                    next.setEnabled(true);
                    choiceManager.setKatakana();
                }
            }
        });

        hiragana.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                if (!hiragana.isChecked()){
                    hiragana.toggle();
                    katakana.setChecked(false);
                    next.setEnabled(true);
                    choiceManager.setHiragana();
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
                        openLetterChoice();
                    }
                }, 30);
            }
        });
    }

    // Opens Letter Choice (for Button)
    public void openLetterChoice(){
        // Setting IDs of the words
        words.setID();

        Intent intent = new Intent(this, LetterChoice.class);
        startActivity(intent);
    }
}













