package com.example.itiproject.RoomDatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.itiproject.AddOrder.AddOrderAggregateData;
import com.example.itiproject.Repair.RepairAggregateData;
import com.example.itiproject.Sell.SellAggregateData;
import com.example.itiproject.Shop.ShopAggregateData;
import com.example.itiproject.Store.StoreAggregateData;

@Database(entities = {SellAggregateData.class
        ,RepairAggregateData.class, AddOrderAggregateData.class, ShopAggregateData.class, StoreAggregateData.class}, version = 1)
@TypeConverters(Converter.class)
public abstract class AppDatabase extends RoomDatabase {
    public  abstract  CustomDao getPojoDao();
    private static AppDatabase pojoDB;

    public static AppDatabase getInstance(Context context) {
        if (null == pojoDB) {
            pojoDB = buildDatabaseInstance(context);
        }
        return pojoDB;
    }

    private static AppDatabase buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context,
                AppDatabase.class,
                "DB_NAME")
                .allowMainThreadQueries().build();
    }

    public void cleanUp(){
        pojoDB = null;
    }
}
