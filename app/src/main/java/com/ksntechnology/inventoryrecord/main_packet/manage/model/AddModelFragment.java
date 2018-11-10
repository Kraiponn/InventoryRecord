package com.ksntechnology.inventoryrecord.main_packet.manage.model;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ksntechnology.inventoryrecord.R;

import java.util.ArrayList;
import java.util.List;

public class AddModelFragment extends Fragment {
    private RecyclerView rcv;
    private ModelAdapter mAdapter;


    public static AddModelFragment newInstance() {
        AddModelFragment fm = new AddModelFragment();
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
                R.layout.fragment_add_model,
                container, false
        );

        initInstance(view);
        return view;
    }

    private void initInstance(View view) {
        rcv = view.findViewById(R.id.recyclerView_modelTable);

        loadDataToRecyclerView();
    }

    private void loadDataToRecyclerView() {
        mAdapter = new ModelAdapter(getContext());
        rcv.setAdapter(mAdapter);
        rcv.setLayoutManager(new LinearLayoutManager(getContext()));

        List<ModelItemDao> dao = new ArrayList<>();
        dao.add(new ModelItemDao(1, "Mobile"));
        dao.add(new ModelItemDao(2, "Web Application"));
        dao.add(new ModelItemDao(3, "Embedded System"));
        dao.add(new ModelItemDao(4, "Graphic Design"));
        dao.add(new ModelItemDao(5, "AI"));
        dao.add(new ModelItemDao(6, "Machine Learning"));
        dao.add(new ModelItemDao(7, "Automation"));

        mAdapter.setmDao(dao);
        mAdapter.notifyDataSetChanged();
    }


}
