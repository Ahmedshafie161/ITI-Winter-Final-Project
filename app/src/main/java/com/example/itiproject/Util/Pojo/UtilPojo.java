package com.example.itiproject.Util.Pojo;

import com.example.itiproject.AddOrder.AddOrderAggregateData;
import com.example.itiproject.Enums.EnumPojo;
import com.example.itiproject.Repair.RepairAggregateData;
import com.example.itiproject.Shop.ShopAggregateData;
import com.example.itiproject.Store.StoreAggregateData;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class UtilPojo {

    public static <T extends UtilPojoInterface> ArrayList <T> intializePojoList(EnumPojo pojoClassName, String[] arrayValues, ArrayList<T>  pojoArrayList,Class<T>className) {
        if (pojoArrayList == null) {
            pojoArrayList = new ArrayList<T>();
        }
/*        ArrayList<T> pojoList = new ArrayList<>();
        for (T item :pojoArrayList){
            pojoList.add(item);
        }*/
        pojoArrayList.add(intializePojo(pojoClassName, arrayValues, className));
        return pojoArrayList;
    }

    public static <T extends UtilPojoInterface> T intializePojo(EnumPojo pojoClassName, String[] arrayValues,Class<T>className) {

        LinkedHashMap hashMap;
        UtilPojoInterface aggregateData = null;

        if (pojoClassName.equals(EnumPojo.StoreAggregateData)) {
            aggregateData = new StoreAggregateData();

        } else if (pojoClassName.equals(EnumPojo.ShopAggregateData)) {
            //        AggregateData = new ShopAggregateData((String) mapAttributes.get("shopName"),(String) mapAttributes.get("location"),(String)mapAttributes.get("phone"));
            aggregateData = new ShopAggregateData();

        } else if (pojoClassName.equals(EnumPojo.RepairAggregateData)) {
            //        AggregateData = new ShopAggregateData((String) mapAttributes.get("shopName"),(String) mapAttributes.get("location"),(String)mapAttributes.get("phone"));
            aggregateData = new RepairAggregateData();

        } else if (pojoClassName.equals(EnumPojo.AddOrderAggregateData)) {
            //        AggregateData = new ShopAggregateData((String) mapAttributes.get("shopName"),(String) mapAttributes.get("location"),(String)mapAttributes.get("phone"));
            aggregateData = new AddOrderAggregateData();

        } else {
            return null;
        }
        hashMap = aggregateData.getAttributeMap();
        List<String> keys = new ArrayList<String>(hashMap.keySet());
        for (int i = 0; i < keys.size(); i++) {
            hashMap.put(keys.get(i), arrayValues[i]);
            aggregateData.setAttributeMap(hashMap);
        }

        return (T) aggregateData;
    }

   /* public static ArrayList intializePojoList(EnumPojo pojoClassName, String[] arrayValues, ArrayList<UtilPojoInterface> pojoArrayList) {
        if (pojoArrayList == null) {
            pojoArrayList = new ArrayList<UtilPojoInterface>();
        }
        pojoArrayList.add(intializePojo(pojoClassName, arrayValues));
        return pojoArrayList;
    }

    public static UtilPojoInterface intializePojo(EnumPojo pojoClassName, String[] arrayValues) {

        LinkedHashMap hashMap;
        UtilPojoInterface aggregateData = null;

        if (pojoClassName.equals(EnumPojo.StoreAggregateData)) {
            aggregateData = new StoreAggregateData();

        } else if (pojoClassName.equals(EnumPojo.ShopAggregateData)) {
            //        AggregateData = new ShopAggregateData((String) mapAttributes.get("shopName"),(String) mapAttributes.get("location"),(String)mapAttributes.get("phone"));
            aggregateData = new ShopAggregateData();

        } else if (pojoClassName.equals(EnumPojo.RepairAggregateData)) {
            //        AggregateData = new ShopAggregateData((String) mapAttributes.get("shopName"),(String) mapAttributes.get("location"),(String)mapAttributes.get("phone"));
            aggregateData = new RepairAggregateData();

        } else if (pojoClassName.equals(EnumPojo.AddOrderAggregateData)) {
            //        AggregateData = new ShopAggregateData((String) mapAttributes.get("shopName"),(String) mapAttributes.get("location"),(String)mapAttributes.get("phone"));
            aggregateData = new AddOrderAggregateData();

        } else {
            return null;
        }
        hashMap = aggregateData.getAttributeMap();
        List<String> keys = new ArrayList(hashMap.keySet());
        for (int i = 0; i < keys.size(); i++) {
            hashMap.put(keys.get(i), arrayValues[i]);
            aggregateData.setAttributeMap(hashMap);
        }

        return aggregateData;
    }



    public static < UtilPojoInterface> ArrayList intializePojoList3(EnumPojo pojoClassName, String[] arrayValues, ArrayList<? extends  UtilPojoInterface>  pojoArrayList) {
        if (pojoArrayList == null) {
            pojoArrayList = new ArrayList<UtilPojoInterface>();
        }
       // ArrayList<T> pojoList = new ArrayList<>();
        //        for (T item :pojoArrayList){
        //            pojoList.add(item);
        //        }
        pojoArrayList.add(intializePojo2(pojoClassName, arrayValues));
        return pojoArrayList;
    }

    public static <T extends UtilPojoInterface> T intializePojo3(EnumPojo pojoClassName, String[] arrayValues) {

        LinkedHashMap hashMap;
        UtilPojoInterface aggregateData = null;

        if (pojoClassName.equals(EnumPojo.StoreAggregateData)) {
            aggregateData = new StoreAggregateData();

        } else if (pojoClassName.equals(EnumPojo.ShopAggregateData)) {
            //        AggregateData = new ShopAggregateData((String) mapAttributes.get("shopName"),(String) mapAttributes.get("location"),(String)mapAttributes.get("phone"));
            aggregateData = new ShopAggregateData();

        } else if (pojoClassName.equals(EnumPojo.RepairAggregateData)) {
            //        AggregateData = new ShopAggregateData((String) mapAttributes.get("shopName"),(String) mapAttributes.get("location"),(String)mapAttributes.get("phone"));
            aggregateData = new RepairAggregateData();

        } else if (pojoClassName.equals(EnumPojo.AddOrderAggregateData)) {
            //        AggregateData = new ShopAggregateData((String) mapAttributes.get("shopName"),(String) mapAttributes.get("location"),(String)mapAttributes.get("phone"));
            aggregateData = new AddOrderAggregateData();

        } else {
            return null;
        }
        hashMap = aggregateData.getAttributeMap();
        List<String> keys = new ArrayList(hashMap.keySet());
        for (int i = 0; i < keys.size(); i++) {
            hashMap.put(keys.get(i), arrayValues[i]);
            aggregateData.setAttributeMap(hashMap);
        }

        return (T) aggregateData;
    }
*/
}