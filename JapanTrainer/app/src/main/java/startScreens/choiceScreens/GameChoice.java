
// Screen for choosing the game mode

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

import gameScreens.QuizGame;
import gameScreens.QuizGameImage;
import gameScreens.TextGame;
import gameScreens.TextGameImage;
import helpClasses.managerClasses.ChoiceManager;
import helpClasses.managerClasses.PointsManager;

public class GameChoice extends AppCompatActivity {

    // Manager Variables
    private PointsManager points;
    private ChoiceManager choiceManager;

    // UI Variables
    private Toolbar toolbar;
    private TextView textView;

    // Variable for checking for choice games
    private String game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choice_game);

        // Initialization
        points = new PointsManager(this);
        choiceManager = new ChoiceManager(this);

        // Card Variables
        final MaterialCardView text_game = findViewById(R.id.text_game);
        final MaterialCardView quiz_game = findViewById(R.id.quiz_game);
        // Button variable
        final Button next = findViewById(R.id.next);
        // Intents for going to another screens
        final Intent intentTextGame = new Intent(this, TextGame.class);
        final Intent intentTextGameImage = new Intent(this, TextGameImage.class);
        final Intent intentQuizGame = new Intent(this, QuizGame.class);
        final Intent intentQuizGameImage = new Intent(this, QuizGameImage.class);

        // Setting Toolbar
        toolbar = findViewById(R.id.homescreen_toolbar);
        setSupportActionBar(toolbar);
        //Setting up the back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Setting points from to the Toolbar
        textView = findViewById(R.id.points);
        textView.setText(String.valueOf(points.getPoints()));

        // Setting Up cards
        text_game.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                if (!text_game.isChecked()){
                    text_game.toggle();
                    quiz_game.setChecked(false);
                    next.setEnabled(true);
                    game = "text_game";
                }
            }
        });

        quiz_game.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                if (!quiz_game.isChecked()){
                    quiz_game.toggle();
                    text_game.setChecked(false);
                    next.setEnabled(true);
                    game = "quiz_game";

                }
            }
        });

        next.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Setting points to 0
                points.setPointsNull();
                // Setting tries to 0
                points.setTriesNull();

                        Button b = (Button)v;
                        b.setBackgroundColor(getResources().getColor(R.color.violet));
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                // Check for choosing the right game
                                if (game.equals("text_game")){
                                    if (choiceManager.isImages())
                                        startActivity(intentTextGameImage);
                                    else
                                        startActivity(intentTextGame);
                                }
                                else{
                                    if (choiceManager.isImages())
                                        startActivity(intentQuizGameImage);
                                    else
                                        startActivity(intentQuizGame);
                                }
                            }
                        }, 30);
                    }
                });
            }
    }
