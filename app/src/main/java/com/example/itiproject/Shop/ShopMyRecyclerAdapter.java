package com.example.itiproject.Shop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itiproject.R;
import com.example.itiproject.Util.UtilDate;

import java.util.ArrayList;

public class ShopMyRecyclerAdapter extends RecyclerView.Adapter<ShopMyRecyclerAdapter.MyViewHolder> {
    ArrayList<ShopAggregateData> arrayAggrList ;
    Context context;

    ShopRecyclerListener listener;
    // intilizing recycler with arraylist data , context and listener to send data back to activity
    public ShopMyRecyclerAdapter(ArrayList<ShopAggregateData> arrayAggrList ) {
        this.arrayAggrList = arrayAggrList;
        this.context = context;

    }
    public ShopMyRecyclerAdapter() {
    }

    @NonNull
    @Override
    // inflate xml file ,create viewholder ,associate xml with view holder , send view holder to onBindVIewHolder
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate view xml file
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_row_layout,parent,false);
        //associate xml with view holder , inflate views
        MyViewHolder mvh = new MyViewHolder(v);
        return mvh;
    }


    @Override
    // get the required data and add it to viewholder
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        //MyAggregateData myAggregateData = arrayAggr.get(position);
        //assign data to view associated with holder, bind data
        ShopAggregateData arrayAggr= arrayAggrList.get(position);
        holder.bind(arrayAggr);


    }
    public void setList(ArrayList<ShopAggregateData> shopAggregateData){
        this.arrayAggrList = shopAggregateData;
        notifyDataSetChanged();
    }

    public void addItem(ShopAggregateData mA){
        this.arrayAggrList.add(mA);
        notifyDataSetChanged();
    }
    public void resetLastVisit(){

        arrayAggrList.get(arrayAggrList.size()-1).setLastVisit(UtilDate.todayDateString());
        notifyDataSetChanged();
    }
    // return to view the number of data
    @Override
    public int getItemCount() {

        return arrayAggrList.size();
    }


    public void setRecyclerActivityListener(ShopRecyclerListener listener){
        this.listener = listener;
    }
    // inflate views ,bind data , register listener ,
    public  class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tvName;
        public TextView tvLocation;
        public TextView tvTelephone;
        public TextView tvLastVisit;
        ShopAggregateData arrayAggr;   // required to send data to activity,used in listener
        //inflate views , register view listener, send data to activity
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvName = itemView.findViewById(R.id.shop_row_shopName);
            this.tvLocation = itemView.findViewById(R.id.shop_row_location);
            this.tvTelephone = itemView.findViewById(R.id.shop_row_telephone);
            this.tvLastVisit = itemView.findViewById(R.id.shop_row_lastVisit);
            itemView.setOnClickListener(view -> {
                //send data from adapter to activity/Util
                if(listener!=null){
                    listener.sendDataToActivity(arrayAggr.getAttributeMap().get("shopName").toString(),arrayAggr.getAttributeMap().get("phone").toString(), arrayAggr.getAttributeMap().get("latitude").toString(), arrayAggr.getAttributeMap().get("longitude").toString());
                }

            });
        }

        // bind data to views
        void bind(ShopAggregateData arrayAggr ){
            this.arrayAggr=arrayAggr;
            tvName.setText("Shop Name "+arrayAggr.getShopName());
            tvLocation.setText("location : "+arrayAggr.getLocation());
            tvTelephone.setText("phone : "+arrayAggr.getPhone());
            tvLastVisit.setText("Last Visit : "+arrayAggr.getLastVisit());
        }

    }
}
