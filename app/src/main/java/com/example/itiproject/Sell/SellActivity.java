package com.example.itiproject.Sell;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.itiproject.R;
import com.example.itiproject.Store.StoreAggregateData;
import com.example.itiproject.Enums.EnumPojo;
import com.example.itiproject.Enums.EnumRecyclerView;
import com.example.itiproject.Util.Pojo.UtilPojo;
import com.example.itiproject.Util.Pojo.UtilPojoInterface;
import com.example.itiproject.Util.UtilGmail;
import com.example.itiproject.Util.UtilRecyclerShow;
import com.example.itiproject.Util.UtilText;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class SellActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList <StoreAggregateData> arrayListPojo = new ArrayList<>();

    boolean intialized =false ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);
        recyclerView = findViewById(R.id.sell_recyclerView);

        String [] arrayValue1 = {"hassona","ahmed","10.5","010","06-07-2009"};


        arrayListPojo= UtilPojo.intializePojoList(EnumPojo.StoreAggregateData,arrayValue1,arrayListPojo,StoreAggregateData.class);
            recyclerView= UtilRecyclerShow.showRecyclerView(arrayListPojo,this, EnumRecyclerView.SellMyRecyclerAdapter,recyclerView);
            showFloatButton();
    }

    public void addListToRecycler(String name , int price , int quantity,String date,String shopName){
        String [] arrayValues ={shopName,name,String.valueOf(price),String.valueOf(quantity),String.valueOf(date)};
        // if data not sent in onCreate
        if (!intialized){
            arrayListPojo = UtilPojo.intializePojoList(EnumPojo.StoreAggregateData,arrayValues, arrayListPojo,StoreAggregateData.class);
            recyclerView= UtilRecyclerShow.showRecyclerView(arrayListPojo,this,EnumRecyclerView.SellMyRecyclerAdapter,recyclerView);
            intialized =true ;
        }else {
            StoreAggregateData storeAggregateData =  UtilPojo.intializePojo(EnumPojo.StoreAggregateData,arrayValues,StoreAggregateData.class);
            SellMyRecyclerAdapter sellMyRecyclerAdapter =(SellMyRecyclerAdapter)recyclerView.getAdapter();
            sellMyRecyclerAdapter.addItem(storeAggregateData);
        }
        }


    void showFloatButton(){
        FloatingActionButton fab = findViewById(R.id.sell_floatButton);

        EditText editTextShopName = findViewById(R.id.sell_etShopName);
        EditText editTextProductName = findViewById(R.id.sell_etProductName);
        EditText editTextPrice = findViewById(R.id.sell_etPrice);
        EditText editTextQuantity = findViewById(R.id.sell_etQuantity);
        EditText editTextDate = findViewById(R.id.sell_etDate);

        fab.setOnClickListener(view -> {
            String shopName =editTextShopName.getText().toString();
            String productName =editTextProductName.getText().toString();
            String _quantity = editTextQuantity.getText().toString();
            String _price = editTextPrice.getText().toString();
            String dateString = editTextDate.getText().toString();
            if(!productName.isEmpty()&&!_quantity.isEmpty()&&!_price.isEmpty()&&!dateString.isEmpty()&&!shopName.isEmpty()){
                int quantity = Integer.parseInt(_quantity);
                int price = Integer.parseInt(_price);
                addListToRecycler(productName,price,quantity,dateString,shopName);
                UtilText.clearText(editTextDate,editTextPrice,editTextProductName,editTextQuantity,editTextShopName);
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true ;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.action_settings){
            //intentGmail(arrayList);
            UtilGmail.intentGmail(this,  arrayListPojo);
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
}