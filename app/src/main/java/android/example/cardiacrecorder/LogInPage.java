package android.example.cardiacrecorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogInPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_page);
        TextView textView=findViewById(R.id.forget);
        EditText editText=findViewById(R.id.EMAIL);
        EditText editText1=findViewById(R.id.editText2);
        Button button=findViewById(R.id.Login);

        ActionBar actionBar = getSupportActionBar();


        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        FirebaseAuth mAuth=FirebaseAuth.getInstance();

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources()
                .getColor(R.color.LogInAction)));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#525252"));}

        textView.setPaintFlags(textView.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
                textView.setOnClickListener(new View.OnClickListener() {
                    /**
                     * Forget Password Method
                     * @param view
                     */
            @Override
            public void onClick(View view) {


            }

        });
        button.setOnClickListener(new View.OnClickListener() {
            /**
             * LogIn Page Intent Method
             * @param view
             */
            @Override
            public void onClick(View view) {
                String mail=editText.getText().toString().trim();
                String password=editText1.getText().toString().trim();
                if(mail.isEmpty())
                {
                    editText.setError("Email Can not be Empty");
                    editText.requestFocus();
                }
                else if(password.isEmpty())
                {
                    editText1.setError("Password Can not be Empty");
                    editText1.requestFocus();
                }
                else
                {
                    mAuth.signInWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        /**
                         * if user is Authorized the method Takes user to LogIn Page.
                         * @param task
                         */
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Intent intent=new Intent(LogInPage.this,homepage.class);
                                startActivity(intent);
                                finish();
                            }
                            else
                            {
                                Toast.makeText(LogInPage.this, "Log in Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                }
            }
        });
    }
    /**
     * Shows a back button
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent=new Intent(LogInPage.this,log_in_or_Sign.class);
                startActivity(intent);
                this.finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}