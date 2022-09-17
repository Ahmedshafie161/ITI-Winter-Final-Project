package com.example.itiproject.Shop;

import com.example.itiproject.Util.UtilDate;
import com.example.itiproject.Util.Pojo.UtilPojoInterface;

import java.util.Date;
import java.util.LinkedHashMap;

public class ShopAggregateData implements UtilPojoInterface {

    public  LinkedHashMap<String,String > attributeMap = new LinkedHashMap<>();

    public  LinkedHashMap getAttributeMap() {

         if (attributeMap.size()==0){
             attributeMap.put("shopName","");
             attributeMap.put("location","");
             attributeMap.put("phone","");
         }

         return  attributeMap;
     }
    public  void setAttributeMap(LinkedHashMap attributeMap) {

         this.attributeMap = attributeMap;
        }
         /* attributeMap.put("shopName","");
            attributeMap.put("location","");
            attributeMap.put("phone","");*/

     public ShopAggregateData() {
         attributeMap.put("shopName","");
         attributeMap.put("longitude","");
         attributeMap.put("latitude","");
         attributeMap.put("phone","");
         attributeMap.put("lastVisit","");


    }
    public ShopAggregateData(Object ...objects){
         for(Object obj:objects){

         }
    }

    public void setLastVisit(String lastVisit) {
        attributeMap.put("lastVisit",lastVisit);

    }

    public String getShopName() {

         return String.valueOf(getAttributeMap().get("shopName"));
    }

    public String getLocation() {

        return String.valueOf(getAttributeMap().get("latitude"))+String.valueOf(getAttributeMap().get("longitude"));
    }

    public String getPhone() {

        return String.valueOf(getAttributeMap().get("phone"));
    }

    public Date getLastVisit() {

        return UtilDate.makeDate((String) getAttributeMap().get("lastVisit"));
    }
}
