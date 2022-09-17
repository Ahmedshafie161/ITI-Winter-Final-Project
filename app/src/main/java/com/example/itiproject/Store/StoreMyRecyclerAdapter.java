package com.example.itiproject.Store;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itiproject.R;

import java.util.ArrayList;

 public class StoreMyRecyclerAdapter extends RecyclerView.Adapter<StoreMyRecyclerAdapter.MyViewHolder>  {
    ArrayList<StoreAggregateData> arrayAggrList ;
    Context context;
    // intilizing recycler with arraylist data , context and listener to send data back to activity
    public StoreMyRecyclerAdapter(ArrayList<StoreAggregateData> arrayAggrList ) {
        this.arrayAggrList = arrayAggrList;
        this.context = context;

    }
    public StoreMyRecyclerAdapter() {
    }

    @NonNull
    @Override
    // inflate xml file ,create viewholder ,associate xml with view holder , send view holder to onBindVIewHolder
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate view xml file
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_row_layout,parent,false);
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
    public void setList(ArrayList<StoreAggregateData> storeAggregateData){
        this.arrayAggrList = storeAggregateData;
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

    public void resetNumbers() {
        for (StoreAggregateData storeAggregateData :arrayAggrList) {
            storeAggregateData.setQuantity(0);
        }
        notifyDataSetChanged();
    }

    // inflate views ,bind data , register listener ,
    public  class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tvName;
        public TextView tvPrice;
        public TextView tvQuantity;
        StoreAggregateData arrayAggr;   // required to send data to activity,used in listener
        //inflate views , register view listener, send data to activity
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvName = itemView.findViewById(R.id.store_row_productName);
            this.tvPrice = itemView.findViewById(R.id.store_row_price);
            this.tvQuantity = itemView.findViewById(R.id.store_row_Quantity);
            //send data from adapter to activity

        }
        // bind data to views
        void bind(StoreAggregateData arrayAggr ){
            this.arrayAggr=arrayAggr;
            tvName.setText(arrayAggr.getName());
            tvPrice.setText("price : "+String.valueOf(arrayAggr.getPrice()));
            tvQuantity.setText("Quantity  : "+String.valueOf(arrayAggr.getQuantity()));
        }

    }
}
