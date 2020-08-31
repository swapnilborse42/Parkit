package com.example.swapnil.parkit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.icu.text.IDNA;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.StringTokenizer;

public class Accept_Details extends AppCompatActivity {
EditText etname,etmail1,etmob,etaddress,etcar;
    Button submit;
    String final1;
    ProgressDialog progressDialog;


    static Accept_Details INSTANCE;
    String data,data1,parkingname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept__details);
        //get data from marker activty
          parkingname=markerActivity.getActivityInstance().getData3();

         INSTANCE=this;
         Toast.makeText(INSTANCE,parkingname, Toast.LENGTH_SHORT).show();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator( getResources().getDrawable(R.drawable.outline_home_white_24dp));
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        etname=(EditText)findViewById(R.id.etname1);
        etmail1=(EditText)findViewById(R.id.etmail1);
        etmob=(EditText)findViewById(R.id.etmob);
        etaddress=(EditText)findViewById(R.id.etaddress);
        etcar=(EditText)findViewById(R.id.etcar);
        submit=(Button)findViewById(R.id.submit);
        progressDialog =new ProgressDialog(this);
    }
    public static Accept_Details getActivityInstance()
    {
        return INSTANCE;
    }

    public String getData()
    {
        return this.data;
    }
    public String getData1()
    {
        return this.data1;
    }


    public void submit(View view) {


        progressDialog.setMessage("processing ....!!!!");
        progressDialog.setMessage("processing ....!!!!");
        progressDialog.show();
        progressDialog.show();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef1 = database.getReference();

        myRef1.child("Customers").child(etmail1.getText().toString()).child("Email:").setValue(etmail1.getText().toString());
        myRef1.child("Customers").child(etmail1.getText().toString()).child("Name:").setValue(etname.getText().toString());
        myRef1.child("Customers").child(etmail1.getText().toString()).child("Phone:").setValue(etmob.getText().toString());
        myRef1.child("Customers").child(etmail1.getText().toString()).child("Address:").setValue(etaddress.getText().toString());
        myRef1.child("Customers").child(etmail1.getText().toString()).child("Car_no:").setValue(etcar.getText().toString());
        myRef1.child("Customers").child(etmail1.getText().toString()).child("Parking_name:").setValue(parkingname);


        String aname=etname.getText().toString().trim();
        String aemail=etmail1.getText().toString().trim();
        String dummy1=aemail.concat(" "+aname);

        String aaadress=etaddress.getText().toString().trim();
        String acarno=etcar.getText().toString().trim();
        String dummy2=aaadress.concat("\n"+acarno);
        String dummy3=dummy1.concat("\n"+dummy2);
        String amob=etmob.getText().toString().trim();
        String finaldummy=dummy3.concat("\n"+amob);

        data=finaldummy;
        data1=aemail;


        StringTokenizer tokenizer=new StringTokenizer(data," ");
        String first=tokenizer.nextToken();
       // String se=tokenizer.nextToken();
        Toast.makeText(INSTANCE, first, Toast.LENGTH_SHORT).show();

        progressDialog.dismiss();


        if(TextUtils.isEmpty(acarno))
        {
            //if email is empty and user press button
            Toast.makeText(this,"please enter carno",Toast.LENGTH_SHORT).show();
            return;//stop funtion from executing further..

        }
        if(TextUtils.isEmpty(aname))
        {
            //if email is empty and user press button
            Toast.makeText(this,"please enter name",Toast.LENGTH_SHORT).show();
            return;//stop funtion from executing further..

        }

        if(TextUtils.isEmpty(aaadress))
        {
            //if email is empty and user press button
            Toast.makeText(this,"please enter address",Toast.LENGTH_SHORT).show();
            return;//stop funtion from executing further..

        }
        if(TextUtils.isEmpty(aemail))
        {
            //if email is empty and user press button
            Toast.makeText(this,"please enter email id",Toast.LENGTH_SHORT).show();
            return;//stop funtion from executing further..

        }

        if(TextUtils.isEmpty(amob))
        {
            //if email is empty and user press button
            Toast.makeText(this,"please enter phone number",Toast.LENGTH_SHORT).show();
            return;//stop funtion from executing further..
        }

        Intent intent = new Intent(this, QR.class);
        startActivity(intent);





    }

}
