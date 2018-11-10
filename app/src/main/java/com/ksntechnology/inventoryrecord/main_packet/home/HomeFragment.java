package com.ksntechnology.inventoryrecord.main_packet.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ksntechnology.inventoryrecord.R;

public class HomeFragment extends Fragment {

    public static HomeFragment newInstance() {
        HomeFragment fm = new HomeFragment();
        Bundle args = new Bundle();
        fm.setArguments(args);
        return fm;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(
                R.layout.fragment_home,
                container, false
        );

        initInstance(view);
        return view;
    }

    private void initInstance(View view) {
        //
    }


}
