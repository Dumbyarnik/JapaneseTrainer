package gameScreens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.japantrainer.R;

import helpClasses.managerClasses.PointsManager;
import helpClasses.managerClasses.WordsManager;

public class TextGame extends AppCompatActivity {

    private PointsManager points;
    private WordsManager wordsManager;
    private Toolbar toolbar;

    // Strings for question (for TextView) and answer (for Edit)
    private String question;
    private String answer;

    // UI Variables
    private TextView textView;
    private EditText mEdit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_text);


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

        // Getting the word
        String[] tmp = wordsManager.getRightAnswer();
        question = tmp[0];
        answer = tmp[1];

        // Setting the word to the TextView
        textView = findViewById(R.id.question);
        textView.setText(question);

        // Setting Edit
        EditText edit = findViewById(R.id.edit_text);
        edit.setOnEditorActionListener(editorListener);

        // Setting button
        Button btn = findViewById(R.id.next);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNextScreen();
            }
        });

    }

    // Setting button on the soft keyboard (Android's one)
    private TextView.OnEditorActionListener editorListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            switch(actionId){
                case EditorInfo.IME_ACTION_DONE:
                    openNextScreen();
                    break;
            }
            return false;
        }
    };

    // Opens next Screens after pressing a button
    private void openNextScreen()   {

        // Reading user's input
        mEdit = findViewById(R.id.edit_text);
        String userString = mEdit.getText().toString();
        userString = userString.toLowerCase();

        MediaPlayer player;
        points.incrementTries();

        // If the word is correct, then show image_right
        if (userString.equals(answer)) {
            ImageView imgView= findViewById(R.id.image_right);
            ImageView imgView2= findViewById(R.id.white_circle);

            imgView2.setVisibility(View.GONE);
            imgView.setVisibility(View.VISIBLE);

            //SoundEffects von https://www.zapsplat.com/
            player = MediaPlayer.create(getApplicationContext(), R.raw.correct);
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
                openTextGame();
            }
        }, 300);
    }

    private void openTextGame(){
        Intent intent = new Intent(this, TextGame.class);
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
        openScoreScreen();
    }
}