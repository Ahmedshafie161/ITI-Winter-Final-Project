package com.example.itiproject.AddOrder;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.itiproject.Util.Pojo.UtilPojoInterface;

import java.util.LinkedHashMap;
@Entity
public class AddOrderAggregateData implements UtilPojoInterface {
    public static final String PRODUCT_NAME = "productName";
    public static final String SHOP_NAME = "shopName";
    public static final String Quantity = "quantity";
    public static final String DATE = "date";

    @PrimaryKey (autoGenerate = true)
    public long id ;
    @ColumnInfo
    public LinkedHashMap attributeMap = new LinkedHashMap();
    public AddOrderAggregateData(){
        attributeMap.put(PRODUCT_NAME,"");
        attributeMap.put(SHOP_NAME,"");
        attributeMap.put(Quantity,"");
        attributeMap.put(DATE,"");
    }
    @Override
    public LinkedHashMap getAttributeMap() {
        return attributeMap;
    }

    @Override
    public void setAttributeMap(LinkedHashMap linkedHashMap) {
        this.attributeMap = linkedHashMap;
    }
}
