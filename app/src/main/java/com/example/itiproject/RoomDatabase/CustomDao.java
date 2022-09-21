package com.example.itiproject.RoomDatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.itiproject.AddOrder.AddOrderAggregateData;
import com.example.itiproject.Repair.RepairAggregateData;
import com.example.itiproject.Sell.SellAggregateData;
import com.example.itiproject.Shop.ShopAggregateData;
import com.example.itiproject.Store.StoreAggregateData;

import java.util.List;
@Dao
public interface CustomDao {

    // RepairAgg
    @Query("SELECT * FROM repairAggregateData")
    List<RepairAggregateData> getRepairAll();

    @Insert
    long insert (RepairAggregateData task);

    @Delete
    void delete(RepairAggregateData storeAggregateData);

    @Update
    void update(RepairAggregateData storeAggregateData);

    // AddOrder
    @Query("SELECT * FROM addorderaggregatedata")
    List<AddOrderAggregateData> getAllAddOrder();

    @Insert
    long insert (AddOrderAggregateData task);

    @Delete
    void delete(AddOrderAggregateData addOrderAggregateData);

    @Update
    void update(AddOrderAggregateData addOrderAggregateData);

    // Store
    @Query("SELECT * FROM storeaggregatedata")
    List<StoreAggregateData> getAllStore();
/*    @Query("SELECT * FROM storeaggregatedata WHERE ")
    StoreAggregateData getStoreWithName(String storeName);*/

    @Insert
    long insert (StoreAggregateData task);

    @Delete
    void delete(StoreAggregateData storeAggregateData);

    @Update
    void update(StoreAggregateData storeAggregateData);

    // Shop
    @Query("SELECT * FROM shopaggregatedata")
    List<ShopAggregateData> getShopList();

    @Insert
    long insert (ShopAggregateData task);

    @Delete
    void delete(ShopAggregateData shopAggregateData);

    @Update
    void update(ShopAggregateData shopAggregateData);

    // Sell
    @Query("SELECT * FROM sellaggregatedata")
    List<SellAggregateData> getSellList();

    @Insert
    long insert (SellAggregateData task);

    @Delete
    void delete(SellAggregateData sellAggregateData);

    @Update
    void update(SellAggregateData sellAggregateData);


}
