package com.example.itiproject.RoomDatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.itiproject.Repair.RepairAggregateData;

import java.util.List;
@Dao
public interface CustomDao {
    @Query("SELECT * FROM repairAggregateData")
    List<RepairAggregateData> getAll();

    @Insert
    long insert (RepairAggregateData task);

    @Delete
    void delete(RepairAggregateData storeAggregateData);

    @Update
    void update(RepairAggregateData storeAggregateData);

}
