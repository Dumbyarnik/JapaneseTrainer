

// Screen for a Quiz
// Created 30.07.2020

package gameScreens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.japantrainer.R;

import java.util.ArrayList;
import java.util.Random;

import helpClasses.managerClasses.PointsManager;
import helpClasses.managerClasses.WordsManager;

public class QuizGame extends AppCompatActivity {


    private PointsManager points;
    private WordsManager wordsManager;
    private Toolbar toolbar;

    // Strings for question (for TextView) and answer (for Edit), 3 words for buttons
    private String answer;
    private String question;
    private String[] otherWords;

    // UI Variables
    private TextView textView;
    private ArrayList<Button> buttons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_quiz);

        // Initialization
        points = new PointsManager(this);
        wordsManager = new WordsManager(this);

        // Setting Toolbar
        toolbar = findViewById(R.id.homescreen_toolbar);
        setSupportActionBar(toolbar);
        //Setting up the back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Setting points from to TextView
        textView = findViewById(R.id.points);
        textView.setText(String.valueOf(points.getPoints()));

        // Getting all the words
        String[] tmp = wordsManager.getRightAnswer();
        question = tmp[0];
        answer = tmp[1];
        otherWords = wordsManager.getWrongAnswers();

        // Setting the word to the textview
        textView = findViewById(R.id.question);
        textView.setText(question);

        //Setting text to buttons
        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);
        Button btn4 = findViewById(R.id.btn4);
        // Add buttons to ArrayList
        buttons.add(btn1);
        buttons.add(btn2);
        buttons.add(btn3);
        buttons.add(btn4);


        int rightButton = RandomBtn();



        // Setting text and setOnClickListener to Buttons
        int i = 0;
        int j = 0;
        for (Button btn : buttons){


            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MediaPlayer player;
                    Button b = (Button)v;
                    String buttonText = b.getText().toString();
                    points.incrementTries();

                    // If the word is correct, then show image_right
                    if (buttonText.equals(answer)) {
                        ImageView imgView= findViewById(R.id.image_right);
                        ImageView imgView2= findViewById(R.id.white_circle);

                        imgView2.setVisibility(View.GONE);
                        imgView.setVisibility(View.VISIBLE);

                        //SoundEffects von https://www.zapsplat.com/
                        player =MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        player.start();

                        points.incrementPoints();
                      
                    }
                  // If the word is incorrect
                  else 
                  {
                      player = MediaPlayer.create(getApplicationContext(), R.raw.incorrect);
                      player.start();

                      ImageView imgView3 = findViewById(R.id.image_wrong);
                      imgView3.setVisibility(View.VISIBLE);
                      ImageView imgView4 = findViewById(R.id.white_circle);
                      imgView4.setVisibility(View.VISIBLE);

                    }
                    
                  // Delaying time to see the feedback
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            openQuizGame();
                        }
                    }, 300);

                }
            });

            if (i == rightButton){
                btn.setText(answer);
            }

            if (i != rightButton){

                btn.setText(otherWords[j]);
                j++;
            }
            i++;
        }

    }

    // Gives random number between 0 and 3
    private int RandomBtn(){
        Random rand = new Random();
        int tmp = rand.nextInt(4);
        return tmp;
    }

    // Going to the next screen
    private void openQuizGame(){
        Intent intent = new Intent(this, QuizGame.class);
        startActivity(intent);
    }

    private void openScoreScreen(){
        Intent intent = new Intent(this, ScoreScreen.class);
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        openScoreScreen(); // or your code
    }


}