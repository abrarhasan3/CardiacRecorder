package android.example.cardiacrecorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import userDefinedClass.AddNewData;

public class homepage extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        Button button=findViewById(R.id.add_new);
        Button stat= findViewById(R.id.button3);



        button.setOnClickListener(new View.OnClickListener() {
            /**
             * Button for add new Data
             * @param view
             */
            @Override
            public void onClick(View view) {
                Dialog dialog=new Dialog(homepage.this);
                dialog.setContentView(R.layout.alert_dialog);
                Spinner dropdown = dialog.findViewById(R.id.spinner);
                String[] items = new String[]{"AM", "PM"};
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(homepage.this, android.R.layout.simple_spinner_dropdown_item, items);
                dropdown.setAdapter(adapter);




                Button button1=dialog.findViewById(R.id.Cancel);
                button1.setOnClickListener(new View.OnClickListener() {
                    /**
                     * Cancels the Dialog
                     * @param view
                     */
                    @Override
                    public void onClick(View view) {

                        dialog.dismiss();
                    }
                });


                Button button2=dialog.findViewById(R.id.Add);
                button2.setOnClickListener(new View.OnClickListener() {
                    /**\
                     * Adds new Data
                     * @param view
                     */
                    @Override
                    public void onClick(View view) {

                        Boolean er=false;

                        DatabaseReference reference=FirebaseDatabase.getInstance().getReference();
                        String id= FirebaseAuth.getInstance().getCurrentUser().getUid();
                        EditText sys=dialog.findViewById(R.id.systolic);
                        EditText hrate= dialog.findViewById(R.id.heartrate);
                        EditText dias=dialog.findViewById(R.id.diastole);
                        EditText eday=dialog.findViewById(R.id.date);
                        EditText emon=dialog.findViewById(R.id.month);
                        EditText eyer=dialog.findViewById(R.id.yearedit);
                        EditText emin=dialog.findViewById(R.id.minute);
                        EditText ehour=dialog.findViewById(R.id.hour);
                        EditText com=dialog.findViewById(R.id.comment);


                        String heartrate=hrate.getText().toString();
                        String systole=sys.getText().toString();
                        String diastole=dias.getText().toString();
                        String day=eday.getText().toString();
                        String month=emon.getText().toString();
                        String year=eyer.getText().toString();
                        String min=emin.getText().toString();
                        String hour=ehour.getText().toString();
                        String comment=com.getText().toString();




                        if(heartrate.isEmpty())
                        {
                            hrate.setError("Required");
                            er=true;
                        }
                        else if(Integer.parseInt(heartrate)<30)
                        {
                            hrate.setError("Minimum Value is 60");
                            er=true;
                        }
                        else if(Integer.parseInt(heartrate)>120)
                        {
                            hrate.setError("Maximum Value is 120");
                            er=true;
                        }


                        if(systole.isEmpty())
                        {
                            sys.setError("Required");
                            er=true;
                        }
                        else if(Integer.parseInt(systole)<60)
                        {
                            sys.setError("Minimum Value is 60");
                            er=true;

                        }
                        else if(Integer.parseInt(systole)>150)
                        {
                            sys.setError("Maximum Value is 150");
                            er=true;
                        }


                        if(dias.getText().toString().isEmpty())
                        {
                            dias.setError("Required");
                            er=true;
                        }
                        else if(Integer.parseInt(diastole)<100)
                        {
                            dias.setError("Minimum Value is 100");
                            er=true;
                        }
                        else if(Integer.parseInt(diastole)>200)
                        {
                            dias.setError("Maximum Value is 200");
                            er=true;
                        }

                        if(eday.getText().toString().isEmpty())
                        {
                            eday.setError("Required");
                            er=true;
                        }
                        else if(Integer.parseInt(day)<0 || Integer.parseInt(day)>31 )
                        {
                            eday.setError("Invalid date");
                            er=true;
                        }

                        if(emon.getText().toString().isEmpty())
                        {
                            emon.setError("Required");
                            er=true;
                        }
                        else if(Integer.parseInt(month)<0 || Integer.parseInt(month)>12 )
                        {
                            emon.setError("Invalid Month");
                            er=true;
                        }

                        if(eyer.getText().toString().isEmpty())
                        {
                            eyer.setError("Required");
                            er=true;
                        }
                        else if(Integer.parseInt(year)<0  )
                        {
                            eyer.setError("Invalid Year");
                            er=true;
                        }


                        if(emin.getText().toString().isEmpty())
                        {
                            emin.setError("Required");
                            er=true;
                        }
                        else if(Integer.parseInt(min)<0 || Integer.parseInt(min)>60 )
                        {
                            emin.setError("Invalid Input");
                            er=true;
                        }

                        if(ehour.getText().toString().isEmpty())
                        {
                            ehour.setError("Required");
                            er=true;
                        }
                        else if(Integer.parseInt(hour)<0 || Integer.parseInt(hour)>12 )
                        {
                            ehour.setError("Invalid Input");
                            er=true;
                        }

                        if(comment.isEmpty())
                        {
                            comment="No comment Available";
                        }

                        if(er.equals(false))
                        {
                            int sysint=Integer.parseInt(systole);
                            int diasint=Integer.parseInt(diastole);
                            int hrateint=Integer.parseInt(heartrate);


                            ProgressBar progressBar= findViewById(R.id.ProgressSystole);
                            progressBar.setProgress(sysint);
                            TextView txt1=findViewById(R.id.TxtSystole);
                            txt1.setText(systole);


                            ProgressBar progressBar1=findViewById(R.id.ProgressDiastole);
                            TextView textView=findViewById(R.id.TxtDayastole);
                            progressBar1.setProgress(diasint);
                            textView.setText(diastole);


                            if(dropdown.getSelectedItem().toString().equals("AM"))
                            {
                                if(hour.equals("12"))
                                {
                                    hour="00";
                                }
                            }
                            else if(dropdown.getSelectedItem().toString().equals("PM"))
                            {
                                int htem=Integer.parseInt(hour);
                                if(htem>=1 && htem<12)
                                {
                                    htem=htem+12;
                                }
                                hour=""+htem;
                            }

                            String date;//=eday.getText().toString().trim()+"."+emon.getText().toString().trim()+"."+eyer.getText().toString();
                            String time;;// =ehour.getText().toString().trim()+":"+emin.getText().toString().trim()+":"+dropdown.getSelectedItem().toString();

                            date=day+"."+month+"."+year+"t"+hour+":"+min+":"+dropdown.getSelectedItem().toString();;
                            SimpleDateFormat dateFormat=new SimpleDateFormat("dd.MM.yyyy't'HH:mm:a");


                            Date date1= Calendar.getInstance().getTime();
                            try{
                                date1=dateFormat.parse(date);
                            }
                            catch( ParseException e)
                            {
                                Toast.makeText(homepage.this,e.toString(),Toast.LENGTH_SHORT).show();
                            }

                            String IdFirebase=reference.push().getKey();
                            AddNewData a=new AddNewData(sysint,diasint,hrateint,IdFirebase,comment,date1);
                            reference.child("Users").child(id).child(IdFirebase).setValue(a);
                            dialog.dismiss();

                        }
                    }
                });
                dialog.show();


            }
        });

        stat.setOnClickListener(new View.OnClickListener() {
            /**
             * Goes to Statistic Page
             * @param view
             */
            @Override
            public void onClick(View view) {

                Intent intent =new Intent(homepage.this,Statistics.class);
                startActivity(intent);
                finish();


            }
        });
    }


}