
// Home Screen, where user can choose game modes

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

    // Manager Variables
    private ChoiceManager choiceManager;
    private PointsManager points;

    // UI Variables
    private Toolbar toolbar;
    private Animation homescreen_animation;
    private ImageView image;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_home);

        // Initialization
        points = new PointsManager(this);
        choiceManager = new ChoiceManager(this);

        // Setting up animation
        homescreen_animation = AnimationUtils.loadAnimation(this,R.anim.homescreen_animation);
        image = findViewById(R.id.animation);
        image.setAnimation(homescreen_animation);

        // Delaying Home Screen
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent homeIntent = new Intent(HomeScreen.this, HomeScreen.class);
        //https://stackoverflow.com/questions/13397709/android-hide-imageview
                ImageView imgView = (ImageView)findViewById(R.id.animation);
                imgView .setVisibility(View.GONE);
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

        // Setting Toolbar
        toolbar = findViewById(R.id.homescreen_toolbar);
        setSupportActionBar(toolbar);

        // Setting points to the toolbar
        textView = findViewById(R.id.points);
        textView.setText(String.valueOf(points.getPoints()));

        // Setting up Buttons for going to another screens
        final Button syllablesGame = findViewById(R.id.syllablesGame);
        final Button wordsGame = findViewById(R.id.wordsGame);
        final Button imageGame = findViewById(R.id.imagesGame);

        syllablesGame.setOnClickListener(this);
        wordsGame.setOnClickListener(this);
        imageGame.setOnClickListener(this);

        // Inserting the words into the database
        InsertManager insert = new InsertManager(this);
        insert.insertWords();


        // Setting up Toggle Buttons and cards
        final Button btn1 = findViewById(R.id.btn1);
        final Button btn2 = findViewById(R.id.btn2);
        final Button btn3 = findViewById(R.id.btn3);
        final CardView syllableCard = (CardView) findViewById(R.id.syllableCard);
        final CardView wordsCard = (CardView) findViewById(R.id.wordsCard);
        final CardView imageCard = (CardView) findViewById(R.id.imageCard);

        // In the beginning we need to show Syllable Card
        wordsCard.setVisibility(View.GONE);
        imageCard.setVisibility(View.GONE);

        btn1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                    syllableCard.setVisibility(View.VISIBLE);
                    wordsCard.setVisibility(View.GONE);
                    imageCard.setVisibility(View.GONE);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                syllableCard.setVisibility(View.GONE);
                wordsCard.setVisibility(View.VISIBLE);
                imageCard.setVisibility(View.GONE);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                syllableCard.setVisibility(View.GONE);
                wordsCard.setVisibility(View.GONE);
                imageCard.setVisibility(View.VISIBLE);
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

            case R.id.imagesGame:
                choiceManager.setImages();
                openFontChoice();
                break;

        }
    }

    // Opens Font Choice for Syllable (for Button)
    private void openFontChoiceSyllables(){
        Intent intent = new Intent(this, FontChoiceSyllables.class);
        startActivity(intent);
    }

    // Opens Font Choice (for Button)
    private void openFontChoice(){
        Intent intent = new Intent(this, FontChoice.class);
        startActivity(intent);
    }

    // Changes language of the app
    private void changeLanguage(String lang) {
        Resources res = getResources();
        // Change locale settings in the app.
        DisplayMetrics dm = res.getDisplayMetrics();
        android.content.res.Configuration conf = res.getConfiguration();
        conf.locale = new Locale(lang);
        res.updateConfiguration(conf, dm);

    }
}