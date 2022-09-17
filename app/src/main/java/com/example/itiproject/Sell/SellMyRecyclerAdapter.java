package com.example.itiproject.Sell;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itiproject.R;
import com.example.itiproject.Store.StoreAggregateData;

import java.util.ArrayList;

public class SellMyRecyclerAdapter extends RecyclerView.Adapter<SellMyRecyclerAdapter.MyViewHolder> {
    ArrayList<StoreAggregateData> arrayAggrList ;
    Context context;
    // intilizing recycler with arraylist data , context and listener to send data back to activity
    public SellMyRecyclerAdapter(ArrayList<StoreAggregateData> arrayAggrList ) {
        this.arrayAggrList = arrayAggrList;
        this.context = context;

    }
    public SellMyRecyclerAdapter() {
    }

    @NonNull
    @Override
    // inflate xml file ,create viewholder ,associate xml with view holder , send view holder to onBindVIewHolder
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate view xml file
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sell_row_layout,parent,false);
        //associate xml with view holder , inflate views
        MyViewHolder mvh = new MyViewHolder(v);
        return mvh;
    }


    @Override
    // get the required data and add it to viewholder
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        //MyAggregateData myAggregateData = arrayAggr.get(position);
        //assign data to view associated with holder, bind data
        StoreAggregateData arrayAggr= arrayAggrList.get(position);
        holder.bind(arrayAggr);


    }
    public void setList(ArrayList<StoreAggregateData> sellAggregateData){
        this.arrayAggrList = sellAggregateData;
        notifyDataSetChanged();
    }

    public void addItem(StoreAggregateData mA){
        this.arrayAggrList.add(mA);
        notifyDataSetChanged();
    }
    // return to view the number of data
    @Override
    public int getItemCount() {

        return arrayAggrList.size();
    }

        // inflate views ,bind data , register listener ,
    public  class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView etProductName;
        public TextView etShopName;
        public TextView etMoney;
        public TextView etQuantity;
        public TextView etDate;
        StoreAggregateData arrayAggr;   // required to send data to activity,used in listener
        //inflate views , register view listener, send data to activity
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.etProductName = itemView.findViewById(R.id.sell_row_ProductName);
            this.etShopName = itemView.findViewById(R.id.sell_row_shopName);
            this.etMoney = itemView.findViewById(R.id.sell_row_totalMoney);
            this.etQuantity = itemView.findViewById(R.id.sell_row_Quantity);
            this.etDate = itemView.findViewById(R.id.sell_row_date);
            //send data from adapter to activity

        }
        // bind data to views
        void bind(StoreAggregateData arrayAggr ){
            this.arrayAggr=arrayAggr;
            etShopName.setText("Shop Name "+ arrayAggr.getShopName());

            etProductName.setText("Product Name "+ arrayAggr.getName());
            etMoney.setText("Total Money : "+String.valueOf((arrayAggr.getPrice()*arrayAggr.getQuantity())));
            etQuantity.setText("Quantity : "+String.valueOf(arrayAggr.getQuantity()));
            if(arrayAggr.getSoldDate()!=null){
                etDate.setText("Date : "+arrayAggr.getSoldDate().toString()) ;
            }

        }

    }
}
