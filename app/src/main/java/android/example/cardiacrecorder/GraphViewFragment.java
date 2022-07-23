package android.example.cardiacrecorder;

import static java.util.Collections.sort;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LabelFormatter;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import userDefinedClass.AddNewData;


public class GraphViewFragment extends Fragment {

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public GraphViewFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment GraphViewFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static GraphViewFragment newInstance(String param1, String param2) {
//        GraphViewFragment fragment = new GraphViewFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_graph_view, container, false);
    }
    LineGraphSeries<DataPoint> series=new LineGraphSeries<>();
    List<Date> dateList=new ArrayList<>();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {



        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        String id=firebaseAuth.getCurrentUser().getUid();
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference();
        reference.child("Users").child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren())
                {
                    String date,time,id;
                    date=dataSnapshot.child("date").getValue(String.class);
                    time=dataSnapshot.child("time").getValue(String.class);
                    id=dataSnapshot.child("id").getValue(String.class);
                    SimpleDateFormat dateFormat=new SimpleDateFormat("dd.MM.yyyy't'HH:mm");
                    String temp=date+"t"+time.substring(0,5);
                    String systolic,diastolic;
                    systolic=dataSnapshot.child("systolic").getValue(String.class);
                    diastolic=dataSnapshot.child("diastolic").getValue(String.class);
                    try{
                        Date date1=dateFormat.parse(temp);
                        dateList.add(date1);
                    }
                    catch( ParseException e)
                    {
                        Toast.makeText(getActivity(),e.toString(),Toast.LENGTH_SHORT).show();

                    }
                }
                GraphView graphView=getView().findViewById(R.id.GraphView);
                sort(dateList);



                graphView.addSeries(series);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //
        //TextView textView=getView().findViewById(R.id.Test);
//        String temp=date+"t"+time.substring(0,5);
//
//
//
//
//
//
//        initData1(new AddNewData(systolic,diastolic,date,time,id) );
//        initRecyclerView1();
//
//                textView.append(""+dateList.get(0)+" "+dateList.get(1));


        series.appendData(new DataPoint(80,120),true,100);
        series.appendData(new DataPoint(90,140),true,100);
        series.appendData(new DataPoint(100,90),true,100);


        super.onViewCreated(view, savedInstanceState);
    }

}