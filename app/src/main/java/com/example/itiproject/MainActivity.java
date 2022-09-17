package com.example.itiproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.itiproject.AddOrder.AddOrderActivity;
import com.example.itiproject.Repair.RepairActivity;
import com.example.itiproject.Sell.SellActivity;
import com.example.itiproject.Shop.ShopActivity;
import com.example.itiproject.Store.StoreActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToStore(View view){
        navigate(StoreActivity.class);
    }
    public void goToSell(View view){
        navigate(SellActivity.class);
    }
    public void goToShops(View view){
        navigate(ShopActivity.class);
    }
    public void goToRepair(View view){
        navigate(RepairActivity.class);
    }
    public void goToAddOrder(View view){
        navigate(AddOrderActivity.class);
    }
    public void goToReport(View view){
        navigate(ReportActivity.class);
    }
    public void navigate(Class   reqClass){
        Intent intent = new Intent(this,reqClass);
        startActivity(intent);
    }
}