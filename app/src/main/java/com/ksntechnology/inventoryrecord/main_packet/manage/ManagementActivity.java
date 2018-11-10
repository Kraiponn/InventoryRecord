package com.ksntechnology.inventoryrecord.main_packet.manage;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.ksntechnology.inventoryrecord.R;
import com.ksntechnology.inventoryrecord.main_packet.global.ScreenOrientation;
import com.ksntechnology.inventoryrecord.main_packet.manage.model.AddModelFragment;
import com.ksntechnology.inventoryrecord.main_packet.manage.userlist.AddUserFragment;

public class ManagementActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView txtActionBarTopic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management);

        initInstance();
        if (savedInstanceState == null) {
            int processName = getProcessName();
            setNewFragment(processName);
        }
    }

    private void setNewFragment(int processName) {
        Fragment fragment = null;

        if (processName == ProcessDestinationType.DISPLAY_PRODUCTS_TYPE) {
            txtActionBarTopic.setText(getResources().getString(R.string.text_topic_display_product));
            fragment = AddUserFragment.newInstance();
        }else if (processName == ProcessDestinationType.ADD_MODEL_TYPE) {
            txtActionBarTopic.setText(getResources().getString(R.string.text_topic_add_model));
            fragment = AddModelFragment.newInstance();
        }else if (processName == ProcessDestinationType.ADD_PRODUCT_TYPE) {
            txtActionBarTopic.setText(getResources().getString(R.string.text_topic_add_product));
            fragment = AddUserFragment.newInstance();
        }else if (processName == ProcessDestinationType.MANAGE_CUSTOMER_TYPE) {
            txtActionBarTopic.setText(getResources().getString(R.string.text_topic_manage_customer));
            fragment = AddUserFragment.newInstance();
        }else if (processName == ProcessDestinationType.MANAGE_PRODUCT_TYPE) {
            txtActionBarTopic.setText(getResources().getString(R.string.text_topic_manage_product));
            fragment = AddUserFragment.newInstance();
        }else if (processName == ProcessDestinationType.MANAGE_USER_INTERFACE_TYPE) {
            txtActionBarTopic.setText(getResources().getString(R.string.text_topic_manage_user));
            fragment = AddUserFragment.newInstance();
        }

        replaceFragment(fragment);
    }


    private int getProcessName() {
        Intent intent = getIntent();
        return intent.getIntExtra("PROCESS_NAME", 0);
    }

    private void initInstance() {
        ScreenOrientation.setScreenPortrait(this);
        toolbar = findViewById(R.id.toolBar);
        txtActionBarTopic = toolbar.findViewById(R.id.txtActionBarTopic);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void replaceFragment(Fragment fm) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(
                        R.id.mainManagementContentContainer,
                        fm
                )
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            overridePendingTransition(
                    R.anim.anim_move_from_top,
                    R.anim.anim_move_to_bottom);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(
                R.anim.anim_move_from_top,
                R.anim.anim_move_to_bottom);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
