
// Screen for the Text Game for Image game

package gameScreens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.japantrainer.R;

import helpClasses.managerClasses.PointsManager;
import helpClasses.managerClasses.WordsManager;

public class TextGameImage extends AppCompatActivity {

    // Manager Variables
    private PointsManager points;
    private WordsManager wordsManager;

    // UI Variables
    private TextView textView;
    private EditText mEdit;
    private ImageView imageView;
    private Toolbar toolbar;

    // String for the answer
    private String answer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_text_image);

        // Initialization
        points = new PointsManager(this);
        wordsManager = new WordsManager(this);

        // Setting Toolbar
        toolbar = findViewById(R.id.homescreen_toolbar);
        setSupportActionBar(toolbar);
        //Setting up the back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Setting points from to the toolbar
        textView = findViewById(R.id.points);
        textView.setText(String.valueOf(points.getPoints()));

        // Getting the word
        answer = wordsManager.getWordAnswerImage();

        // Setting the image to the ImageView
        byte[] image = wordsManager.getImage();
        imageView = findViewById(R.id.question);
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        imageView.setImageBitmap(bitmap);

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
            ImageView image_right = findViewById(R.id.image_right);
            ImageView white_circle = findViewById(R.id.white_circle);

            // Showing the right feedback
            white_circle.setVisibility(View.GONE);
            image_right.setVisibility(View.VISIBLE);

            //SoundEffects von https://www.zapsplat.com/
            player = MediaPlayer.create(getApplicationContext(), R.raw.correct);
            player.start();

            points.incrementPoints();
        }
        // If the word is incorrect
        else
        {
            ImageView image_wrong = findViewById(R.id.image_wrong);
            ImageView white_circle = findViewById(R.id.white_circle);

            // Showing the wrong feedback
            white_circle.setVisibility(View.VISIBLE);
            image_wrong.setVisibility(View.VISIBLE);

            player = MediaPlayer.create(getApplicationContext(), R.raw.incorrect);
            player.start();
        }

        // Delaying time to see the feedback
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                openTextGameImage();
            }
        }, 300);
    }

    private void openTextGameImage(){
        Intent intent = new Intent(this, TextGameImage.class);
        startActivity(intent);
    }


    // Functions for the back button
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