package android.example.cardiacrecorder;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import userDefinedClass.AddNewData;

/**
 * Adapter Class to show data to RecyclerView
 */
public class Adapter_Stat extends RecyclerView.Adapter<Adapter_Stat.ViewHolder> {
    List<AddNewData> DataList;
    Context context;

    public Adapter_Stat(List<AddNewData> dataList, Context context) {
        DataList = dataList;
        this.context = context;
    }
    public Adapter_Stat(List<AddNewData> dataList) {
        DataList = dataList;
    }

    /**
     * On Create ViewHolder method is Used to set the layout of Each List and bind it.
     * @param parent
     * @param viewType
     * @return Adapter_Stat.ViewHolder
     */

    @NonNull
    @Override
    public Adapter_Stat.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_design,parent,false);
        return new Adapter_Stat.ViewHolder(view);


    }

    /**
     * This method shows the data and set other backend code for each List Item in recycler View.
     * @param holder
     * @param position
     */

    @Override
    public void onBindViewHolder(@NonNull Adapter_Stat.ViewHolder holder, int position) {
        holder.setData(DataList.get(position));
        ImageView imageView=holder.delete;

        imageView.setOnClickListener(new View.OnClickListener() {
            /**
             * This is for deleting a data from recycle View.
             * @param view
             */
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert=new AlertDialog.Builder(context);
                //alert.setIcon();
                alert.setTitle("Delete Confirmation");
                 alert.setMessage("Are You Sure to Delete?");
                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    /**
                     * if yes is clicked from alertDialog data is deleted
                     * @param dialogInterface
                     * @param i
                     */
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
                        DatabaseReference reference=FirebaseDatabase.getInstance().getReference().child("Users").child(uid);
                        String removeId=DataList.get(holder.getAdapterPosition()).getId();
                        Toast.makeText(context,"DELETED"+DataList.get(holder.getAdapterPosition()).getId() ,Toast.LENGTH_SHORT).show();
                        reference.child(removeId).removeValue();
                        DataList.remove(holder.getAdapterPosition());
                        notifyItemRemoved(holder.getAdapterPosition());
                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    /**
                     * If no is selected AlertDialog is canceled.
                     * @param dialogInterface
                     * @param i
                     */
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog alertDialog=alert.create();
                alertDialog.show();
            }
        });
        ImageView imageView1=holder.edit;
        imageView1.setOnClickListener(new View.OnClickListener() {
            /**
             * If edit is User Can edit the item
             * @param view
             */
            @Override
            public void onClick(View view) {

                //Toast.makeText(context, "EDIT  "+holder.getAdapterPosition(), Toast.LENGTH_SHORT).show();
                String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
                DatabaseReference reference =FirebaseDatabase.getInstance().getReference().child("Users").child(uid);

                Dialog dialog=new Dialog(context);
                dialog.setContentView(R.layout.alert_dialog);

                TextView t=dialog.findViewById(R.id.UpperTxt);
                t.setText("Update Data");

                EditText sys=dialog.findViewById(R.id.systolic);
                EditText hrate= dialog.findViewById(R.id.heartrate);
                EditText dias=dialog.findViewById(R.id.diastole);
                EditText eday=dialog.findViewById(R.id.date);
                EditText emon=dialog.findViewById(R.id.month);
                EditText eyer=dialog.findViewById(R.id.yearedit);
                EditText emin=dialog.findViewById(R.id.minute);
                EditText ehour=dialog.findViewById(R.id.hour);
                EditText com=dialog.findViewById(R.id.comment);





                Spinner dropdown = dialog.findViewById(R.id.spinner);
                String[] items = new String[]{"AM", "PM"};
                ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, items);
                dropdown.setAdapter(adapter1);
                Button button1=dialog.findViewById(R.id.Cancel);
                button1.setOnClickListener(new View.OnClickListener() {
                    /**
                     * This Dismiss the dialog
                     * @param view
                     */
                    @Override
                    public void onClick(View view) {

                        dialog.dismiss();
                    }
                });
                Button button2=dialog.findViewById(R.id.Add);
                button2.setOnClickListener(new View.OnClickListener() {
                    /**
                     * This Updates The value.
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

                        if(er.equals(false)) {
                            int sysint = Integer.parseInt(systole);
                            int diasint = Integer.parseInt(diastole);
                            int hrateint = Integer.parseInt(heartrate);


                            String date;//=eday.getText().toString().trim()+"."+emon.getText().toString().trim()+"."+eyer.getText().toString();

                            // =ehour.getText().toString().trim()+":"+emin.getText().toString().trim()+":"+dropdown.getSelectedItem().toString();
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

                            date = day + "." + month + "." + year + "t" + hour + ":" + min + ":" + dropdown.getSelectedItem().toString();

                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy't'HH:mm:a");




                            Date date1 = Calendar.getInstance().getTime();
                            try {
                                date1 = dateFormat.parse(date);
                            } catch (ParseException e) {
                                Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
                            }
                            AddNewData a = new AddNewData(sysint, diasint, hrateint, DataList.get(holder.getAdapterPosition()).getId(), comment, date1);
                            reference.child("Users").child(id).child(DataList.get(holder.getAdapterPosition()).getId()).setValue(a);
                            dialog.dismiss();

                            DataList.get(holder.getAdapterPosition()).setHeartRate(hrateint);
                            DataList.get(holder.getAdapterPosition()).setSystolic(sysint);
                            DataList.get(holder.getAdapterPosition()).setDiastolic(diasint);
                            DataList.get(holder.getAdapterPosition()).setDate(date1);
                            DataList.get(holder.getAdapterPosition()).setComment(comment);
                            notifyItemChanged(holder.getAdapterPosition());
                        }

//                        int sysint =Integer.parseInt(sys.getText().toString().trim());
//                        int diasint=Integer.parseInt(dias.getText().toString().trim());
//                        int hrteint=Integer.parseInt(hrate.getText().toString().trim());
//
//                        DatabaseReference reference=FirebaseDatabase.getInstance().getReference();
//                        String id= FirebaseAuth.getInstance().getCurrentUser().getUid();
//
//                        String IdFirebase=DataList.get(holder.getAdapterPosition()).getId();
//                        AddNewData a=new AddNewData(sysint,diasint,hrteint,date,time,IdFirebase);
////                        reference.child("Users").child(id).child(IdFirebase).setValue(a);

//
//                        dialog.dismiss();


                    }
                });


                hrate.setText(""+DataList.get(holder.getAdapterPosition()).getHeartRate());
                sys.setText((""+DataList.get(holder.getAdapterPosition()).getSystolic()));
                dias.setText(""+DataList.get(holder.getAdapterPosition()).getDiastolic());

                String comment1=DataList.get(holder.getAdapterPosition()).getComment();
                com.setText(comment1);


                Date date11=DataList.get(holder.getAdapterPosition()).getDate();


                SimpleDateFormat dd = new SimpleDateFormat("dd");
                SimpleDateFormat MM=new SimpleDateFormat("MM");
                SimpleDateFormat yyyy=new SimpleDateFormat("yyyy");
                eday.setText(dd.format(date11));
                emon.setText(MM.format(date11));
                eyer.setText(yyyy.format(date11));


                SimpleDateFormat dateFormat1=new SimpleDateFormat("HH:mm:a");
                SimpleDateFormat HH=new SimpleDateFormat("HH");
                int timetemp=Integer.parseInt(HH.format(date11));
                Spinner spinner=dialog.findViewById(R.id.spinner);
                if(timetemp==0)
                {
                    ehour.setText("12");
                    spinner.setSelection(0);
                }
                else if(timetemp<12)
                {
                    ehour.setText(""+timetemp);
                    spinner.setSelection(0);

                }
                else if(timetemp==12)
                {
                    ehour.setText(""+timetemp);
                    spinner.setSelection(1);
                }
                else if(timetemp>12 )
                {
                    timetemp=timetemp-12;
                    ehour.setText(""+timetemp);

                    spinner.setSelection(1);
                }

                SimpleDateFormat mm=new SimpleDateFormat("mm");
                emin.setText(""+mm.format(date11));
                dialog.show();

//                reference.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        for(DataSnapshot dataSnapshot:snapshot.getChildren())
//                        {
//
//
//                            int hrateint1=dataSnapshot.child("heartRate").getValue(int.class);
//                            int systolic1=dataSnapshot.child("systolic").getValue(int.class);
//                            int diastolic1=dataSnapshot.child("diastolic").getValue(int.class);
//                            String comment1=dataSnapshot.child("comment").getValue(String.class);
//                            Date date11=dataSnapshot.child("date").getValue(Date.class);
//
//                            hrate.setText(""+hrateint1);
//                            sys.setText((""+systolic1));
//                            dias.setText(""+diastolic1);
//                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
//                            SimpleDateFormat dateFormat1=new SimpleDateFormat("HH:mm:a");
//                            String date=dateFormat.format(date11);
//                            String time=dateFormat1.format(date11);
//
//                            com.setText(comment1);
//                            eday.setText(date.substring(0,2));
//                            emon.setText(date.substring(3,5));
//                            eyer.setText(date.substring(6,10));
//
//                            ehour.setText(time.substring(0,2));
//                            emin.setText(time.substring(3,5));
//
//                            if(time.startsWith("AM", 6))
//                            {
//                                Spinner s=dialog.findViewById(R.id.spinner);
//                                s.setSelection(0);
//                            }
//                            else
//                            {
//                                Spinner s=dialog.findViewById(R.id.spinner);
//                                s.setSelection(1);
//                            }
//
//                        }
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//                        Toast.makeText(context,""+error,Toast.LENGTH_SHORT).show();
//
//                    }
//                });

            }
        });

    }

    /**
     * Returns the item count
     * @return int
     */
    @Override
    public int getItemCount() {
        return DataList.size();
    }

    /**
     * Sets the Texts and Images in View.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView delete=itemView.findViewById(R.id.DeleteButton);
        ImageView edit=itemView.findViewById(R.id.EditButton);

        TextView sys=itemView.findViewById(R.id.ItemSystolic);
        TextView diastolic=itemView.findViewById(R.id.ItemDiastolic);
        TextView time=itemView.findViewById(R.id.TxtTime);
        TextView date=itemView.findViewById(R.id.TxtDate);
        TextView txthr=itemView.findViewById(R.id.ItemHeartRate);
        TextView txtcom=itemView.findViewById(R.id.listComment);

        ImageView alert=itemView.findViewById(R.id.alertImage);
        TextView txtsd=itemView.findViewById(R.id.highsystolic);
        TextView txthd=itemView.findViewById(R.id.highdiastolic);
        TextView txtnorm=itemView.findViewById(R.id.good);
        /**
         * 
         * @param itemView
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        /**
         * User Defined Class for setting Data
         * @param obj1
         */
        public void setData(AddNewData obj1)
        {

              sys.setText(""+obj1.getSystolic());
              diastolic.setText(""+obj1.getDiastolic());
              txthr.setText(""+obj1.getHeartRate());
              txtcom.setText(""+obj1.getComment());


              if(obj1.getSystolic()>85)
              {
                  if(obj1.getDiastolic()>150)
                  {
                      alert.setBackgroundResource(R.drawable.alert);
                      txtsd.setVisibility(View.VISIBLE);
                      txthd.setVisibility(View.VISIBLE);
                      txtnorm.setVisibility(View.GONE);
                  }
                  else
                  {
                      alert.setBackgroundResource(R.drawable.alert);
                      txtsd.setVisibility(View.VISIBLE);
                      txtnorm.setVisibility(View.GONE);
                      txthd.setVisibility(View.GONE);

                  }
              }
              else
              {
                  if(obj1.getDiastolic()>150)
                  {
                      alert.setBackgroundResource(R.drawable.alert);
                      txthd.setVisibility(View.VISIBLE);
                      txtnorm.setVisibility(View.GONE);
                      txtsd.setVisibility(View.GONE);
                  }
                  else
                  {
                      alert.setBackgroundResource(R.drawable.varified);
                      txtnorm.setVisibility(View.VISIBLE);
                      txtsd.setVisibility(View.GONE);
                      txthd.setVisibility(View.GONE);
                  }
              }



              SimpleDateFormat dateFormat1=new SimpleDateFormat("dd.MM.yyyy");



              String datestr=dateFormat1.format(obj1.getDate());
              date.setText(datestr);


            String timestr="";
            SimpleDateFormat HH=new SimpleDateFormat("HH");
            int hour_int=Integer.parseInt(HH.format(obj1.getDate()));
            SimpleDateFormat mm=new SimpleDateFormat("mm");
            if(hour_int==0)
            {
                timestr="12:"+mm.format(obj1.getDate())+":"+"AM";
            }
            else if(hour_int<12)
            {
                timestr=""+hour_int+":"+mm.format(obj1.getDate())+":"+"AM";
            }
            else if(hour_int==12)
            {
                timestr="12:"+mm.format(obj1.getDate())+":"+"PM";
            }
            else if(hour_int<24)
            {
                timestr=""+(hour_int-12)+":"+mm.format(obj1.getDate())+":"+"PM";
            }
            time.setText(timestr);



//            this.time.setText(obj1.getTime());
//            this.date.setText(obj1.getDate());
        }

    }


}
