package com.example.itiproject.AddOrder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.itiproject.Enums.EnumPojo;
import com.example.itiproject.Enums.EnumRecyclerView;
import com.example.itiproject.R;
import com.example.itiproject.Util.Pojo.UtilPojo;
import com.example.itiproject.Util.Pojo.UtilPojoInterface;
import com.example.itiproject.Util.UtilRecyclerShow;
import com.example.itiproject.Util.UtilText;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AddOrderActivity extends AppCompatActivity {

    FloatingActionButton floatingActionButton ;
    EditText etShopName ;
    EditText etProductName ;
    EditText etQuantity ;
    EditText etDate;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order);

        floatingActionButton = findViewById(R.id.addOrder_fab);
         etShopName = findViewById(R.id.addOrder_et_ShopName);
         etProductName = findViewById(R.id.addOrder_et_proNam);
         etQuantity = findViewById(R.id.addOrder_et_quantity);
         etDate = findViewById(R.id.addOrder_et_date);

        String[] stringData = {"ahmed", "mohamed","5","2-3-2002"};
        ArrayList <UtilPojoInterface> arrayDataList = new ArrayList();
        arrayDataList =UtilPojo.intializePojoList(EnumPojo.AddOrderAggregateData,stringData,arrayDataList, UtilPojoInterface.class);
        recyclerView = findViewById(R.id.addOrder_recyclerView);
        recyclerView = UtilRecyclerShow.showRecyclerView(arrayDataList,this, EnumRecyclerView.AddOrderMyRecyclerAdapter,recyclerView);



    }
    public void addData(View view){
        String shopName = etShopName.getText().toString();
        String productName = etProductName.getText().toString();
        String quantity = etQuantity.getText().toString();
        String date = etDate.getText().toString();
        String []arrData = {productName,shopName,quantity,date};
        AddOrderAggregateData addOrderAggregateData= UtilPojo.intializePojo(EnumPojo.AddOrderAggregateData,arrData,AddOrderAggregateData.class);
        AddOrderMyRecyclerAdapter addOrderMyRecyclerAdapter = (AddOrderMyRecyclerAdapter) recyclerView.getAdapter();
        addOrderMyRecyclerAdapter.addItem(addOrderAggregateData);
        UtilText.clearText(etProductName,etShopName,etQuantity,etDate);
    }
}