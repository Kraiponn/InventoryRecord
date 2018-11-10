package com.ksntechnology.inventoryrecord.manager;

import android.content.Context;

public class SigletonTemplate {
    private static SigletonTemplate sContextor;
    private Context mContext;

    public static SigletonTemplate getInstance() {
        if (sContextor == null) {
            sContextor = new SigletonTemplate();
        }

        return sContextor;
    }

    private SigletonTemplate() {
        mContext = Contextor.getInstance().getContext();
    }

    public Context getContext() {
        return mContext;
    }

}
