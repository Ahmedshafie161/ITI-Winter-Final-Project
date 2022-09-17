package com.example.itiproject.AddOrder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itiproject.R;


import java.util.ArrayList;
import java.util.LinkedHashMap;

public class AddOrderMyRecyclerAdapter extends RecyclerView.Adapter<AddOrderMyRecyclerAdapter.MyViewHolder> {
    ArrayList<AddOrderAggregateData> arrayAggrList ;
    Context context;

    // intilizing recycler with arraylist data , context and listener to send data back to activity
    public AddOrderMyRecyclerAdapter(ArrayList<AddOrderAggregateData> arrayAggrList ) {
        this.arrayAggrList = arrayAggrList;
        this.context = context;

    }
    public AddOrderMyRecyclerAdapter() {
    }

    @NonNull
    @Override
    // inflate xml file ,create viewholder ,associate xml with view holder , send view holder to onBindVIewHolder
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate view xml file
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.addorder_row_layout,parent,false);
        //associate xml with view holder , inflate views
        MyViewHolder mvh = new MyViewHolder(v);
        return mvh;
    }


    @Override
    // get the required data and add it to viewholder
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        //MyAggregateData myAggregateData = arrayAggr.get(position);
        //assign data to view associated with holder, bind data
        AddOrderAggregateData arrayAggr= arrayAggrList.get(position);
        holder.bind(arrayAggr);


    }
    public void setList(ArrayList<AddOrderAggregateData> repairAggregateData){
        this.arrayAggrList = repairAggregateData;
        notifyDataSetChanged();
    }

    public void addItem(AddOrderAggregateData mA){
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

        LinkedHashMap attributeMap ;
        public TextView tvProdName;
        public TextView tvShopName;
        public TextView tvDate;
        public TextView tvQuantity;
        AddOrderAggregateData arrayAggr;   // required to send data to activity,used in listener
        //inflate views , register view listener, send data to activity
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvProdName = itemView.findViewById(R.id.addorder_tvProductName);
            this.tvShopName = itemView.findViewById(R.id.addorder_tvShopName);
            this.tvDate = itemView.findViewById(R.id.addorder_tvDate);
            this.tvQuantity = itemView.findViewById(R.id.addorder_tvQuantity);

        }

        // bind data to views
        void bind(AddOrderAggregateData arrayAggr ){
            attributeMap = arrayAggr.getAttributeMap() ;
            this.arrayAggr=arrayAggr;
            tvProdName.setText("Product Name "+attributeMap.get(AddOrderAggregateData.PRODUCT_NAME));
            tvShopName.setText("Shop Name : "+attributeMap.get(AddOrderAggregateData.SHOP_NAME));
            tvDate.setText("Date : "+attributeMap.get(AddOrderAggregateData.DATE));
            tvQuantity.setText("Quantity: "+attributeMap.get(AddOrderAggregateData.Quantity));
        }

    }
}
