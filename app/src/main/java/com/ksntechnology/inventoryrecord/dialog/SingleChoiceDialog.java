package com.ksntechnology.inventoryrecord.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

public class SingleChoiceDialog extends DialogFragment {
    private int selectIndex = -1;

    public static SingleChoiceDialog getDialog(String[] items, String title) {
        SingleChoiceDialog choiceDialog = new SingleChoiceDialog();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putStringArray("items", items);
        choiceDialog.setArguments(args);
        return choiceDialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        String title = getArguments().getString("title");
        String[] items = getArguments().getStringArray("items");

        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext())
                .setTitle(title)
                .setSingleChoiceItems(items, selectIndex, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectIndex = which;
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mCallBack.onDialogFinished(selectIndex);
                    }
                });

        return dialog.create();
    }


    public interface OnDialogFinishListener {
        void onDialogFinished(int position);
    }

    private OnDialogFinishListener mCallBack;

    public void setOnDialogItemSelectListener(OnDialogFinishListener listener) {
        mCallBack = listener;
    }



}
