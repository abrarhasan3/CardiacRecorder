package android.example.cardiacrecorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
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
        FirebaseAuth mAuth=FirebaseAuth.getInstance();

        textView.setPaintFlags(textView.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
                textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LogInPage.this, "HI",Toast.LENGTH_SHORT).show();

            }

        });
        button.setOnClickListener(new View.OnClickListener() {
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
}