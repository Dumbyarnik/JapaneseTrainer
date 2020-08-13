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

import helpClasses.managerClasses.ChoiceManager;
import helpClasses.managerClasses.PointsManager;

public class LetterChoice extends AppCompatActivity implements View.OnClickListener {

    private ChoiceManager choiceManager;
    private TextView textView;
    private PointsManager points;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choice_letter);

        // Initialization
        choiceManager = new ChoiceManager(this);
        points = new PointsManager(this);

        final Button next = findViewById(R.id.next);
        final MaterialCardView romaji = findViewById(R.id.romaji);
        final MaterialCardView japanese = findViewById(R.id.japanese);

        // Setting Toolbar
        toolbar = findViewById(R.id.homescreen_toolbar);
        setSupportActionBar(toolbar);
        //Setting up the back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Setting points from to TextView
        textView = findViewById(R.id.points);
        textView.setText(String.valueOf(points.getPoints()));



        romaji.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                if (!romaji.isChecked()){
                    romaji.toggle();
                    japanese.setChecked(false);
                    next.setEnabled(true);
                    choiceManager.setRomaji();
                }
            }
        });

        japanese.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                if (!japanese.isChecked()){
                    japanese.toggle();
                    romaji.setChecked(false);
                    next.setEnabled(true);
                    choiceManager.setJapanese();
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Button b = (Button)v;
                b.setBackgroundColor(getResources().getColor(R.color.violet));
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        openGameChoice();
                    }
                }, 30);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.romaji:
                choiceManager.setRomaji();
                openGameChoice();
                break;

            case R.id.japanese:
                choiceManager.setJapanese();
                openGameChoice();
                break;

        }
    }

    // Opens Game Activity (for Button)
    public void openGameChoice(){
        Intent intent = new Intent(this, GameChoice.class);
        startActivity(intent);
    }
}