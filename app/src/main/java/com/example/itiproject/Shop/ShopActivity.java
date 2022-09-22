package com.example.itiproject.Shop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.itiproject.R;
import com.example.itiproject.Enums.EnumPojo;
import com.example.itiproject.Enums.EnumRecyclerView;
import com.example.itiproject.RoomDatabase.AppDatabase;
import com.example.itiproject.Util.GoogleMaps;
import com.example.itiproject.Util.UtilCall;
import com.example.itiproject.Util.Pojo.UtilPojo;
import com.example.itiproject.Util.UtilRecyclerShow;
import com.example.itiproject.Util.UtilText;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class ShopActivity extends AppCompatActivity {
    RecyclerView recyclerView ;
    FusedLocationProviderClient fusedLocationProviderClient;
    String selectedLongitude;
    String selectedLatitude ;
    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        appDatabase = AppDatabase.getInstance(ShopActivity.this);
        recyclerView = findViewById(R.id.shop_recyclerView);
        new ShowTask(this).execute();


        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);



      //  String [] arrayValue1 = {"ahmed","cairo","10","1","2-3-2010"};
      //  arrayList= UtilPojo.intializePojoList(EnumPojo.ShopAggregateData,arrayValue1,arrayList,ShopAggregateData.class);




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
            if (grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
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
        ShopMyRecyclerAdapter shopMyRecyclerAdapter=(ShopMyRecyclerAdapter) recyclerView.getAdapter();
        assert shopMyRecyclerAdapter != null;
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
                ShopAggregateData shopAggregateData =  UtilPojo.getPojoFromArray(EnumPojo.ShopAggregateData,valueArray,ShopAggregateData.class);
                ShopMyRecyclerAdapter myRecyclerAdapter =(ShopMyRecyclerAdapter) recyclerView.getAdapter();
                assert myRecyclerAdapter != null;
                myRecyclerAdapter.addItem(shopAggregateData);
                UtilText.clearText(editTextShopName, editTextLocation, editTextPhone, editTextVisit);
                Toast.makeText(this, "added",Toast.LENGTH_SHORT).show();
                new InsertTask(this,shopAggregateData).execute();

            }
        });
    }
    public void findLocation(View v ){
        if(selectedLatitude!=null&&selectedLongitude!=null)
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

    private static class InsertTask extends AsyncTask<Void,Void,Boolean> {

        private WeakReference<ShopActivity> activityWeakReference;
        private ShopAggregateData shopAggregateData;

        InsertTask(ShopActivity  shopActivity,ShopAggregateData shopAggregateData){
            activityWeakReference=new WeakReference<>(shopActivity);
            this.shopAggregateData=shopAggregateData;

        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            long j = activityWeakReference.get().appDatabase.getPojoDao().insert(shopAggregateData);
            shopAggregateData.id = j ;
            Log.e("ID ", "doInBackground: " + j);
            return true;
        }

        @Override
        protected void onPostExecute(Boolean o) {
            if (o){
                Toast.makeText(activityWeakReference.get(),"succ"+shopAggregateData.id,Toast.LENGTH_LONG).show();
                //     activityWeakReference.get().setResult(repairAggregateData.id);
                //      activityWeakReference.get().finish();
            }
        }
    }
    private static class ShowTask extends AsyncTask <Void,Void,Boolean> {
        List<ShopAggregateData> shopAggregateDataList;

        private WeakReference<ShopActivity> activityWeakReference;


        ShowTask(ShopActivity shopActivity){
            activityWeakReference=new WeakReference<>(shopActivity);
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            shopAggregateDataList  = activityWeakReference.get().appDatabase.getPojoDao().getShopList();
/*
            for (RepairAggregateData repairAggregateData:repairAggregateDataList){
                for(Object value:repairAggregateData.attributeMap.values()){
                    Log.e("Romm_return_value",value.toString());

                }
                Log.e("Romm_return_map", String.valueOf(repairAggregateData.id));
            }
*/
            return true;
        }

        @Override
        protected void onPostExecute(Boolean o) {
            if (o){
                activityWeakReference.get().recyclerView= UtilRecyclerShow.showRecyclerView((ArrayList) shopAggregateDataList,activityWeakReference.get(), EnumRecyclerView.ShopMyRecyclerAdapter,activityWeakReference.get().recyclerView);
               ShopMyRecyclerAdapter shopMyRecyclerAdapter = (ShopMyRecyclerAdapter) activityWeakReference.get().recyclerView.getAdapter();

                shopMyRecyclerAdapter.setRecyclerActivityListener((name, number, latitude, longtitude) -> {
                    UtilCall.setNumber(number);
                    Toast.makeText(activityWeakReference.get(),"Shop Selected : "+name,Toast.LENGTH_SHORT).show();
                    activityWeakReference.get().selectedLongitude = longtitude ;
                    activityWeakReference.get().selectedLatitude = latitude ;
                });
/*
                for (RepairAggregateData repairAggregateData :activityWeakReference.get().repairAggregateDataList)
                {
                    for (Object object : repairAggregateData.attributeMap.values()){
                        Log.e("RoomRecieved", "onPostExecute: "+object.toString() );
                    }
                }
*/
                //  Toast.makeText(activityWeakReference.get(),"succ"+repairAggregateDataList.get(repairAggregateDataList.size()-1).id,Toast.LENGTH_LONG).show();

            }
        }
    }
    }


