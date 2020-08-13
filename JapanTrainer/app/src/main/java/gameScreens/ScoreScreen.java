package gameScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.japantrainer.R;

import helpClasses.managerClasses.PointsManager;
import startScreens.HomeScreen;

public class ScoreScreen extends AppCompatActivity {

    private PointsManager pointsManager;
    // UI Variables
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_score);

        // Initialization
        pointsManager = new PointsManager(this);

        // Setting points from to TextView
        textView = findViewById(R.id.points);
        textView.setText(String.valueOf(pointsManager.getPoints()));

        // Setting button
        Button btn = findViewById(R.id.finish);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomeScreen();
            }
        });
    }

    // Opens next Screens after pressing a button
    private void openHomeScreen()   {

        // Going to the next screen
        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
    }
}