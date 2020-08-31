package com.example.swapnil.parkit;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

public class QR extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView1;
    private FrameLayout frame1;


    private QRfragment qRfragment;
    private Feedbackfragment feedbackfragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator( getResources().getDrawable(R.drawable.outline_home_white_24dp));
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        frame1=(FrameLayout)findViewById(R.id.frame11);
        bottomNavigationView1 = (BottomNavigationView) findViewById(R.id.main_nav1);

        qRfragment = new QRfragment();
        feedbackfragment = new Feedbackfragment();
        setFragment(qRfragment);

        bottomNavigationView1.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem1) {


                switch (menuItem1.getItemId()) {

                    case R.id.qr1:
                        bottomNavigationView1.setBackgroundResource(R.color.colorPrimary);
                        setFragment(qRfragment);
                        // intent=new Intent(getBaseContext(),login.class);
                        // startActivity(intent);

                        return true;

                    case R.id.feedback1:
                        bottomNavigationView1.setBackgroundResource(R.color.colorPrimary);
                        setFragment(feedbackfragment);
                        return true;

                    default:
                        return false;

                }
            }
        });


    }


    private void setFragment(Fragment qrfrag) {//just a normal fragment

        FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
        fragmentTransaction1.replace(R.id.frame11, qrfrag);
        fragmentTransaction1.commit();
    }
}

