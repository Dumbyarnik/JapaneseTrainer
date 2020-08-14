
// Screen for a Quiz Game for Image game

package gameScreens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.japantrainer.R;

import java.util.ArrayList;
import java.util.Random;

import helpClasses.managerClasses.PointsManager;
import helpClasses.managerClasses.WordsManager;

public class QuizGameImage extends AppCompatActivity {

    // Manager Variables
    private PointsManager pointsManager;
    private WordsManager wordsManager;

    // UI Variables
    private TextView textView;
    private ArrayList<Button> buttons = new ArrayList<>();
    private ImageView imageView;
    private Toolbar toolbar;

    // Strings for the answer and 3 words for variants
    private String answer;
    private String[] otherWords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_quiz_image);

        // Initialization
        pointsManager = new PointsManager(this);
        wordsManager = new WordsManager(this);

        // Setting Toolbar
        toolbar = findViewById(R.id.homescreen_toolbar);
        setSupportActionBar(toolbar);
        //Setting up the back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Setting points from to the toolbar
        textView = findViewById(R.id.points);
        textView.setText(String.valueOf(pointsManager.getPoints()));

        // Getting all the words for the buttons
        answer = wordsManager.getWordInImage();
        otherWords = wordsManager.getWrongAnswersInImage();

        // Setting the image to the ImageView
        byte[] image = wordsManager.getImage();
        imageView = findViewById(R.id.question);
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        imageView.setImageBitmap(bitmap);
        
        // Setting text to buttons
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
                    pointsManager.incrementTries();

                    // If the word is correct, then show image_right
                    if (buttonText.equals(answer)) {
                        ImageView image_right = findViewById(R.id.image_right);
                        ImageView white_circle = findViewById(R.id.white_circle);

                        white_circle.setVisibility(View.GONE);
                        image_right.setVisibility(View.VISIBLE);

                        //SoundEffects von https://www.zapsplat.com/
                        player = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        player.start();

                        pointsManager.incrementPoints();

                    }
                    // If the word is incorrect
                    else
                    {
                        player = MediaPlayer.create(getApplicationContext(), R.raw.incorrect);
                        player.start();

                        ImageView image_wrong = findViewById(R.id.image_wrong);
                        ImageView white_circle = findViewById(R.id.white_circle);

                        image_wrong.setVisibility(View.VISIBLE);
                        white_circle.setVisibility(View.VISIBLE);
                    }

                    // Delaying time to see the feedback
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            openQuizGameImage();
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
    private void openQuizGameImage(){
        Intent intent = new Intent(this, QuizGameImage.class);
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