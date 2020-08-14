
// First Screen of the App, it shows Logo and the name of the app

package startScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.japantrainer.R;

public class SplashScreen extends AppCompatActivity {

    // Duration of wait
    private static int SplashTimeOut = 7900;
    // UI Variables
    private Animation splashscreen_animation;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_splash);

        // Animation
        //https://www.youtube.com/watch?v=cts9Ou0IQ3U
        splashscreen_animation = AnimationUtils.loadAnimation(this,R.anim.splashscreen_animation);
        image = findViewById(R.id.imageView);
        image.setAnimation(splashscreen_animation);

        // Starting HomeScreen Activity
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent homeIntent = new Intent(SplashScreen.this, HomeScreen.class);
                startActivity(homeIntent);
                finish();
            }
        }, SplashTimeOut);
    }
}
