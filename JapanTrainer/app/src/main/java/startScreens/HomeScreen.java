
// Screen where user can start the game
// Created 24.07.2020

package startScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import helpClasses.managerClasses.ChoiceManager;
import helpClasses.managerClasses.InsertManager;
import helpClasses.managerClasses.PointsManager;
import startScreens.choiceScreens.FontChoice;
import startScreens.choiceScreens.FontChoiceSyllables;

import com.example.japantrainer.R;

import java.util.Locale;

public class HomeScreen extends AppCompatActivity implements View.OnClickListener {


    private TextView textView;
    private PointsManager points;
    private Toolbar toolbar;
    private Animation homescreen_animation;
    private ImageView image;
    private ChoiceManager choiceManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_home);
        // Initialization
        points = new PointsManager(this);
        choiceManager = new ChoiceManager(this);

        // Setting up animation
        //homescreen_animation = AnimationUtils.loadAnimation(this,R.anim.homescreen_animation);
        //image = findViewById(R.id.imageView2);
        //image.setAnimation(homescreen_animation);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                // Create an Intent that will start the Menu-Activity.
                //Intent homeIntent = new Intent(HomeScreen.this, HomeScreen.class);
        //https://stackoverflow.com/questions/13397709/android-hide-imageview
                //ImageView imgView = (ImageView)findViewById(R.id.imageView2);
                //imgView .setVisibility(View.GONE);
            }
        }, 1000);

        //Switch for Changing Language
        Switch sb = findViewById(R.id.Switch);
        String CurrentLang = getResources().getConfiguration().locale.getLanguage();
        // Check for switch
        if(CurrentLang.equals("eng")){
            sb.setChecked(false);
        }
        if(CurrentLang.equals("de")){
            sb.setChecked(true);
        }

        // Setting Toolbar
        toolbar = findViewById(R.id.homescreen_toolbar);
        setSupportActionBar(toolbar);

        // Inserting the words
        InsertManager insert = new InsertManager(this);
        insert.insertWords();

        // Setting points from to TextView
        textView = findViewById(R.id.points);
        textView.setText(String.valueOf(points.getPoints()));

        // Setting up Buttons
        Button syllablesGame = findViewById(R.id.syllablesGame);
        Button wordsGame = findViewById(R.id.wordsGame);

        syllablesGame.setOnClickListener(this);
        wordsGame.setOnClickListener(this);
        // Listener for switch
        sb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    changeLanguage("de");
                    startActivity(getIntent());

                } else {
                    changeLanguage("eng");
                    startActivity(getIntent());
                }
            }
        });




        // Experimental Area of toggle button
        Button btn2 = findViewById(R.id.btn2);
        final CardView card1 = (CardView) findViewById(R.id.card1);

        btn2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                card1.setVisibility(View.GONE);
            }
        });




    }

    // Setting game choice
    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.syllablesGame:
                choiceManager.setSyllables();
                openFontChoiceSyllables();
                break;

            case R.id.wordsGame:
                choiceManager.setWords();
                openFontChoice();
                break;

        }
    }

    // Opens Font Choice (for Button)
    private void openFontChoiceSyllables(){
        Intent intent = new Intent(this, FontChoiceSyllables.class);
        startActivity(intent);
    }

    // Opens Font Choice (for Button)
    private void openFontChoice(){
        Intent intent = new Intent(this, FontChoice.class);
        startActivity(intent);
    }


    private void changeLanguage(String lang) {
        Resources res = getResources();
        // Change locale settings in the app.
        DisplayMetrics dm = res.getDisplayMetrics();
        android.content.res.Configuration conf = res.getConfiguration();
        conf.locale = new Locale(lang);
        res.updateConfiguration(conf, dm);

    }
}