package com.example.itiproject.Util;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itiproject.AddOrder.AddOrderMyRecyclerAdapter;
import com.example.itiproject.Enums.EnumRecyclerView;
import com.example.itiproject.Repair.RepairMyRecyclerAdapter;
import com.example.itiproject.Sell.SellMyRecyclerAdapter;
import com.example.itiproject.Shop.ShopAggregateData;
import com.example.itiproject.Shop.ShopMyRecyclerAdapter;
import com.example.itiproject.Store.StoreAggregateData;
import com.example.itiproject.Store.StoreMyRecyclerAdapter;

import java.util.ArrayList;

public class UtilRecyclerShow {

    public static RecyclerView showRecyclerView(ArrayList arrayList , AppCompatActivity appCompatActivity, EnumRecyclerView adapterClass, RecyclerView recyclerView){
        RecyclerView.Adapter adapter;

        if (arrayList !=null) {
            RecyclerView rW = recyclerView;
            if (adapterClass.equals(EnumRecyclerView.ShopMyRecyclerAdapter)){
                adapter = new ShopMyRecyclerAdapter(arrayList );
            }else if (adapterClass.equals(EnumRecyclerView.StoreMyRecyclerAdapter)){
                adapter = new StoreMyRecyclerAdapter(arrayList);
            }else if (adapterClass.equals(EnumRecyclerView.SellMyRecyclerAdapter)){
                adapter = new SellMyRecyclerAdapter(arrayList);
            }else if (adapterClass.equals(EnumRecyclerView.RepairMyReceyclerAdapter)){
                adapter = new RepairMyRecyclerAdapter(arrayList);
            }else if (adapterClass.equals(EnumRecyclerView.AddOrderMyRecyclerAdapter)){
                adapter = new AddOrderMyRecyclerAdapter(arrayList);
            }
            else{return null ;}

            rW.setLayoutManager(new LinearLayoutManager(appCompatActivity));
            rW.setAdapter(adapter);
            adapter =null ;
            return rW ;
        }

/*
        if (arrayList.size()!=0) {
            RecyclerView rW = recyclerView;
            if (adapterClass.equals(EnumRecyclerView.ShopMyRecyclerAdapter)&&arrayList.get(0) instanceof ShopAggregateData){
                adapter = new ShopMyRecyclerAdapter(arrayList );
            }else if (adapterClass.equals(EnumRecyclerView.StoreMyRecyclerAdapter)&&arrayList.get(0) instanceof StoreAggregateData){
                adapter = new StoreMyRecyclerAdapter(arrayList);
            }else if (adapterClass.equals(EnumRecyclerView.SellMyRecyclerAdapter)&&arrayList.get(0) instanceof StoreAggregateData){
                adapter = new SellMyRecyclerAdapter(arrayList);
            }else if (adapterClass.equals(EnumRecyclerView.RepairMyReceyclerAdapter)){
                adapter = new RepairMyRecyclerAdapter(arrayList);
            }else if (adapterClass.equals(EnumRecyclerView.AddOrderMyRecyclerAdapter)){
                adapter = new AddOrderMyRecyclerAdapter(arrayList);
            }
            else{return null ;}

            rW.setLayoutManager(new LinearLayoutManager(appCompatActivity));
            rW.setAdapter(adapter);
            adapter =null ;
            return rW ;
        }
*/

        return null;
    }

    private  UtilRecyclerShow(){}
}
