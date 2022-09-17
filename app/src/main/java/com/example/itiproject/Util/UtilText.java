package com.example.itiproject.Util;

import android.widget.EditText;

public class UtilText {
    public UtilText() {
    }

    public static void clearText(EditText... editTexts) {
        for (EditText editText : editTexts) {
            editText.setText("");

        }

    }
}