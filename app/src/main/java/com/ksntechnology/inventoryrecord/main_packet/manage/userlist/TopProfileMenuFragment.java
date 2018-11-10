package com.ksntechnology.inventoryrecord.main_packet.manage.userlist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ksntechnology.inventoryrecord.R;

public class TopProfileMenuFragment extends Fragment {
    private LinearLayout btnExitMenu;

    public static TopProfileMenuFragment newInstance() {
        TopProfileMenuFragment fm = new TopProfileMenuFragment();
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
                R.layout.fragment_top_profile_menu,
                container, false
        );

        initInstance(view);
        return view;
    }

    private void initInstance(View view) {
        btnExitMenu = view.findViewById(R.id.layoutTopMenu_exit);

        btnExitMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
    }


}
