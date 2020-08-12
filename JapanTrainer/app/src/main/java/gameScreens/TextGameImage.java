package gameScreens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
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

    private PointsManager points;
    private WordsManager wordsManager;
    private Toolbar toolbar;

    // Strings for question (for TextView) and answer (for Edit)
    private String answer;

    // UI Variables
    private TextView textView;
    private EditText mEdit;
    private ImageView imageView;


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

        // Setting points from to TextView
        textView = findViewById(R.id.points);
        textView.setText(String.valueOf(points.getPoints()));

        // Getting the word
        answer = wordsManager.getWordAnswerImage();

        // Setting the image to the ImageView
        byte[] image = wordsManager.getImage();
        imageView = findViewById(R.id.question1);
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

        // If user is right, then incrementing points
        if (userString.equals(answer)) {
            points.incrementPoints();
        }

        // Going to the next screen
        Intent intent = new Intent(this, TextGameImage.class);
        startActivity(intent);
    }
}