package com.example.itiproject.Repair;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.itiproject.Enums.EnumPojo;
import com.example.itiproject.Enums.EnumRecyclerView;
import com.example.itiproject.R;
import com.example.itiproject.Util.Pojo.UtilPojo;
import com.example.itiproject.Util.UtilRecyclerShow;

import java.util.ArrayList;

public class RepairActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    RecyclerView recyclerView;
    RadioButton radioButton;
    EditText productName;
    EditText shopName;
    EditText description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair);

         radioGroup =  findViewById(R.id.repair_radioGroup);
         productName = findViewById(R.id.repair_et_productName);
         shopName = findViewById(R.id.repair_et_shopName);
         description = findViewById(R.id.repair_et_description);

        String []arrrayData = {"ahmed","ali" ,"prblem" , "dededodod","true"};
        ArrayList <RepairAggregateData>arrayListData = new ArrayList( );
       try {
           arrayListData = UtilPojo.intializePojoList(EnumPojo.RepairAggregateData, arrrayData, arrayListData,RepairAggregateData.class);
            recyclerView = findViewById(R.id.RepairRecyclerView);
           recyclerView = UtilRecyclerShow.showRecyclerView(arrayListData, this, EnumRecyclerView.RepairMyReceyclerAdapter, recyclerView);
       }catch ( Exception e){
           e.printStackTrace();
       }
    }

    public void addProblem (View view){
        radioButton = findViewById(radioGroup.getCheckedRadioButtonId());

        String problemType = radioButton.getText().toString();
        String shopNameText= shopName.getText().toString();
        String productNameText= productName.getText().toString();
        String descriptionText= description.getText().toString();
        if (!shopNameText.isEmpty()&&!productNameText.isEmpty()&&!problemType.isEmpty()){
            RepairMyRecyclerAdapter repairMyRecyclerAdapter = (RepairMyRecyclerAdapter) recyclerView.getAdapter();
            String[] arrayData = {productNameText,shopNameText,problemType,descriptionText,"false"};
            RepairAggregateData repairAggregateData =  UtilPojo.intializePojo(EnumPojo.RepairAggregateData,arrayData,RepairAggregateData.class);
            repairMyRecyclerAdapter.addItem(repairAggregateData);
            clearText(productName,shopName,description);
        }

    }
    void clearText(EditText ...editTexts){
        for(EditText editText:editTexts){
            editText.setText("");

        }

    }

}