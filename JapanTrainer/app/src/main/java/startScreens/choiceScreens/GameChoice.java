
// FOr the choice of the game
// Created 30.07.2020

package startScreens.choiceScreens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.japantrainer.R;
import com.google.android.material.card.MaterialCardView;

import gameScreens.QuizGame;
import gameScreens.TextGame;
import helpClasses.managerClasses.PointsManager;

public class GameChoice extends AppCompatActivity {

    private PointsManager points;
    private Toolbar toolbar;
    private TextView textView;
    private String game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choice_game);

        // Initialization
        points = new PointsManager(this);

        final Button next = findViewById(R.id.next);
        final MaterialCardView text_game = findViewById(R.id.text_game);
        final MaterialCardView quiz_game = findViewById(R.id.quiz_game);
        // For buttons to go to another screen
        final Intent intent = new Intent(this, TextGame.class);
        final Intent intent2 = new Intent(this, QuizGame.class);

        // Setting Toolbar
        toolbar = findViewById(R.id.homescreen_toolbar);
        setSupportActionBar(toolbar);
        //Setting up the back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Setting points from to TextView
        textView = findViewById(R.id.points);
        textView.setText(String.valueOf(points.getPoints()));



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

                if (game.equals("text_game")){
                    startActivity(intent);
                }
                else{
                    startActivity(intent2);
                }


            }
        });
    }
}