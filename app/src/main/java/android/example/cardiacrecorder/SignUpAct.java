package android.example.cardiacrecorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import userDefinedClass.UserInfo;

public class SignUpAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        EditText nameEdit=findViewById(R.id.name);
        EditText emailEdit=findViewById(R.id.SignUpEmail);
        EditText passwordEdit=findViewById(R.id.pass_edit1);
        EditText confirmEdit=findViewById(R.id.editpass2);
        Button button=findViewById(R.id.SignUp_2);
        ActionBar actionBar = getSupportActionBar();


        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        FirebaseAuth mAuth=FirebaseAuth.getInstance();
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources()
                .getColor(R.color.S_Action)));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#05828c"));
        }



        button.setOnClickListener(new View.OnClickListener() {
            /**
             * SignUp to Firebase
             * @param view
             */
            @Override
            public void onClick(View view) {
                String pass=passwordEdit.getText().toString().trim();
                String pass2=confirmEdit.getText().toString().trim();
                String name=nameEdit.getText().toString().trim();
                String email=emailEdit.getText().toString().trim();
                if(name.isEmpty())
                {
                    nameEdit.setError("Name Field is Required");
                }
                else if(email.isEmpty())
                {
                    emailEdit.setError("Email Field Is Required");
                }
                else if( pass.isEmpty())
                {
                    passwordEdit.setError("Please Enter A Password");
                }
                else if(pass2.isEmpty())
                {
                    confirmEdit.setError("Please Enter The password Again");
                }
                else if(!pass.equals(pass2))
                {
                    confirmEdit.setError("Password Does not Match");
                }
                else
                {
                    mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        /**
                         * if signup successful this method takes user to homepage
                         * @param task
                         */
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(SignUpAct.this, "SIGN UP SUCCESSFUL!",Toast.LENGTH_SHORT).show();
                                DatabaseReference reference= FirebaseDatabase.getInstance().getReference();
                                String id=FirebaseAuth.getInstance().getCurrentUser().getUid();
                                EditText editText=findViewById(R.id.editheight);
                                EditText editText1=findViewById(R.id.Weight);
                                EditText e=findViewById(R.id.name);

                                float height_f=Float.parseFloat(editText.getText().toString().trim());
                                float weight_f=Float.parseFloat(editText1.getText().toString().trim());
                                String name=e.getText().toString().trim();

                                UserInfo userInfo=new UserInfo(name,height_f,weight_f);

                                reference.child("Personal").child(id).setValue(userInfo);
                                Intent intent=new Intent(SignUpAct.this,homepage.class);
                                startActivity(intent);
                                finish();
                            }
                            else
                            {
                                Toast.makeText(SignUpAct.this, "Log in Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
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
                Intent intent=new Intent(SignUpAct.this,log_in_or_Sign.class);
                startActivity(intent);
                this.finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}