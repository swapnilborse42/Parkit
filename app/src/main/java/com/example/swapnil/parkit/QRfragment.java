package com.example.swapnil.parkit;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;


/**
 * A simple {@link Fragment} subclass.
 */
public class QRfragment extends Fragment implements View.OnClickListener {
    Context context;
    CardView qr;
    CardView feedback;

    public QRfragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_qrfragment, container, false);
        context = view.getContext();
        qr = (CardView) view.findViewById(R.id.cardview);
        qr.setOnClickListener(this);
        feedback = (CardView) view.findViewById(R.id.cardview1);
        feedback.setOnClickListener(this);


        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cardview:
                Intent intent = new Intent(context, QRgenerator.class);
                startActivity(intent);
                break;
            case R.id.cardview1:
                Intent intent1 = new Intent(context, Feedback.class);
                startActivity(intent1);
                break;

        }
    }
}

