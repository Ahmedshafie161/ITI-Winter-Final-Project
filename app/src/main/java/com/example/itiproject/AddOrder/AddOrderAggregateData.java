package com.example.itiproject.AddOrder;

import com.example.itiproject.Util.Pojo.UtilPojoInterface;

import java.util.LinkedHashMap;

public class AddOrderAggregateData implements UtilPojoInterface {
    public static final String PRODUCT_NAME = "productName";
    public static final String SHOP_NAME = "shopName";
    public static final String Quantity = "quantity";
    public static final String DATE = "date";

    LinkedHashMap attributeMap = new LinkedHashMap();
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
