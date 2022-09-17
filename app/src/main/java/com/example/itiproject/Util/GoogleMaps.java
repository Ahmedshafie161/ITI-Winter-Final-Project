package com.example.itiproject.Util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.itiproject.Shop.ShopActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.Task;

public class GoogleMaps {

    public static void launchGoogleMaps(Context context, double latitude, double longitude, String label) {
        String format = "geo:0,0?q=" + Double.toString(latitude) + "," + Double.toString(longitude) + "(" + label + ")";
        Uri uri = Uri.parse(format);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    private void askLocationPermission(Activity activity) {
        if(ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity,Manifest.permission.ACCESS_FINE_LOCATION)){
                ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1001);

            }else {
                ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1001);

            }
        }
    }

    /*
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

    private void getLastLocation(Context context) {
        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);
        ;
        try{
            Task<Location> locationTask= fusedLocationProviderClient.getLastLocation();
            locationTask.addOnSuccessListener(location -> {
                if (location != null){
                    Log.d("hello", "onSuccess: "+location.getLatitude());
                    Toast.makeText(context,"succ"+location.getLatitude(),Toast.LENGTH_SHORT).show();
                }

            });
            locationTask.addOnFailureListener(e -> {

            });

        }catch (SecurityException e){

        }
    } */

}
