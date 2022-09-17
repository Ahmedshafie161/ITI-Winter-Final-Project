package com.example.itiproject.AddOrder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.itiproject.Enums.EnumPojo;
import com.example.itiproject.Enums.EnumRecyclerView;
import com.example.itiproject.R;
import com.example.itiproject.Util.Pojo.UtilPojo;
import com.example.itiproject.Util.UtilRecyclerShow;

import java.util.ArrayList;

public class AddOrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order);

        String[] stringData = {"ahmed", "mohamed","5","2-3-2002"};
        ArrayList arrayDataList = new ArrayList();
        arrayDataList =UtilPojo.intializePojoList(EnumPojo.AddOrderAggregateData,stringData,arrayDataList);
        RecyclerView recyclerView = findViewById(R.id.addOrder_recyclerView);
        recyclerView = UtilRecyclerShow.showRecyclerView(arrayDataList,this, EnumRecyclerView.AddOrderMyRecyclerAdapter,recyclerView);


    }
}