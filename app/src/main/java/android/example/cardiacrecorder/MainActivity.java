package android.example.cardiacrecorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 3000;

    Animation topAnim,bottomAnim;
    ImageView icon;
    TextView title,tagLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // EDIT BY SHOUMYA

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        // Animations For SplashScreen
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        icon = findViewById(R.id.Icon);
        title = findViewById(R.id.Title);
        tagLine = findViewById(R.id.Tagline);

        icon.setAnimation(topAnim);
        title.setAnimation(bottomAnim);
        tagLine.setAnimation(bottomAnim);



        new Handler().postDelayed(new Runnable() {
            /**
             * Intents from Splash Screen to LogInOrSignPage
             */
            @Override
            public void run() {
                Intent intent =new Intent(MainActivity.this, log_in_or_Sign.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);

        // SHOUMYA EDIT ENDS HERE
    }
}