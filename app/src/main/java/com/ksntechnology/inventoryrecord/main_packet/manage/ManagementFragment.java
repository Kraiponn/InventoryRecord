package com.ksntechnology.inventoryrecord.main_packet.manage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ksntechnology.inventoryrecord.R;

public class ManagementFragment extends Fragment implements View.OnClickListener {
    private LinearLayout btnDisplayProduct;
    private LinearLayout btnManageProduct;
    private LinearLayout btnAddProduct;
    private LinearLayout btnAddCustomer;
    private LinearLayout btnManageUser;
    private LinearLayout btnAddModel;

    public interface onSelectItemProcess{
        void onSelectedItemProcess(int processName);
    }

    public static ManagementFragment newInstance() {
        ManagementFragment fm = new ManagementFragment();
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
                R.layout.fragment_management,
                container, false
        );

        initInstance(view);
        return view;
    }

    private void initInstance(View view) {
        btnDisplayProduct = view.findViewById(R.id.btnManage_displayProducts);
        btnManageProduct = view.findViewById(R.id.btnManage_manageProduct);
        btnAddProduct = view.findViewById(R.id.btnManage_addProduct);
        btnAddCustomer = view.findViewById(R.id.btnManage_manageCustomer);
        btnManageUser = view.findViewById(R.id.btnManage_manageUser);
        btnAddModel = view.findViewById(R.id.btnManage_addModel);

        btnDisplayProduct.setOnClickListener(this);
        btnManageProduct.setOnClickListener(this);
        btnAddProduct.setOnClickListener(this);
        btnAddCustomer.setOnClickListener(this);
        btnManageUser.setOnClickListener(this);
        btnAddModel.setOnClickListener(this);
    }


    private void callDestinationProcess(int processName) {
        onSelectItemProcess callBack = (onSelectItemProcess) getActivity();
        callBack.onSelectedItemProcess(processName);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnManage_displayProducts:
                callDestinationProcess(ProcessDestinationType.DISPLAY_PRODUCTS_TYPE);
                break;
            case R.id.btnManage_manageProduct:
                callDestinationProcess(ProcessDestinationType.MANAGE_PRODUCT_TYPE);
                break;
            case R.id.btnManage_addProduct:
                callDestinationProcess(ProcessDestinationType.ADD_PRODUCT_TYPE);
                break;
            case R.id.btnManage_manageCustomer:
                callDestinationProcess(ProcessDestinationType.MANAGE_CUSTOMER_TYPE);
                break;
            case R.id.btnManage_addModel:
                callDestinationProcess(ProcessDestinationType.ADD_MODEL_TYPE);
                break;
            case R.id.btnManage_manageUser:
                callDestinationProcess(ProcessDestinationType.MANAGE_USER_INTERFACE_TYPE);
                break;
        }
    }


}
