package gameScreens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
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

        // If user is right, then incrementing points
        if (userString.equals(answer)) {
            points.incrementPoints();
         }

        // Going to the next screen
        Intent intent = new Intent(this, TextGame.class);
        startActivity(intent);
    }
}