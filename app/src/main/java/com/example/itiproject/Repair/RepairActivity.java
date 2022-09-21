package com.example.itiproject.Repair;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.itiproject.Enums.EnumPojo;
import com.example.itiproject.Enums.EnumRecyclerView;
import com.example.itiproject.R;
import com.example.itiproject.RoomDatabase.AppDatabase;
import com.example.itiproject.Util.Pojo.UtilPojo;
import com.example.itiproject.Util.UtilRecyclerShow;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class RepairActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    RecyclerView recyclerView;
    RadioButton radioButton;
    EditText productName;
    EditText shopName;
    EditText description;

    //Room database refrences
    private AppDatabase  appDatabase;
//    private RepairAggregateData repairAggregateData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair);
        try {
            //   arrayListData = UtilPojo.intializePojoList(EnumPojo.RepairAggregateData, arrrayData, arrayListData, RepairAggregateData.class);
            recyclerView = findViewById(R.id.RepairRecyclerView);


        } catch (Exception e) {
            e.printStackTrace();
        }

        //room intialization
        appDatabase = AppDatabase.getInstance(RepairActivity.this);
        new ShowTask(this).execute();

        radioGroup = findViewById(R.id.repair_radioGroup);
        productName = findViewById(R.id.repair_et_productName);
        shopName = findViewById(R.id.repair_et_shopName);
        description = findViewById(R.id.repair_et_description);

       // String[] arrrayData = {"ahmed", "ali", "prblem", "dededodod", "true"};
       // ArrayList<RepairAggregateData> arrayListData = new ArrayList<>();
    }

    public void addProblem(View view) {
        radioButton = findViewById(radioGroup.getCheckedRadioButtonId());

        String problemType = radioButton.getText().toString();
        String shopNameText = shopName.getText().toString();
        String productNameText = productName.getText().toString();
        String descriptionText = description.getText().toString();
        if (!shopNameText.isEmpty() && !productNameText.isEmpty() && !problemType.isEmpty()) {
            RepairMyRecyclerAdapter repairMyRecyclerAdapter = (RepairMyRecyclerAdapter) recyclerView.getAdapter();
            String[] arrayData = {productNameText, shopNameText, problemType, descriptionText, "false"};
            RepairAggregateData repairAggregateData = UtilPojo.getPojoFromArray(EnumPojo.RepairAggregateData, arrayData, RepairAggregateData.class);
            assert repairMyRecyclerAdapter != null;
            repairMyRecyclerAdapter.addItem(repairAggregateData);
            clearText(productName, shopName, description);
            new InsertTask(this,repairAggregateData).execute();
        }

    }

    void clearText(EditText... editTexts) {
        for (EditText editText : editTexts) {
            editText.setText("");

        }

    }

    /*public void save (View view){
        Toast.makeText(RepairActivity.this, "button save cliked", Toast.LENGTH_SHORT).show();
        RepairAggregateData repairAggregateData= new RepairAggregateData() ;
        repairAggregateData.attributeMap.put(RepairAggregateData.PRODUCT_NAME,"ahmed");
        repairAggregateData.attributeMap.put(RepairAggregateData.SHOP_NAME,"ali");
        repairAggregateData.attributeMap.put(RepairAggregateData.DESCRIPTION,"this is me");
        repairAggregateData.attributeMap.put(RepairAggregateData.IS_SOLVED,"No");

        new InsertTask(this,repairAggregateData).execute();
        new ShowTask(this).execute();
    }*/

    private static class InsertTask extends AsyncTask <Void,Void,Boolean> {

        private WeakReference<RepairActivity> activityWeakReference;
        private RepairAggregateData repairAggregateData;

        InsertTask(RepairActivity repairActivity ,RepairAggregateData repairAggregateData){
            activityWeakReference=new WeakReference<>(repairActivity);
            this.repairAggregateData=repairAggregateData;

        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            long j = activityWeakReference.get().appDatabase.getPojoDao().insert(repairAggregateData);
            repairAggregateData.id = j ;
            Log.e("ID ", "doInBackground: " + j);
            return true;
        }

        @Override
        protected void onPostExecute(Boolean o) {
            if (o){
                Toast.makeText(activityWeakReference.get(),"succ"+repairAggregateData.id,Toast.LENGTH_LONG).show();
                //     activityWeakReference.get().setResult(repairAggregateData.id);
                //      activityWeakReference.get().finish();
            }
        }
    }
    private static class ShowTask extends AsyncTask <Void,Void,Boolean> {
        List<RepairAggregateData> repairAggregateDataList;

        private WeakReference<RepairActivity> activityWeakReference;


        ShowTask(RepairActivity repairActivity){
            activityWeakReference=new WeakReference<>(repairActivity);
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            repairAggregateDataList = activityWeakReference.get().appDatabase.getPojoDao().getRepairAll();
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
                activityWeakReference.get().recyclerView= UtilRecyclerShow.showRecyclerView( (ArrayList) repairAggregateDataList, activityWeakReference.get(), EnumRecyclerView.RepairMyReceyclerAdapter,   activityWeakReference.get().recyclerView);

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