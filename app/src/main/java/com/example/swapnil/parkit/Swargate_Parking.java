package com.example.swapnil.parkit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Swargate_Parking extends AppCompatActivity {
    TextView total_park;
    int total=function();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swargate__parking);
        total_park= (TextView) findViewById(R.id.total_park3);


    }

    public void dec1(View view) {
       // parking="Swargate_parking";
       // parkingname=parking;

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        myRef.child("Swargate_Parking").child("total").setValue(total);
        total=total-1;
        myRef.child("Swargate_Parking").child("total").setValue(total);
        // textView2.setText(total);
        final DatabaseReference orders_Reference = myRef.child("Swargate_Parking");

        orders_Reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    if(data.getKey().equals("total")){
                        String orderNumber = data.getValue().toString();
                        Log.d("Specific Node Value",orderNumber);
                        total_park.setText(orderNumber);

                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        startActivity(new Intent(getApplicationContext(),Accept_Details.class));
    }

    public int function() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef1 = database.getReference();


        final DatabaseReference orders_Reference = myRef1.child("Swargate_Parking");

        orders_Reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    if(data.getKey().equals("total")){
                        String orderNumber = data.getValue().toString();
                        Log.d("Specific Node Value", orderNumber);
                        //Toast.makeText(MainActivity.this, total, Toast.LENGTH_SHORT).show();
                        // total= Integer.parseInt(orderNumber);

                        //String val=textView3.getText().toString();
                        total=Integer.parseInt(orderNumber);
                        total_park.setText(orderNumber);


                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        return total;

    }

    public void back2(View view) {

        startActivity(new Intent(getApplicationContext(),markerActivity.class));
    }
}
