package com.example.itiproject.Store;


import com.example.itiproject.Util.UtilDate;
import com.example.itiproject.Util.Pojo.UtilPojoInterface;

import java.util.Date;
import java.util.LinkedHashMap;

public class StoreAggregateData implements UtilPojoInterface {

    public   LinkedHashMap<String,Object > attributeMap = new LinkedHashMap<>();


    public StoreAggregateData() {
        attributeMap.put("shopName","");
        attributeMap.put("name","");
        attributeMap.put("price","");
        attributeMap.put("quantity","");
        attributeMap.put("soldDate","");
    }

    public String getName() {

        return  (String) getAttributeMap().get("name");
    }

    public void setName(String name) {

        getAttributeMap().put("name",name);
    }

    public double getPrice() {
        if (getAttributeMap().get("price").toString().trim().isEmpty())
            return 0;
        return  Double.parseDouble(getAttributeMap().get("price").toString());
    }
    public int getQuantity() {
        if(getAttributeMap().get("quantity").toString().trim().isEmpty()){
            return 0 ;
        }
        return   Integer.parseInt(getAttributeMap().get("quantity").toString());
    }
    public Date getSoldDate(){

        return UtilDate.makeDate((String) getAttributeMap().get("soldDate"));
    }
    public void setPrice(int price) {

        attributeMap.put("quantity", String.valueOf(price));
    }
    public void setQuantity(int quantity) {
        attributeMap.put("quantity", String.valueOf(quantity));
    }

    public String getShopName() {
        /*if(!shopName.isEmpty());*/
        return (String) getAttributeMap().get("shopName");

    }

    public void setShopName(String shopName) {

        getAttributeMap().put("shopName",shopName);
    }

    public void setSoldDate(String date) {
        getAttributeMap().put("soldDate",String.valueOf(date));
    }

    @Override
    public String toString() {
        return "StoreAggregateData{" +
                "shopName='" + getAttributeMap().get("shopName") + '\'' +
                ", name='" + getAttributeMap().get("name") + '\'' +
                ", price=" + getAttributeMap().get("price") +
                ", quantity=" + getAttributeMap().get("quantity") +
                ", soldDate=" + getAttributeMap().get("soldDate") +
                '}';
    }

    @Override
    public LinkedHashMap getAttributeMap() {
        if (attributeMap.size()==0){
            attributeMap.put("shopName","");
            attributeMap.put("name","");
            attributeMap.put("price","");
            attributeMap.put("quantity","");
            attributeMap.put("soldDate","");
        }

        return  attributeMap;
    }

    @Override
    public void setAttributeMap(LinkedHashMap hashMap) {
        this.attributeMap=hashMap;
    }

}
