package com.example.swapnil.parkit;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Resetpass extends AppCompatActivity {

      EditText putemail;
      Button sendemail;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpass);

        putemail=(EditText)findViewById(R.id.putemail);
        sendemail=(Button)findViewById(R.id.sendemail);
        firebaseAuth=FirebaseAuth.getInstance();

        sendemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String resetmail=putemail.getText().toString().trim();
                if(TextUtils.isEmpty(resetmail))
                {
                    //if email is empty and user press button
                    Toast.makeText(Resetpass.this, "Enter email", Toast.LENGTH_SHORT).show();
                    return;//stop funtion from executing further..

                }
                else
                {
                    firebaseAuth.sendPasswordResetEmail(resetmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful())
                            {    Toast.makeText(Resetpass.this,"Please check your email!!!! ",Toast.LENGTH_SHORT).show();

                                startActivity(new Intent(getApplicationContext(),login2.class));
                            }
                            else
                            {
                                Toast.makeText(Resetpass.this,"Something went wrong...!!!! ",Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });

    }
}
