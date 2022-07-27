package android.example.cardiacrecorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ListFragment;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import userDefinedClass.AddNewData;

public class Statistics extends AppCompatActivity {
    LinearLayoutManager linearLayoutManager;
    List<AddNewData> dataList=new ArrayList<>();
    Adapter_Stat adapter;
    RecyclerView recyclerView;
    TextInputLayout textInputLayout;
    AutoCompleteTextView autoCompleteTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        ActionBar actionBar = getSupportActionBar();


        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);


        textInputLayout=findViewById(R.id.menu_drop);
        autoCompleteTextView=findViewById(R.id.drop_item);
        String [] s={"Heart Rate","Systolic","Diastolic","Date"};
        ArrayAdapter<String> adapter=new ArrayAdapter<>(Statistics.this,R.layout.menu_item,s);
        autoCompleteTextView.setAdapter(adapter);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * For Getting Data from Firebase
             * @param adapterView
             * @param view
             * @param i
             * @param l
             */
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                getdatafromfirebase(i);

            }
        });

        getdatafromfirebase(1);
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
                Intent intent=new Intent(Statistics.this,homepage.class);
                startActivity(intent);
                this.finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * This method Fetches data from firebase
     * @param p
     */
    public  void getdatafromfirebase( int p)
    {
        dataList.clear();
        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        String id=firebaseAuth.getCurrentUser().getUid();
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("Users").child(id);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            /**
             * Takes a snapshot of database
             * @param snapshot
             */
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren())
                {

//                    String systolic,diastolic;
//                    String date,time,id;
//                    systolic=dataSnapshot.child("systolic").getValue(String.class);
//                    diastolic=dataSnapshot.child("diastolic").getValue(String.class);
//                    date=dataSnapshot.child("date").getValue(String.class);
//                    time=dataSnapshot.child("time").getValue(String.class);
//                    id=dataSnapshot.child("id").getValue(String.class);

                    int hrateint=dataSnapshot.child("heartRate").getValue(int.class);
                    int systolic=dataSnapshot.child("systolic").getValue(int.class);
                    int diastolic=dataSnapshot.child("diastolic").getValue(int.class);
                    String id1=dataSnapshot.child("id").getValue(String.class);
                    String comment=dataSnapshot.child("comment").getValue(String.class);
                    Date date=dataSnapshot.child("date").getValue(Date.class);
                    initData1(new AddNewData(systolic,diastolic,hrateint,id1,comment,date) );
                }
                //TextView t=findViewById(R.id.UserData);
                //t.setText(""+dataList.get(0).getSystolic()+""+dataList.get(1).getSystolic());
                if(p==0)
                {
                    Collections.sort(dataList, new Comparator<AddNewData>() {
                        /**
                         * Compares two AddnewData object basis on HeartRate
                         * @param addNewData
                         * @param t1
                         * @return
                         */
                        @Override
                        public int compare(AddNewData addNewData, AddNewData t1) {
                            return addNewData.getHeartRate()-t1.getHeartRate();
                        }
                    });
                }

                else if(p==1)
                {

                    Collections.sort(dataList, new Comparator<AddNewData>() {
                        /**
                         * Compares two AddnewData object basis on Systolic
                         * @param addNewData
                         * @param t1
                         * @return
                         */
                        @Override
                        public int compare(AddNewData addNewData, AddNewData t1) {
                            return  addNewData.getSystolic()-t1.getSystolic();
                        }
                    });
                }
                else if(p==2)
                {
                    Collections.sort(dataList, new Comparator<AddNewData>() {
                        /**
                         * Compares two AddnewData object basis on Diastolic
                         * @param addNewData
                         * @param t1
                         * @return
                         */
                        @Override
                        public int compare(AddNewData addNewData, AddNewData t1) {
                            return addNewData.getDiastolic()-t1.getDiastolic();
                        }
                    });
                }
                else
                {
                    Collections.sort(dataList, new Comparator<AddNewData>() {
                        /**
                         * Compares two AddnewData object basis on Date
                         * @param addNewData
                         * @param t1
                         * @return
                         */
                        @Override
                        public int compare(AddNewData addNewData, AddNewData t1) {
                            return addNewData.getDate().compareTo(t1.getDate());
                        }
                    });
                }
                //Toast.makeText(Statistics.this,""+dataList.get(0).getSystolic()+""+dataList.get(1).getSystolic(),Toast.LENGTH_SHORT).show();
                initRecyclerView1(p);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Statistics.this," "+error.toString(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     * Added a new data to dataList
     * @param a
     */
    private void initData1(AddNewData a) {
        dataList.add(a);
    }

    /**
     * Sets adapter, Layout and notify if changes
     * @param p
     */

    private void initRecyclerView1(int p) {
        recyclerView=findViewById(R.id.recyclerview);
        linearLayoutManager=new LinearLayoutManager(Statistics.this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter=new Adapter_Stat(dataList,Statistics.this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

}