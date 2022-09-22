package com.example.itiproject.Sell;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.itiproject.R;
import com.example.itiproject.RoomDatabase.AppDatabase;
import com.example.itiproject.Shop.ShopAggregateData;
import com.example.itiproject.Store.StoreAggregateData;
import com.example.itiproject.Enums.EnumPojo;
import com.example.itiproject.Enums.EnumRecyclerView;
import com.example.itiproject.Util.Pojo.UtilPojo;
import com.example.itiproject.Util.Pojo.UtilPojoInterface;
import com.example.itiproject.Util.UtilGmail;
import com.example.itiproject.Util.UtilRecyclerShow;
import com.example.itiproject.Util.UtilText;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class SellActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList <SellAggregateData> arrayListPojo = new ArrayList<>();
    private AppDatabase appDatabase;

    boolean intialized =false ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);
        recyclerView = findViewById(R.id.sell_recyclerView);
        appDatabase = AppDatabase.getInstance(SellActivity.this);

        new ShowTask(this).execute();

     /*   String [] arrayValue1 = {"hassona","ahmed","10.5","010","06-07-2009"};


        arrayListPojo= UtilPojo.intializePojoList(EnumPojo.StoreAggregateData,arrayValue1,arrayListPojo,StoreAggregateData.class);
            recyclerView= UtilRecyclerShow.showRecyclerView(arrayListPojo,this, EnumRecyclerView.SellMyRecyclerAdapter,recyclerView);
   */
        showFloatButton();
    }

/*    public void addListToRecycler(String name , int price , int quantity,String date,String shopName){
        String [] arrayValues ={shopName,name,String.valueOf(price),String.valueOf(quantity),String.valueOf(date)};
        // if data not sent in onCreate
        if (!intialized){
            arrayListPojo = UtilPojo.getArraylistPojoFromArray(EnumPojo.StoreAggregateData,arrayValues, arrayListPojo,StoreAggregateData.class);
            recyclerView= UtilRecyclerShow.showRecyclerView(arrayListPojo,this,EnumRecyclerView.SellMyRecyclerAdapter,recyclerView);
            intialized =true ;
        }else {
            StoreAggregateData storeAggregateData =  UtilPojo.getPojoFromArray(EnumPojo.StoreAggregateData,arrayValues,StoreAggregateData.class);
            SellMyRecyclerAdapter sellMyRecyclerAdapter =(SellMyRecyclerAdapter)recyclerView.getAdapter();
            sellMyRecyclerAdapter.addItem(storeAggregateData);
        }
        }*/


    void showFloatButton(){
        FloatingActionButton fab = findViewById(R.id.sell_floatButton);

        EditText editTextShopName = findViewById(R.id.sell_etShopName);
        EditText editTextProductName = findViewById(R.id.sell_etProductName);
        EditText editTextQuantity = findViewById(R.id.sell_etQuantity);
        EditText editTextDate = findViewById(R.id.sell_etDate);

        fab.setOnClickListener(view -> {
            String shopName =editTextShopName.getText().toString();
            String productName =editTextProductName.getText().toString();
            String _quantity = editTextQuantity.getText().toString();
            String dateString = editTextDate.getText().toString();
            if(!productName.isEmpty()&&!_quantity.isEmpty()&&!dateString.isEmpty()&&!shopName.isEmpty()){
                double quantity = Double.parseDouble(_quantity);
                //addListToRecycler(productName,price,quantity,dateString,shopName);
                new InsertTask(SellActivity.this,shopName,productName,quantity,dateString).execute();
                UtilText.clearText(editTextDate,editTextProductName,editTextQuantity,editTextShopName);
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

    private static class InsertTask extends AsyncTask<Void,Void,Boolean> {

        private WeakReference<SellActivity> activityWeakReference;
        private SellAggregateData sellAggregateData;
        String shopName;
        String storeName;
        StoreAggregateData storeAggregateData;
        ShopAggregateData shopAggregateData;
        String date ;
        double quantity ;
        InsertTask(SellActivity sellActivity ,String shopName,String storeName, double quantity,String date){
            activityWeakReference=new WeakReference<>(sellActivity);
            this.shopName = shopName;
            this.storeName = storeName;
            this.quantity = quantity;
            this.date = date;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {

            // get store pojo whose name matches
            ArrayList<UtilPojoInterface> aggregateDataList =(ArrayList) activityWeakReference.get().appDatabase.getPojoDao().getAllStore();
            storeAggregateData=(StoreAggregateData) UtilPojo.getPojoFromArrayList(storeName,aggregateDataList);
            // get shop List
             aggregateDataList =(ArrayList) activityWeakReference.get().appDatabase.getPojoDao().getShopList();
            shopAggregateData=(ShopAggregateData) UtilPojo.getPojoFromArrayList(shopName,aggregateDataList);
           if (storeAggregateData!= null && shopAggregateData != null){
               sellAggregateData = new SellAggregateData(storeAggregateData,shopAggregateData,quantity,date);
               long j = activityWeakReference.get().appDatabase.getPojoDao().insert(sellAggregateData);
               sellAggregateData.id = j ;
               Log.e("Room_sell ", "doInBackground: " + j);
               storeAggregateData.attributeMap.put("quantity",storeAggregateData.getQuantity()-quantity);
               activityWeakReference.get().appDatabase.getPojoDao().update(storeAggregateData);
               return true;
           }
            Log.e("null pojo dependency","check store/sell string name/List") ;
           return false;
        }

        @Override
        protected void onPostExecute(Boolean o) {
            if (o){
                Toast.makeText(activityWeakReference.get(),"succ addOrder"+sellAggregateData.id,Toast.LENGTH_LONG).show();
               SellMyRecyclerAdapter sellMyRecyclerAdapter=(SellMyRecyclerAdapter) activityWeakReference.get().recyclerView.getAdapter();
                sellMyRecyclerAdapter.addItem(sellAggregateData);
            }
        }
    }
    private static class ShowTask extends AsyncTask <Void,Void,Boolean> {
        /*ArrayList<SellAggregateData> sellAggregateDataList;*/

        private WeakReference<SellActivity> activityWeakReference;


        ShowTask(SellActivity sellActivity){
            activityWeakReference=new WeakReference(sellActivity);
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            activityWeakReference.get().arrayListPojo = (ArrayList<SellAggregateData>) activityWeakReference.get().appDatabase.getPojoDao().getSellList();


/*
            for (RepairAggregateData repairAggregateData:repairAggregateDataList){
                for(Object value:repairAggregateData.attributeMap.values()){
                    Log.e("Romm_return_value",value.toString());

                }
                Log.e("Romm_return_map", String.valueOf(repairAggregateData.id));
            }
*/
            return true;
        }

        @Override
        protected void onPostExecute(Boolean o) {
            if (o){
                activityWeakReference.get().recyclerView= UtilRecyclerShow.showRecyclerView(activityWeakReference.get().arrayListPojo, activityWeakReference.get(), EnumRecyclerView.SellMyRecyclerAdapter,activityWeakReference.get().recyclerView);

/*
                for (RepairAggregateData repairAggregateData :activityWeakReference.get().repairAggregateDataList)
                {
                    for (Object object : repairAggregateData.attributeMap.values()){
                        Log.e("RoomRecieved", "onPostExecute: "+object.toString() );
                    }
                }
*/
                //  Toast.makeText(activityWeakReference.get(),"succ"+repairAggregateDataList.get(repairAggregateDataList.size()-1).id,Toast.LENGTH_LONG).show();

            }
        }
    }

}