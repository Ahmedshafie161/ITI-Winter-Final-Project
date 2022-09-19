package com.example.itiproject.AddOrder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.itiproject.Enums.EnumPojo;
import com.example.itiproject.Enums.EnumRecyclerView;
import com.example.itiproject.R;
import com.example.itiproject.Repair.RepairActivity;
import com.example.itiproject.Repair.RepairAggregateData;
import com.example.itiproject.RoomDatabase.AppDatabase;
import com.example.itiproject.Util.Pojo.UtilPojo;
import com.example.itiproject.Util.UtilRecyclerShow;
import com.example.itiproject.Util.UtilText;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class AddOrderActivity extends AppCompatActivity {

    //Room database refrences
    private AppDatabase appDatabase;
    private RepairAggregateData repairAggregateData;


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
        //room intialization
        appDatabase = AppDatabase.getInstance(AddOrderActivity.this);

        floatingActionButton = findViewById(R.id.addOrder_fab);
         etShopName = findViewById(R.id.addOrder_et_ShopName);
         etProductName = findViewById(R.id.addOrder_et_proNam);
         etQuantity = findViewById(R.id.addOrder_et_quantity);
         etDate = findViewById(R.id.addOrder_et_date);

        String[] stringData = {"ahmed", "mohamed","5","2-3-2002"};
        ArrayList <AddOrderAggregateData> arrayDataList = new ArrayList<>();
        arrayDataList =UtilPojo.intializePojoList(EnumPojo.AddOrderAggregateData,stringData,arrayDataList, AddOrderAggregateData.class);
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
        assert addOrderMyRecyclerAdapter != null;
        addOrderMyRecyclerAdapter.addItem(addOrderAggregateData);
        UtilText.clearText(etProductName,etShopName,etQuantity,etDate);
    }

    public  void save(View view){
        AddOrderAggregateData addOrderAggregateData = new AddOrderAggregateData() ;
        addOrderAggregateData.attributeMap.put(AddOrderAggregateData.Quantity,3);
        addOrderAggregateData.attributeMap.put(AddOrderAggregateData.PRODUCT_NAME,"ahmed");
        addOrderAggregateData.attributeMap.put(AddOrderAggregateData.SHOP_NAME,"aloka");
        new InsertTask(this,addOrderAggregateData).execute();
    }
    private static class InsertTask extends AsyncTask<Void,Void,Boolean> {

        private WeakReference<AddOrderActivity> activityWeakReference;
        private AddOrderAggregateData addOrderAggregateData;

        InsertTask(AddOrderActivity repairActivity ,AddOrderAggregateData addOrderAggregateData){
            activityWeakReference=new WeakReference<>(repairActivity);
            this.addOrderAggregateData=addOrderAggregateData;

        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            long j = activityWeakReference.get().appDatabase.getPojoDao().insert(addOrderAggregateData);
            addOrderAggregateData.id = j ;
            Log.e("Room_addOrder ", "doInBackground: " + j);
            return true;
        }

        @Override
        protected void onPostExecute(Boolean o) {
            if (o){
                Toast.makeText(activityWeakReference.get(),"succ addOrder"+addOrderAggregateData.id,Toast.LENGTH_LONG).show();

            }
        }
    }

}
