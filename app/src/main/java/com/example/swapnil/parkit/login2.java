package com.example.swapnil.parkit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login2 extends AppCompatActivity implements View.OnClickListener{
    EditText etemail2;
    EditText etpass2;
    Button buttonRegister2;
    TextView alreadyacc2;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    TextView forget;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        firebaseAuth=FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser()!= null)//this is  for if one loged previously then direct get login
        {
            //next activity here...
            finish();
            startActivity(new Intent(getApplicationContext(), Home.class));
            Toast.makeText(login2.this,"yupp!!!! ",Toast.LENGTH_SHORT).show();
        }

        progressDialog =new ProgressDialog(this);

        etemail2=(EditText)findViewById(R.id.etemail2);
        etpass2=(EditText)findViewById(R.id.etpass2);
        buttonRegister2=(Button) findViewById(R.id.register2);
        alreadyacc2=(TextView)findViewById(R.id.alreadyacc4);
        forget=(TextView)findViewById(R.id.forget);
        buttonRegister2.setOnClickListener(this);
        alreadyacc2.setOnClickListener(this);
        forget.setOnClickListener(this);
    }

    private void userLogin()
    {
        String username=etemail2.getText().toString().trim();
        String password=etpass2.getText().toString().trim();

        if(TextUtils.isEmpty(username))
        {
            //if email is empty and user press button
            Toast.makeText(this,"please enter email",Toast.LENGTH_SHORT).show();
            return;//stop funtion from executing further..

        }

        if(TextUtils.isEmpty(password))
        {
            //if pass is empty and user press button
            Toast.makeText(this,"please enter pass",Toast.LENGTH_SHORT).show();
            return;//stop funtion from executing further..
        }
        progressDialog.setMessage("Processing ....!!!!");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(username,password)
                .addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful())
                        {    Toast.makeText(login2.this,"Welcome!!!! ",Toast.LENGTH_SHORT).show();
                            //successfully
                           // finish();
                             progressDialog.dismiss();
                            startActivity(new Intent(getApplicationContext(),Home.class));
                        }
                        else
                        {
                            Toast.makeText(login2.this,"Not Registered...!!!! ",Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }



    @Override
    public void onClick(View v) {
        if(v==buttonRegister2)
        {
            userLogin();
        }

        if(v==alreadyacc2)
        {
            startActivity(new Intent(getApplicationContext(),login.class));
        }
        if (v==forget)
        {
            startActivity(new Intent(getApplicationContext(),Resetpass.class));
        }

    }
}
