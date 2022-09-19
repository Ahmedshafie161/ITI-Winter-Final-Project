package com.example.itiproject.Store;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.itiproject.R;
import com.example.itiproject.Enums.EnumPojo;
import com.example.itiproject.Enums.EnumRecyclerView;
import com.example.itiproject.RoomDatabase.AppDatabase;
import com.example.itiproject.Util.Pojo.UtilPojo;

import com.example.itiproject.Util.UtilRecyclerShow;
import com.example.itiproject.Util.UtilText;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class StoreActivity extends AppCompatActivity {
//    ArrayList <StoreAggregateData> arrayListPojo = new ArrayList<>();
    RecyclerView recyclerView ;
    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

     /*   String [] arrayValue1 = {"hassona","ahmed","10.5","010",""};

        arrayListPojo= UtilPojo.intializePojoList(EnumPojo.StoreAggregateData,arrayValue1,arrayListPojo,StoreAggregateData.class);
  */      recyclerView = findViewById(R.id.store_recyclerView);
        appDatabase = AppDatabase.getInstance(StoreActivity.this);
        new ShowTask(this).execute();

     //   recyclerView= UtilRecyclerShow.showRecyclerView(arrayListPojo,this, EnumRecyclerView.StoreMyRecyclerAdapter,recyclerView);
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
                StoreAggregateData storeAggregateData =  UtilPojo.intializePojo(EnumPojo.StoreAggregateData,valueArray,StoreAggregateData.class);
                StoreMyRecyclerAdapter myRecyclerAdapter =(StoreMyRecyclerAdapter) recyclerView.getAdapter();
                myRecyclerAdapter.addItem(storeAggregateData);
                UtilText.clearText(editTextName,editTextPrice,editTextNumber);
                new InsertTask(this,storeAggregateData).execute();
            }
        });


    }

    public void resetNumbers(View view){
        StoreMyRecyclerAdapter myRecyclerAdapter =(StoreMyRecyclerAdapter) recyclerView.getAdapter();
        ArrayList<StoreAggregateData> storeAggregateDataList= myRecyclerAdapter.resetNumbers();
        for (StoreAggregateData storeAggregateData: storeAggregateDataList)
        new UpdateTask(this,storeAggregateData).execute();
    }

    private static class InsertTask extends AsyncTask<Void,Void,Boolean> {

        private WeakReference<StoreActivity> activityWeakReference;
        private StoreAggregateData storeAggregateData;

        InsertTask(StoreActivity  storeActivity,StoreAggregateData storeAggregateData){
            activityWeakReference=new WeakReference<>(storeActivity);
            this.storeAggregateData=storeAggregateData;

        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            long j = activityWeakReference.get().appDatabase.getPojoDao().insert(storeAggregateData);
            storeAggregateData.id = j ;
            Log.e("ID ", "doInBackground: " + j);
            return true;
        }

        @Override
        protected void onPostExecute(Boolean o) {
            if (o){
                Toast.makeText(activityWeakReference.get(),"succ"+storeAggregateData.id,Toast.LENGTH_LONG).show();
                //     activityWeakReference.get().setResult(repairAggregateData.id);
                //      activityWeakReference.get().finish();
            }
        }
    }
    private static class UpdateTask extends AsyncTask<Void,Void,Boolean> {

        private WeakReference<StoreActivity> activityWeakReference;
        private StoreAggregateData storeAggregateData;

        UpdateTask(StoreActivity  storeActivity,StoreAggregateData storeAggregateData){
            activityWeakReference=new WeakReference<>(storeActivity);
            this.storeAggregateData=storeAggregateData;

        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            activityWeakReference.get().appDatabase.getPojoDao().update(storeAggregateData);
      /*      storeAggregateData.id = j ;
            Log.e("ID ", "doInBackground: " + j);*/
            return true;
        }

        @Override
        protected void onPostExecute(Boolean o) {
            if (o){
                Toast.makeText(activityWeakReference.get(),"updated"+storeAggregateData.id,Toast.LENGTH_LONG).show();
                //     activityWeakReference.get().setResult(repairAggregateData.id);
                //      activityWeakReference.get().finish();
            }
        }
    }
    private static class ShowTask extends AsyncTask <Void,Void,Boolean> {
        List<StoreAggregateData> storeAggregateDataList;

        private WeakReference<StoreActivity> activityWeakReference;


        ShowTask(StoreActivity storeActivity){
            activityWeakReference=new WeakReference(storeActivity);
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            storeAggregateDataList  = activityWeakReference.get().appDatabase.getPojoDao().getAllStore();

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
                activityWeakReference.get().recyclerView= UtilRecyclerShow.showRecyclerView((ArrayList) storeAggregateDataList,activityWeakReference.get(), EnumRecyclerView.StoreMyRecyclerAdapter,activityWeakReference.get().recyclerView);
              /*  StoreMyRecyclerAdapter storeMyRecyclerAdapter = (StoreMyRecyclerAdapter) activityWeakReference.get().recyclerView.getAdapter();

                storeMyRecyclerAdapter.setRecyclerActivityListener((name, number, latitude, longtitude) -> {
                    UtilCall.setNumber(number);
                    Toast.makeText(activityWeakReference.get(),"Shop Selected : "+name,Toast.LENGTH_SHORT).show();
                    activityWeakReference.get().selectedLongitude = longtitude ;
                    activityWeakReference.get().selectedLatitude = latitude ;
                });*/
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