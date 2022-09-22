package com.example.itiproject.RoomDatabase;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.LinkedHashMap;

import kotlin.jvm.JvmStatic;

public class Converter {

    @TypeConverter
    @JvmStatic
    public static LinkedHashMap stringToMap(String value) {
      //  (new Gson()).fromJson(value, (new TypeToken() {}).getType());
        return new Gson().fromJson(value, LinkedHashMap.class);
    }

    @TypeConverter
    @JvmStatic
    public static String stringFromMap(LinkedHashMap hashMap) {
        return  new Gson().toJson(hashMap);
    }


}
