package com.example.swapnil.parkit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Feedback extends AppCompatActivity {
    String data1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        final RatingBar mRatingBar = (RatingBar) findViewById(R.id.ratingBar);
        final TextView mRatingScale = (TextView) findViewById(R.id.tvRatingScale);
        Button mSendFeedback = (Button) findViewById(R.id.btnSubmit);

        data1=Accept_Details.getActivityInstance().getData1();
        Toast.makeText(this, data1, Toast.LENGTH_SHORT).show();


        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                mRatingScale.setText(String.valueOf(rating));
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef2 = database.getReference();

                switch ((int) ratingBar.getRating()) {
                    case 1:
                        mRatingScale.setText("Very bad");
                        myRef2.child("Customers").child(data1).child("Feedback:").setValue(mRatingScale.getText().toString());
                        break;
                    case 2:
                        mRatingScale.setText("Need some improvement");
                        myRef2.child("Customers").child(data1).child("Feedback:").setValue(mRatingScale.getText().toString());
                        break;
                    case 3:
                        mRatingScale.setText("Good");
                        myRef2.child("Customers").child(data1).child("Feedback:").setValue(mRatingScale.getText().toString());
                        break;
                    case 4:
                        mRatingScale.setText("Great");
                        myRef2.child("Customers").child(data1).child("Feedback:").setValue(mRatingScale.getText().toString());
                        break;
                    case 5:
                        mRatingScale.setText("Awesome. I love it");
                        myRef2.child("Customers").child(data1).child("Feedback:").setValue(mRatingScale.getText().toString());
                        break;
                    default:
                        mRatingScale.setText("");
                }
            }
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef1 = database.getReference();

        });

        mSendFeedback.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                    mRatingBar.setRating(0);
                Toast.makeText(Feedback.this, "Thank you for sharing your feedback", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


