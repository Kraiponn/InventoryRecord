package com.ksntechnology.inventoryrecord.main_packet.other;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ksntechnology.inventoryrecord.R;

import java.util.ArrayList;
import java.util.List;

public class OtherFragment extends Fragment {
    private RecyclerView rcv;
    private OtherListItemAdapter mAdapter;
    private List<OtherListItemDao> mDao;

    public static OtherFragment newInstance() {
        OtherFragment fm = new OtherFragment();
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
                R.layout.fragment_other,
                container, false
        );

        initInstance(view);
        return view;
    }

    private void initInstance(View view) {
        rcv = view.findViewById(R.id.recyclerView_other);
    }

    @Override
    public void onStart() {
        super.onStart();
        setDataToView();
    }

    private void setDataToView() {
        mAdapter = new OtherListItemAdapter(getContext());
        rcv.setAdapter(mAdapter);
        rcv.setLayoutManager(new LinearLayoutManager(getContext()));
        /*RecyclerView.ItemDecoration decoration =
                new DividerItemDecoration(
                        getContext(),
                        DividerItemDecoration.VERTICAL
                );
        rcv.addItemDecoration(decoration);*/
        mDao = new ArrayList<>();

        mDao.add(new OtherListItemDao(
                1, R.drawable.ic_setting, "ตั้งค่า"
        ));
        mDao.add(new OtherListItemDao(
                2, R.drawable.ic_contact, "ติดต่อเรา"
        ));
        mDao.add(new OtherListItemDao(
                3, R.drawable.ic_manual, "คู่มือการใช้งาน"
        ));

        mAdapter.setDao(mDao);
        mAdapter.notifyDataSetChanged();
        mAdapter.setOnItemClickListener(new OtherListItemAdapter.OnItemClicked() {
            @Override
            public void itemClicked(OtherListItemDao dao) {
                Toast.makeText(getContext(),
                        "Data name " + dao.getTitle(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
