package com.example.itiproject.Repair;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itiproject.R;

import com.example.itiproject.Util.UtilDate;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class RepairMyRecyclerAdapter extends RecyclerView.Adapter<RepairMyRecyclerAdapter.MyViewHolder> {
    ArrayList<RepairAggregateData> arrayAggrList ;
    RepairActivity context;

    // intilizing recycler with arraylist data , context and listener to send data back to activity
    public RepairMyRecyclerAdapter(ArrayList<RepairAggregateData> arrayAggrList,RepairActivity repairActivity ) {
        this.arrayAggrList = arrayAggrList;
        this.context = repairActivity;

    }
    public RepairMyRecyclerAdapter() {
    }

    @NonNull
    @Override
    // inflate xml file ,create viewholder ,associate xml with view holder , send view holder to onBindVIewHolder
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate view xml file
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.repair_row_layout,parent,false);
        //associate xml with view holder , inflate views
        MyViewHolder mvh = new MyViewHolder(v);
        return mvh;
    }


    @Override
    // get the required data and add it to viewholder
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        //MyAggregateData myAggregateData = arrayAggr.get(position);
        //assign data to view associated with holder, bind data
        RepairAggregateData arrayAggr= arrayAggrList.get(position);
        holder.bind(arrayAggr);


    }
    public void setList(ArrayList<RepairAggregateData> repairAggregateData){
        this.arrayAggrList = repairAggregateData;
        notifyDataSetChanged();
    }

    public void addItem(RepairAggregateData mA){
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
        public TextView tvDescription;
        public TextView tvProblemType;
        public TextView tvIsRepaired;
        RepairAggregateData arrayAggr;
        Switch mySwitch ;// required to send data to activity,used in listener
        //inflate views , register view listener, send data to activity
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvProdName = itemView.findViewById(R.id.repair_productName);
            this.tvShopName = itemView.findViewById(R.id.repair_shopName);
            this.tvDescription = itemView.findViewById(R.id.repair_Description);
            this.tvProblemType = itemView.findViewById(R.id.repair_problemType);
            this.tvIsRepaired = itemView.findViewById(R.id.repair_isRepaired);
             mySwitch = itemView.findViewById(R.id.repair_switch);

        }

        // bind data to views
        void bind(RepairAggregateData arrayAggr ){
            attributeMap = arrayAggr.getAttributeMap() ;
            this.arrayAggr=arrayAggr;
            tvProdName.setText("Product Name "+attributeMap.get(RepairAggregateData.PRODUCT_NAME));
            tvShopName.setText("Shop Name : "+attributeMap.get(RepairAggregateData.SHOP_NAME));
            tvDescription.setText("description : "+attributeMap.get(RepairAggregateData.DESCRIPTION));
            tvProblemType.setText("problemType: "+attributeMap.get(RepairAggregateData.PROBLEM_TYPE));
            tvIsRepaired.setText("is repaired : "+attributeMap.get(RepairAggregateData.IS_SOLVED));

            mySwitch.setChecked(Boolean.parseBoolean((String) arrayAggr.getAttributeMap().get(RepairAggregateData.IS_SOLVED)));
            mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                  arrayAggr.getAttributeMap().put(RepairAggregateData.IS_SOLVED,"true");
                    new RepairActivity.UpdateTask( context,arrayAggr).execute();
                }
            });
        }

    }
}
