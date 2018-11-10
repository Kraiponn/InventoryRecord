package com.ksntechnology.inventoryrecord.manager;

import android.content.Context;

public class Contextor {
    private static Contextor sContextor;
    private Context mContext;

    public static Contextor getInstance() {
        if (sContextor == null) {
            sContextor = new Contextor();
        }

        return sContextor;
    }

    private Contextor() {
        //
    }

    public void init(Context context) {
        mContext = context;
    }

    public Context getContext() {
        return mContext;
    }

}
