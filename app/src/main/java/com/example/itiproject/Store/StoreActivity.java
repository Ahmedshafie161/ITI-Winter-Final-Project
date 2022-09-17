package com.example.itiproject.Store;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.itiproject.R;
import com.example.itiproject.Enums.EnumPojo;
import com.example.itiproject.Enums.EnumRecyclerView;
import com.example.itiproject.Util.Pojo.UtilPojo;
import com.example.itiproject.Util.Pojo.UtilPojoInterface;
import com.example.itiproject.Util.UtilRecyclerShow;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

 public class StoreActivity extends AppCompatActivity {
    ArrayList <UtilPojoInterface> arrayListPojo ;
    RecyclerView recyclerView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        String [] arrayValue1 = {"hassona","ahmed","10.5","010",""};

        arrayListPojo= UtilPojo.intializePojoList(EnumPojo.StoreAggregateData,arrayValue1,arrayListPojo);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView= UtilRecyclerShow.showRecyclerView(arrayListPojo,this, EnumRecyclerView.StoreMyRecyclerAdapter,recyclerView);
        showFloatButton();

    }

    void showFloatButton(){
        FloatingActionButton fab = findViewById(R.id.fab);
        EditText editTextName = findViewById(R.id.store_etName);
        EditText editTextPrice = findViewById(R.id.store_etPrice);
        EditText editTextNumber = findViewById(R.id.store_etNumber);
        fab.setOnClickListener(view -> {
            String name =editTextName.getText().toString();
            String _quantity = editTextNumber.getText().toString();
            String _price = editTextPrice.getText().toString();
            if(!name.isEmpty()&&!_quantity.isEmpty()&&!_price.isEmpty()){
                int quantity = Integer.parseInt(_quantity);
                int price = Integer.parseInt(_price);
                String []valueArray = {"",name,String.valueOf(price),String.valueOf(quantity),""};
                StoreAggregateData storeAggregateData = (StoreAggregateData) UtilPojo.intializePojo(EnumPojo.StoreAggregateData,valueArray);
                StoreMyRecyclerAdapter myRecyclerAdapter =(StoreMyRecyclerAdapter) recyclerView.getAdapter();
                myRecyclerAdapter.addItem(storeAggregateData);
                clearText(editTextName,editTextPrice,editTextNumber);
            }
        });


    }
     void clearText(EditText ...editTexts){
         for(EditText editText:editTexts){
             editText.setText("");

         }

     }
    public void resetNumbers(View view){
        StoreMyRecyclerAdapter myRecyclerAdapter =(StoreMyRecyclerAdapter) recyclerView.getAdapter();
        myRecyclerAdapter.resetNumbers();
    }
}