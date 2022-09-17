package com.example.itiproject.Shop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.itiproject.R;
import com.example.itiproject.Enums.EnumPojo;
import com.example.itiproject.Enums.EnumRecyclerView;
import com.example.itiproject.Util.GoogleMaps;
import com.example.itiproject.Util.UtilCall;
import com.example.itiproject.Util.Pojo.UtilPojo;
import com.example.itiproject.Util.Pojo.UtilPojoInterface;
import com.example.itiproject.Util.UtilRecyclerShow;
import com.example.itiproject.Util.UtilText;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ShopActivity extends AppCompatActivity {
    ArrayList<UtilPojoInterface> arrayList = new ArrayList<>();
    RecyclerView shopRecyclerView ;
    FusedLocationProviderClient fusedLocationProviderClient;
    String selectedLongitude;
    String selectedLatitude ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);



        String [] arrayValue1 = {"ahmed","cairo","10","1","2-3-2010"};
        arrayList= UtilPojo.intializePojoList(EnumPojo.ShopAggregateData,arrayValue1,arrayList);

        shopRecyclerView = findViewById(R.id.shop_recyclerView);
        shopRecyclerView=UtilRecyclerShow.showRecyclerView(arrayList,this, EnumRecyclerView.ShopMyRecyclerAdapter,shopRecyclerView);
        ShopMyRecyclerAdapter shopMyRecyclerAdapter = (ShopMyRecyclerAdapter) shopRecyclerView.getAdapter();
        shopMyRecyclerAdapter.setRecyclerActivityListener((name, number,latitude, longtitude) -> {
            UtilCall.setNumber(number);
            Toast.makeText(ShopActivity.this,"Shop Selected : "+name,Toast.LENGTH_SHORT).show();
            selectedLongitude = longtitude ;
            selectedLatitude = latitude ;
        });

        //showFloatButton();

    }

    void getLocation(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
              getLastLocation();
        }else {
             askLocationPermission();
        }

    }

    private void askLocationPermission() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION)){
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1001);

            }else {
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1001);

            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
       super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        if (requestCode==1001){
            if (grantResults.length>0 && grantResults[0]== getPackageManager().PERMISSION_GRANTED){
                getLastLocation();
            }else {

            }
        }
    }

    private void getLastLocation() {
        try{
            Task<Location> locationTask= fusedLocationProviderClient.getLastLocation();
            locationTask.addOnSuccessListener(location -> {
                if (location != null){
                    Toast.makeText(ShopActivity.this,"succ"+location.getLatitude(),Toast.LENGTH_SHORT).show();

                    selectedLongitude = String.valueOf(location.getLongitude());
                    selectedLatitude = String.valueOf(location.getLatitude());
                    Toast.makeText(ShopActivity.this, "lat "+selectedLatitude+" alt"+ selectedLongitude, Toast.LENGTH_SHORT).show();
                }
            });
            locationTask.addOnFailureListener(e -> {

            });

        }catch (SecurityException e){
e.printStackTrace();
        }
    }

    public void resetLastVisit(View view){
        ShopMyRecyclerAdapter shopMyRecyclerAdapter=(ShopMyRecyclerAdapter) shopRecyclerView.getAdapter();
        shopMyRecyclerAdapter.resetLastVisit();
    }

    public void shop_btnCall (View view){
        // user must manually allow permission
       UtilCall.call(this);
    }
    public void showFloatButton(View vieww){
        FloatingActionButton fab = findViewById(R.id.shop_btnFab);
        EditText editTextShopName = findViewById(R.id.shop_etShopName);
        EditText editTextVisit = findViewById(R.id.shop_etLastVisit);
        EditText editTextLocation = findViewById(R.id.shop_etShopLocation);
        EditText editTextPhone = findViewById(R.id.shop_etShopPhone);
        fab.setOnClickListener(view -> {
            String shopName =editTextShopName.getText().toString();
            String phone = editTextPhone.getText().toString();
            //  String location = editTextLocation.getText().toString();
            getLocation();

            String lastVisit = editTextVisit.getText().toString();

            if(!shopName.isEmpty()&&!phone.isEmpty()&&selectedLatitude!=null && selectedLongitude !=null&&!lastVisit.isEmpty()){

                String []valueArray = {shopName, selectedLongitude,selectedLatitude,phone,lastVisit};
                ShopAggregateData shopAggregateData = (ShopAggregateData) UtilPojo.intializePojo(EnumPojo.ShopAggregateData,valueArray);
                ShopMyRecyclerAdapter myRecyclerAdapter =(ShopMyRecyclerAdapter) shopRecyclerView.getAdapter();
                myRecyclerAdapter.addItem(shopAggregateData);
                UtilText.clearText(editTextShopName, editTextLocation, editTextPhone, editTextVisit);
                Toast.makeText(this, "added",Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void findLocation(View v ){
        if(!selectedLatitude.isEmpty()&&!selectedLongitude.isEmpty())
        {
            Log.d("myLocation", "long : "+ selectedLongitude + "lat "+selectedLatitude);
            Toast.makeText(ShopActivity.this, "lat "+selectedLatitude+" long"+ selectedLongitude, Toast.LENGTH_SHORT).show();
            GoogleMaps.launchGoogleMaps(this,Double.parseDouble(selectedLatitude),Double.parseDouble(selectedLongitude),"data");


        }

/*
        String format = "geo:0,0?q=" + Double.toString(1.2) + "," + Double.toString(2.5) + "(" + "ahmed" + ")";
        Uri uri = Uri.parse(format);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent); */
    }

    }


