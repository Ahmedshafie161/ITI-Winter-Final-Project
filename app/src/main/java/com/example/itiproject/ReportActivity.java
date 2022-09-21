package com.example.itiproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.example.itiproject.Repair.RepairAggregateData;
import com.example.itiproject.RoomDatabase.AppDatabase;
import com.example.itiproject.Sell.SellAggregateData;
import com.example.itiproject.Store.StoreAggregateData;
import com.example.itiproject.Util.UtilGmail;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class ReportActivity extends AppCompatActivity {
    public ArrayList<SellAggregateData> arrayListPojo;
    public ArrayList<RepairAggregateData> arrayRepairListPojo;
    private AppDatabase appDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        appDatabase = AppDatabase.getInstance(ReportActivity.this);

    }
    public void shareProduct(View view){
        new ShowSoldPRoducPojoTask(this).execute();
    //    UtilGmail.intentGmail(this,);
    }
    public void shareOperation(View view){
        new ShowSellPojoTask(this).execute();


    }
    public void shareFaildProduct(View view){
        new ShowRoportPojoTask(this).execute();

    }
    private static class ShowSellPojoTask extends AsyncTask<Void,Void,Boolean> {
        /*ArrayList<SellAggregateData> sellAggregateDataList;*/

        private WeakReference<ReportActivity> activityWeakReference;


        ShowSellPojoTask(ReportActivity reportActivity){
            activityWeakReference=new WeakReference(reportActivity);
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
                UtilGmail.intentGmail(activityWeakReference.get(),activityWeakReference.get().arrayListPojo);
                //   activityWeakReference.get().recyclerView= UtilRecyclerShow.showRecyclerView(activityWeakReference.get().arrayListPojo, activityWeakReference.get(), EnumRecyclerView.SellMyRecyclerAdapter,activityWeakReference.get().recyclerView);

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
    private static class ShowRoportPojoTask extends AsyncTask<Void,Void,Boolean> {
        /*ArrayList<SellAggregateData> sellAggregateDataList;*/

        private WeakReference<ReportActivity> activityWeakReference;


        ShowRoportPojoTask(ReportActivity reportActivity){
            activityWeakReference=new WeakReference(reportActivity);
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            activityWeakReference.get().arrayRepairListPojo = (ArrayList<RepairAggregateData>) activityWeakReference.get().appDatabase.getPojoDao().getRepairAll();

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
                UtilGmail.intentGmail(activityWeakReference.get(),activityWeakReference.get().arrayRepairListPojo);
                //   activityWeakReference.get().recyclerView= UtilRecyclerShow.showRecyclerView(activityWeakReference.get().arrayListPojo, activityWeakReference.get(), EnumRecyclerView.SellMyRecyclerAdapter,activityWeakReference.get().recyclerView);

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
    private static class ShowSoldPRoducPojoTask extends AsyncTask<Void,Void,Boolean> {
        /*ArrayList<SellAggregateData> sellAggregateDataList;*/

        private WeakReference<ReportActivity> activityWeakReference;

        ArrayList <StoreAggregateData> soldStore = new ArrayList<>();

        ShowSoldPRoducPojoTask(ReportActivity reportActivity){
            activityWeakReference=new WeakReference(reportActivity);
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            ArrayList <SellAggregateData> sellAggregateDataArrayList= (ArrayList<SellAggregateData>) activityWeakReference.get().appDatabase.getPojoDao().getSellList();
            ArrayList <StoreAggregateData> storeAggregateDataArrayList= (ArrayList<StoreAggregateData>) activityWeakReference.get().appDatabase.getPojoDao().getAllStore();
         for(SellAggregateData sellAggregateData : sellAggregateDataArrayList){
             String storeName=sellAggregateData.getStoreName();
             for (StoreAggregateData storeAggregateData : storeAggregateDataArrayList){
                 if (storeName.equals(storeAggregateData.getName())){
                     soldStore.add(storeAggregateData);
                 }
             }
         }

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
                UtilGmail.intentGmail(activityWeakReference.get(),soldStore);
                //   activityWeakReference.get().recyclerView= UtilRecyclerShow.showRecyclerView(activityWeakReference.get().arrayListPojo, activityWeakReference.get(), EnumRecyclerView.SellMyRecyclerAdapter,activityWeakReference.get().recyclerView);

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