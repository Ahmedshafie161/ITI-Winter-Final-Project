package com.example.itiproject.Util;

import android.widget.EditText;

import com.example.itiproject.AddOrder.AddOrderAggregateData;
import com.example.itiproject.Sell.SellActivity;
import com.example.itiproject.Store.StoreAggregateData;
import com.example.itiproject.Util.Pojo.UtilPojoInterface;

import java.util.ArrayList;

public class UtilText {
    public UtilText() {
    }


    public static void clearText(EditText... editTexts) {

        for (EditText editText : editTexts) {
            editText.setText("");

        }

    }

}