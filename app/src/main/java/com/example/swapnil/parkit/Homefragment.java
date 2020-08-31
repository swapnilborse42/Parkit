package com.example.swapnil.parkit;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 */
public class Homefragment extends Fragment implements View.OnClickListener {
    Context context4;
    CardView tip;
    CardView policies;
    CardView aboutus;
    CardView logout;
    CardView help;
    FirebaseAuth firebaseAuth;



    public Homefragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homefragment, container, false);
        context4 = view.getContext();
        tip = (CardView) view.findViewById(R.id.tip);
        tip.setOnClickListener(this);
        policies = (CardView) view.findViewById(R.id.policies);
        policies.setOnClickListener(this);
        aboutus = (CardView) view.findViewById(R.id.About);
        aboutus.setOnClickListener(this);
        logout = (CardView) view.findViewById(R.id.logout);
        logout.setOnClickListener(this);
        help = (CardView) view.findViewById(R.id.help1);
        help.setOnClickListener(this);

        return view;


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tip:
                Intent i = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://sites.google.com/view/parkit-move-smart-move-fast/tips?authuser=0"));
                startActivity(i);
                break;
            case R.id.policies:
                Intent i1 = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://sites.google.com/view/parkit-move-smart-move-fast/policies?authuser=0"));
                startActivity(i1);
                break;
            case R.id.About:
                Intent i2 = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://sites.google.com/view/parkit-move-smart-move-fast/about-us?authuser=0"));
                startActivity(i2);
                break;
            case R.id.help1:
                Intent intent1=new Intent(context4,helpActivity.class);
                startActivity(intent1);
                break;

            case R.id.logout:
                firebaseAuth=FirebaseAuth.getInstance();
                firebaseAuth.signOut();

                Intent intent=new Intent(context4,login.class);
                startActivity(intent);
                break;
        }

    }
}
