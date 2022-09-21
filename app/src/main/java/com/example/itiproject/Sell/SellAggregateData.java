package com.example.itiproject.Sell;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.itiproject.Shop.ShopAggregateData;
import com.example.itiproject.Store.StoreAggregateData;
import com.example.itiproject.Util.Pojo.UtilPojoInterface;

import java.util.LinkedHashMap;
import java.util.Map;

@Entity
public class SellAggregateData implements UtilPojoInterface {

    public static final String STORE_NAME = "storeName";
    public static final String PRICE = "price";
    public static final String QUANTITY = "quantity";
    public static final String SHOP_NAME = "shopName";
    public static final String DATE = "date";
    /*  StoreAggregateData storeAggregateData;
            ShopAggregateData shopAggregateData ;*/
    @PrimaryKey (autoGenerate = true)
    public long id ;
    @ColumnInfo

    public LinkedHashMap <String, Object> attributeMap = new <StoreAggregateData, ShopAggregateData> LinkedHashMap();

  public SellAggregateData (){

  }
    public SellAggregateData(StoreAggregateData storeAggregateData, ShopAggregateData shopAggregateData,double quantity,String date){
        attributeMap.put(STORE_NAME,storeAggregateData.getName());
        attributeMap.put(PRICE,storeAggregateData.getPrice());
        attributeMap.put(QUANTITY,quantity);
        attributeMap.put(SHOP_NAME,shopAggregateData.getName());
        attributeMap.put(DATE,date);
    }
    @Override
    public LinkedHashMap <String, Object>getAttributeMap() {
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
    public String getShopName(){
        return (String) attributeMap.get(SellAggregateData.SHOP_NAME);
    }
    public String getStoreName(){
        return (String) attributeMap.get(SellAggregateData.STORE_NAME);
    }
  @Override
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
/*    public String getName() {

        return  (String) getAttributeMap().get("name");
    }

    public String getShopName() {
    }*/
}
