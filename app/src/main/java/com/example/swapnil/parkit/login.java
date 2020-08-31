package com.example.swapnil.parkit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity implements View.OnClickListener{

        EditText etemail;
        EditText etpass;
        Toolbar toolbar;
        Button buttonRegister;
        TextView alreadyacc;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /*FragmentManager Manager=getSupportFragmentManager();
        FragmentTransaction t=Manager.beginTransaction();
        Homefragment homefragment=new Homefragment();
        t.add(R.id.constrain,homefragment);
        t.commit();*/

        progressDialog =new ProgressDialog(this);

        firebaseAuth=FirebaseAuth.getInstance();

      if (firebaseAuth.getCurrentUser()!=null)//this is  for if one load previously then direct get login
        {
            //next activity here...
            finish();
            startActivity(new Intent(getApplicationContext(),Home.class  ));
              Toast.makeText(login.this,"Welcome!!!! ",Toast.LENGTH_SHORT).show();
        }

        etemail=(EditText)findViewById(R.id.etemail);
        etpass=(EditText)findViewById(R.id.etpass);
        buttonRegister=(Button) findViewById(R.id.register);
        alreadyacc=(TextView)findViewById(R.id.alreadyacc);

        buttonRegister.setOnClickListener(this);
        alreadyacc.setOnClickListener(this);
    }

    private void registerUser()
    {
        String username=etemail.getText().toString().trim();
        String password=etpass.getText().toString().trim();

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
        progressDialog.setMessage("Registering ....!!!!");
        progressDialog.show();


        firebaseAuth.createUserWithEmailAndPassword(username,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            //successfully registred
                            progressDialog.dismiss();
                            Toast.makeText(login.this,"Registered successfully...!!!",Toast.LENGTH_SHORT).show();
                            //finish();
                            //startActivity(new Intent(getApplicationContext(),aftercustomerlogin.class));
                        }
                        else
                        {
                            Toast.makeText(login.this,"Wrong id or pass!!!! ",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    @Override
    public void onClick(View v) {

        if(v==buttonRegister)
        {
            registerUser();
        }

        if(v==alreadyacc)
        {
            Intent intent1=new Intent(this,login2.class);
            startActivity(intent1);
            //startActivity(new Intent(getApplicationContext(),login2.class));
        }

    }
}
