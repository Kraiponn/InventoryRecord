package com.ksntechnology.inventoryrecord.dialog;

import android.content.Context;
import android.graphics.Color;
import android.widget.Toast;

import com.ksntechnology.inventoryrecord.R;
import com.muddzdev.styleabletoast.StyleableToast;

public class MyCustomToast {

    public static void toastSuccessMessage(Context context, String text) {
        StyleableToast.makeText(context, text,
                Toast.LENGTH_LONG,
                R.style.MyToastSuccessStyle).show();
    }

    public static void toastErrorMessage(Context context, String text) {
        StyleableToast.makeText(context, text,
                Toast.LENGTH_LONG,
                R.style.MyToastErrorStyle).show();
    }

    public static void toastPatternSuccessMessage(Context context) {
        new StyleableToast
                .Builder(context)
                .text("Hello world!")
                .textColor(Color.WHITE)
                .backgroundColor(Color.GREEN)
                .show();
    }

    public static void toastPatternErrorMessage(Context context) {
        new StyleableToast
                .Builder(context)
                .text("Hello world!")
                .textColor(Color.WHITE)
                .backgroundColor(Color.RED)
                .show();
    }

}
