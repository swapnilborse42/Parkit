package com.example.swapnil.parkit;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class markerActivity extends FragmentActivity implements OnMapReadyCallback {

    private final static String TAG = "markerActivity";
    private GoogleMap mMap;
    private final static int MY_PERMISSION_FINE_LOCATION = 101;
    private EditText mserachText;
    private final static float DEFAULT_ZOOM = 15f;
    private Marker mKot;
    private Marker mKotb;
    private Marker mSwar;
    private Marker mpvg;
    static markerActivity INSTANCE;
    String parkingname,parking;

    LatLng latlag;
    Double l1;
    Double l2;

    Double datal1;
    Double datal2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marker);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        INSTANCE=this;
        mserachText=(EditText)findViewById(R.id.input_search);
    }

    public static markerActivity getActivityInstance()
    {
        return INSTANCE;
    }

    public String getData3()
    {
        return this.parkingname;
    }


    public Double getData4()
    {
        return this.datal1;
    }

    public Double getData5()
    {
        return this.datal2;
    }

    private void init()
    {

        Log.d(TAG,"init:intialising");
        mserachText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent KeyEvent) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || KeyEvent.getAction() == KeyEvent.ACTION_DOWN
                        ||KeyEvent.getAction()== KeyEvent.KEYCODE_ENTER){

                    //execute method for searching
                    geoLocate();

                }

                return false;
            }
        });
        hidesoftkeyboard();
    }

    private void geoLocate()
    {

        Log.d(TAG,"geoLocate: geolocating");
        String searchString = mserachText.getText().toString();

        Geocoder geocoder=new Geocoder( markerActivity.this);
        List<Address> list= new ArrayList<>();
        try{
            list=geocoder.getFromLocationName(searchString,1);
        }catch (IOException e){
            Log.e(TAG,"geoLocate: IOException: " + e.getMessage() );
        }

        if(list.size() > 0) {
            Address address=list.get(0);

            Log.d(TAG,"geoLocate: found a location" + address.toString());
            //  Toast.makeText(this,address.toString(), Toast.LENGTH_SHORT).show();
            moveCamera(new LatLng(address.getLatitude(), address.getLongitude()), DEFAULT_ZOOM,address.getAddressLine(0));


        }
    }

    private void moveCamera(LatLng latlng,float zoom,String title) {
        Log.d(TAG, "moveCamera: moving camera to: lat:" + latlng.latitude + ", lng:" + latlng.longitude);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, zoom));

        MarkerOptions options = new MarkerOptions()
                .position(latlng)
                .alpha(0.7f)
                .title(title);
        mMap.addMarker(options);
        hidesoftkeyboard();
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Integer clickCount = (Integer) marker.getTag();
                if (clickCount != null) {
                    clickCount = clickCount + 1;
                    marker.setTag(clickCount);
                    mKot.setVisible(true);
                    mKot.setAlpha(1.6f);
                    mKotb.setVisible(true);
                    mKotb.setAlpha(1.6f);
                    mpvg.setVisible(true);
                    mpvg.setAlpha(1.6f);
                    mSwar.setVisible(true);
                    mSwar.setAlpha(1.6f);
                    //Intent intent=new Intent( MapsActivity.this, Booking.class);
                    //  startActivity(intent);

                }

                mKot.setVisible(true);
                mSwar.setVisible(true);

                if(marker.getTitle().equals("Sai_Parking")) {

                    Intent intent1 = new Intent(markerActivity.this, Sai_parking.class);
                    startActivity(intent1);
                    parkingname="Sai parking";
                    l1=18.5062007;
                    l2=73.7927666;

                    datal1 = l1;
                    datal2 = l2;

                }
                if(marker.getTitle().equals("Swargate_Parking")) {

                    Intent intent1 = new Intent(markerActivity.this, Swargate_Parking.class);
                    startActivity(intent1);
                    parkingname="Swargate parking";
                    l1=18.5012927;
                    l2=73.8627894;

                    datal1 = l1;
                    datal2 = l2;
                }
                if(marker.getTitle().equals("Pvg_Parking(Bike)")) {

                    Intent intent1 = new Intent(markerActivity.this, Pvg_Parking.class);
                    startActivity(intent1);
                    parkingname="Pvg_Parking(Bike)";
                    l1=18.5309221;
                    l2=73.8032782;

                    datal1 = l1;
                    datal2 = l2;
                }
                if(marker.getTitle().equals("Swargate_Parking(Bike)")) {

                    Intent intent1 = new Intent(markerActivity.this, Swargate_Parking_bike.class);
                    startActivity(intent1);
                    parkingname="Swargate_Parking(Bike)";
                    l1=18.48994;
                    l2=73.85244;

                    datal1 = l1;
                    datal2 = l2;
                }



                return false;
            }
        });

    }

    private void hidesoftkeyboard(){
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        /*LatLng sydney = new LatLng(18.505049, 73.7927643);
        mMap.addMarker(new MarkerOptions().position(sydney).snippet("sai parking services").title("sai parkings"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            mMap.setMyLocationEnabled(true);
            init();


        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_FINE_LOCATION);

            }
        }

        mKot = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(18.5062007,73.7927666))
                .title("Sai_Parking")
                .snippet("SAI PARKING SERVICES  CONTACT NO. 9876532142")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.car))
                .visible(true));

        mSwar = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(18.5012927,73.8627894))
                .title("Swargate_Parking")
                .snippet("Swargate_Parking Services  CONTACT NO. 9130547514")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.car))
                .visible(true));

        mKotb = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(18.5309221,73.8032782))
                .title("Swargate_Parking(Bike)")
                .snippet("Swargate_Parking Services CONTACT NO. 9130547514")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.motorcycle))
                .visible(true));
        mpvg= mMap.addMarker(new MarkerOptions()
                .position(new LatLng(18.48994,73.85244))
                .title("Pvg_Parking(Bike)")
                .snippet("PVG_Parking  CONTACT NO. 9130547514")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.motorcycle))
                .visible(true));


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSION_FINE_LOCATION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                        mMap.setMyLocationEnabled(true);


                    }

                } else {
                    Toast.makeText(getApplicationContext(), "this app requries location permissions to be granted", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }



}
