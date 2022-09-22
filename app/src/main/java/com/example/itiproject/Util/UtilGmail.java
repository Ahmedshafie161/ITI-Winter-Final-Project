package com.example.itiproject.Util;

import android.content.Context;
import android.content.Intent;

import com.example.itiproject.Sell.SellAggregateData;
import com.example.itiproject.Util.Pojo.UtilPojoInterface;

import java.util.ArrayList;

public class UtilGmail {
    public static <T extends UtilPojoInterface> void intentGmail(Context context,ArrayList <T> arrayListPojo) {
        final Intent intent = new Intent(Intent.ACTION_SEND);

        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"test00@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "test00");
        StringBuilder body = new StringBuilder();

        for (T AggregateData :arrayListPojo) {
            body.append(AggregateData.toString());
            body.append(System.getProperty("line.separator"));
        }


        intent.putExtra(Intent.EXTRA_TEXT, body.toString());
        intent.setType("message/rfc822");
        // intent.setData(Uri.parse("testoot@gmail.com"));

        context.startActivity(intent);
    }
    /*public static <T extends UtilPojoInterface> void intentGmail2(Context context,ArrayList <T> arrayListPojo) {
        final Intent intent = new Intent(Intent.ACTION_SEND);

        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"test00@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "test00");
        StringBuilder body = new StringBuilder();

        for (T  customAggregateData :arrayListPojo) {
            body.append(customAggregateData.toString());
            body.append(System.getProperty("line.separator"));
        }
        intent.putExtra(Intent.EXTRA_TEXT, body.toString());
        intent.setType("message/rfc822");
        // intent.setData(Uri.parse("testoot@gmail.com"));

        context.startActivity(intent);
    }*/
}