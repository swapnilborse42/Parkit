package com.example.swapnil.parkit;


//import android.app.FragmentTransaction;   
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private FrameLayout frame;

     private Homefragment homefragment;
     private Navfragmant navfragment;
     FirebaseAuth firebaseAuth;

     //my website url:https://sites.google.com/view/parkit-move-smart-move-fast/tips?authuser=0

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        firebaseAuth= FirebaseAuth.getInstance();//it will initialise our fireAuth object
        if (firebaseAuth.getCurrentUser()==null)
        {   finish();
            startActivity(new Intent(this,Home.class));
        }

        //FirebaseUser user = firebaseAuth.getCurrentUser();


        frame=(FrameLayout)findViewById(R.id.frame);

        bottomNavigationView=(BottomNavigationView)findViewById(R.id.main_nav);

        homefragment=new Homefragment();
        navfragment=new Navfragmant();

        setFragment(homefragment);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
              //  Intent intent;

                switch (menuItem.getItemId()){

                    case R.id.menu_home :
                        bottomNavigationView.setBackgroundResource(R.color.colorPrimary);
                           setFragment(homefragment);
                         // intent=new Intent(getBaseContext(),login.class);
                         // startActivity(intent);

                        return true;

                    case R.id.menu_nav :
                        bottomNavigationView.setBackgroundResource(R.color.colorPrimary);
                            setFragment(navfragment);
                        return true;

                        default:
                            return false;
                }



            }
        });



    }

    private void setFragment(Fragment homefragment) {//method called from above

        FragmentTransaction fragmentTransaction =  getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame,homefragment);
        fragmentTransaction.commit();

    }
}
