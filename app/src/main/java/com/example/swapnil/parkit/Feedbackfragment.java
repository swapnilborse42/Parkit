package com.example.swapnil.parkit;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class Feedbackfragment extends Fragment {
    Button b2;
    Context context;
    Double data;
    Double data1;


    public Feedbackfragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        View v= inflater.inflate(R.layout.fragment_feedbackfragment, container, false);
        context = v.getContext(); // Assign your rootView to context
        data=markerActivity.getActivityInstance().getData4();
        data1=markerActivity.getActivityInstance().getData5();
        b2=(Button) v.findViewById(R.id.buttonnav2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?daddr="+data+","+data1));
                startActivity(intent);
            }
        });
        return v;
    }

}
