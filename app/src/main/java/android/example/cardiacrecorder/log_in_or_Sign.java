package android.example.cardiacrecorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class log_in_or_Sign extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_or_sign);
        Button button=findViewById(R.id.login_button);
        Button button1=findViewById(R.id.signup_button);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources()
                .getColor(R.color.Pink_Login)));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#cc3e43"));
        }

        button.setOnClickListener(new View.OnClickListener() {
            /**
             * Goes to Login Page by Clicking this
             * @param view
             */
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(log_in_or_Sign.this,LogInPage.class );
                startActivity(intent);
                finish();
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            /**
             * Goes to SignUpPage
             * @param view
             */
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(log_in_or_Sign.this,SignUpAct.class );
                startActivity(intent);
                finish();
            }
        });


    }

}