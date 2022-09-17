package com.example.itiproject.Util;

import android.content.Intent;
import android.net.Uri;

import androidx.appcompat.app.AppCompatActivity;

//    <uses-permission android:name="android.permission.CALL_PHONE" />
public class UtilCall {
    static String numberCall ;
    public static void call (AppCompatActivity appCompatActivity){
        try {
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:" + numberCall));//change the number
        appCompatActivity.startActivity(callIntent);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void setNumber(String number) {
        numberCall = number;
    }
}
