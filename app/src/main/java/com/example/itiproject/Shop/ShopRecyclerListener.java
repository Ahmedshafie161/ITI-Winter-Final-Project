package com.example.itiproject.Shop;

import android.location.Location;

public interface ShopRecyclerListener {

    public void sendDataToActivity(String name, String number, String longitude, String latitude);
}
