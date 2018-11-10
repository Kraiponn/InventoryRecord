package com.ksntechnology.inventoryrecord.main_packet.global;

import android.content.Context;
import android.view.Gravity;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

import io.github.douglasjunior.androidSimpleTooltip.SimpleTooltip;

public class ViewNotify {

    public static void setEdittextNotify(Context context, String text, EditText editText) {
        new SimpleTooltip.Builder(context)
                .anchorView(editText)
                .text(text)
                .gravity(Gravity.BOTTOM)
                .animated(true)
                .transparentOverlay(false)
                .build()
                .show();
    }

    public static void setRadioButtonNotify(Context context, String text, RadioButton button) {
        new SimpleTooltip.Builder(context)
                .anchorView(button)
                .text(text)
                .gravity(Gravity.BOTTOM)
                .animated(true)
                .transparentOverlay(false)
                .build()
                .show();
    }

    public static void setCheckBoxNotify(Context context, String text, CheckBox checkBox) {
        new SimpleTooltip.Builder(context)
                .anchorView(checkBox)
                .text(text)
                .gravity(Gravity.BOTTOM)
                .animated(true)
                .transparentOverlay(false)
                .build()
                .show();
    }

    public static void setImageViewNotify(Context context, String text, ImageView imageView) {
        new SimpleTooltip.Builder(context)
                .anchorView(imageView)
                .text(text)
                .gravity(Gravity.BOTTOM)
                .animated(true)
                .transparentOverlay(false)
                .build()
                .show();
    }

}
