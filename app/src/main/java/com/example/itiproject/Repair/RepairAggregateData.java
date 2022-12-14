package com.example.itiproject.Repair;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.itiproject.Util.Pojo.UtilPojoInterface;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

@Entity
public class RepairAggregateData implements UtilPojoInterface, Serializable {
    public static final String PRODUCT_NAME = "productName";
    public static final String SHOP_NAME = "shopName";
    public static final String PROBLEM_TYPE = "problemType";
    public static final String DESCRIPTION = "description";
    public static final String IS_SOLVED = "isSolved";

    @PrimaryKey (autoGenerate = true)
    public long id ;
    @ColumnInfo (name = "mapAttribute")
    public LinkedHashMap <String ,Object>attributeMap = new LinkedHashMap();

    public RepairAggregateData(){
        attributeMap.put(PRODUCT_NAME,"");
        attributeMap.put(SHOP_NAME,"");
        attributeMap.put(PROBLEM_TYPE,"");
        attributeMap.put(DESCRIPTION,"");
        attributeMap.put(IS_SOLVED,"");
    }
    @Override
    public LinkedHashMap getAttributeMap() {
        return attributeMap;
    }

    @Override
    public void setAttributeMap(LinkedHashMap linkedHashMap) {
        this.attributeMap = linkedHashMap;
    }

    @Override
    public String getName() {
        return null;
    }
    public String toString (){
        StringBuilder builder = new StringBuilder() ;
        builder.append(id+"\n");
        for (Map.Entry entry: attributeMap.entrySet()){
            builder.append(entry.getKey().toString());
            builder.append(" : ");
            builder.append(entry.getValue().toString());
            builder.append(" \n ");

        }
        return  builder.toString();

    }
}
