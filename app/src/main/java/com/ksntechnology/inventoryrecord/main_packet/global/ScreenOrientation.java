package com.ksntechnology.inventoryrecord.main_packet.global;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;

import com.ksntechnology.inventoryrecord.R;

public class ScreenOrientation extends AppCompatActivity {

    public static void checkScreenSizeForSetPortrait(Activity activity) {
        if (activity.getResources().getBoolean(R.bool.portrait_only)) {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            //
        }
    }

    public static void setScreenPortrait(Activity activity) {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public static void setScreenLandScape(Activity activity) {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

}
