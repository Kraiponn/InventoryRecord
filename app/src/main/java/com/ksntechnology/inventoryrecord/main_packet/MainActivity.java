package com.ksntechnology.inventoryrecord.main_packet;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ksntechnology.inventoryrecord.R;
import com.ksntechnology.inventoryrecord.main_packet.global.RuntimePermission;
import com.ksntechnology.inventoryrecord.main_packet.global.ScreenOrientation;
import com.ksntechnology.inventoryrecord.main_packet.home.HomeFragment;
import com.ksntechnology.inventoryrecord.main_packet.manage.ManagementActivity;
import com.ksntechnology.inventoryrecord.main_packet.manage.ManagementFragment;
import com.ksntechnology.inventoryrecord.main_packet.manage.userlist.AddUserFragment;
import com.ksntechnology.inventoryrecord.main_packet.manage.userlist.TopProfileMenuFragment;
import com.ksntechnology.inventoryrecord.main_packet.other.OtherFragment;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener, ManagementFragment.onSelectItemProcess {
    private LinearLayout btnNavHome;
    private LinearLayout btnNavManage;
    private LinearLayout btnNavOther;
    private ImageView imgNavHome;
    private ImageView imgNavManage;
    private ImageView imgNavOther;
    private TextView txtNavHome;
    private TextView txtNavManage;
    private TextView txtNavOther;
    private ImageView imgTopProfileMenu;
    private ImageView imgTopMenuLogOff;

    private final int SELECT_HOME = 0;
    private final int SELECT_MANAGE = 1;
    private final int SELECT_OTHER = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInstance();
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(
                            R.anim.move_from_right, R.anim.move_to_left,
                            R.anim.move_from_left, R.anim.move_to_right
                    )
                    .add(R.id.mainContentContainer, HomeFragment.newInstance())
                    .commit();
        }
    }

    private void initInstance() {
        ScreenOrientation.setScreenPortrait(this);
        RuntimePermission.onRequestRuntimePermission(this);

        btnNavHome = findViewById(R.id.layoutNav_home);
        btnNavManage = findViewById(R.id.layoutNav_manage);
        btnNavOther = findViewById(R.id.layoutNav_other);
        imgNavHome = findViewById(R.id.imgNav_home);
        imgNavManage = findViewById(R.id.imgNav_manage);
        imgNavOther = findViewById(R.id.imgNav_other);
        txtNavHome = findViewById(R.id.txtNav_home);
        txtNavManage = findViewById(R.id.txtNav_manage);
        txtNavOther = findViewById(R.id.txtNav_other);
        imgTopProfileMenu = findViewById(R.id.imgTopMenu);
        imgTopMenuLogOff = findViewById(R.id.imgTopMenuLogOut);

        btnNavHome.setOnClickListener(this);
        btnNavManage.setOnClickListener(this);
        btnNavOther.setOnClickListener(this);
        imgTopProfileMenu.setOnClickListener(this);
        imgTopMenuLogOff.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgTopMenu:
                openTopProfileMenu();
                break;
            case R.id.imgTopMenuLogOut:
                onBackPressed();
                break;
            case R.id.layoutNav_home:
                changeFragment(HomeFragment.newInstance(), SELECT_HOME);
                break;
            case  R.id.layoutNav_manage:
                changeFragment(ManagementFragment.newInstance(), SELECT_MANAGE);
                break;
            case R.id.layoutNav_other:
                changeFragment(OtherFragment.newInstance(), SELECT_OTHER);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        confirmExitApp();
        //super.onBackPressed();
    }


    private void confirmExitApp() {
        if (!isTopProfileMenuActive()) {
            return;
        }

        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.custom_logoff_layout, null);

        final AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setView(view);

        TextView txtTitle = view.findViewById(R.id.txtLogoffTitle);
        Button btnCancel = view.findViewById(R.id.btnLogoffCancel);
        Button btnConfirm = view.findViewById(R.id.btnLogoffConfirm);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        dialog.show();
    }

    private void openTopProfileMenu() {
        Fragment fragment = getSupportFragmentManager()
                .findFragmentById(R.id.mainContentContainer);

        if (fragment instanceof TopProfileMenuFragment == false) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(
                            R.anim.anim_move_from_top, R.anim.anim_move_to_bottom,
                            R.anim.anim_move_from_bottom, R.anim.anim_move_to_top
                    )
                    .add(
                            R.id.mainContentContainer,
                            TopProfileMenuFragment.newInstance())
                    .addToBackStack(null)
                    .commit();
        }
    }

    private boolean isTopProfileMenuActive() {
        Fragment fragment = getSupportFragmentManager()
                .findFragmentById(R.id.mainContentContainer);

        if (fragment instanceof TopProfileMenuFragment == false) {
            return true;
        } else {
            return false;
        }
    }

    private void changeFragment(Fragment fm, int fragmentNumber) {
        if (!isTopProfileMenuActive()) {
            return;
        }

        Fragment fragment = getSupportFragmentManager()
                .findFragmentById(R.id.mainContentContainer);

        switch (fragmentNumber) {
            case SELECT_HOME:
                if (fragment instanceof HomeFragment == true) {
                    return;
                }else{
                    setActiveNavBottomMenu(SELECT_HOME);
                }
                break;
            case SELECT_MANAGE:
                if (fragment instanceof AddUserFragment == true) {
                    return;
                } else {
                    setActiveNavBottomMenu(SELECT_MANAGE);
                }
                break;
            case SELECT_OTHER:
                if (fragment instanceof OtherFragment == true) {
                    return;
                } else {
                    setActiveNavBottomMenu(SELECT_OTHER);
                }
                break;
        }

        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(
                        R.anim.move_from_right, R.anim.move_to_left,
                        R.anim.move_from_left, R.anim.move_to_right
                )
                .replace(R.id.mainContentContainer, fm)
                .commit();
    }

    private void setActiveNavBottomMenu(int navSelected) {
        if (navSelected == SELECT_HOME) {
            setNavBottomHomeActive();
        } else if (navSelected == SELECT_MANAGE) {
            setNavBottomManageActive();
        } else if (navSelected == SELECT_OTHER) {
            setNavBottomOtherActive();
        }
    }

    private void setNavBottomHomeActive() {
        imgNavHome.setImageResource(R.drawable.ic_home_active);
        txtNavHome.setTextColor(
                ContextCompat.getColor(this, R.color.text_nav_bottom_active)
        );

        imgNavManage.setImageResource(R.drawable.ic_manage_normal);
        txtNavManage.setTextColor(
                ContextCompat.getColor(this, R.color.text_nav_bottom_normal)
        );

        imgNavOther.setImageResource(R.drawable.ic_other_normal);
        txtNavOther.setTextColor(
                ContextCompat.getColor(this, R.color.text_nav_bottom_normal)
        );
    }

    private void setNavBottomManageActive() {
        imgNavHome.setImageResource(R.drawable.ic_home_normal);
        txtNavHome.setTextColor(
                ContextCompat.getColor(this, R.color.text_nav_bottom_normal)
        );

        imgNavManage.setImageResource(R.drawable.ic_manage_active);
        txtNavManage.setTextColor(
                ContextCompat.getColor(this, R.color.text_nav_bottom_active)
        );

        imgNavOther.setImageResource(R.drawable.ic_other_normal);
        txtNavOther.setTextColor(
                ContextCompat.getColor(this, R.color.text_nav_bottom_normal)
        );
    }

    private void setNavBottomOtherActive() {
        imgNavHome.setImageResource(R.drawable.ic_home_normal);
        txtNavHome.setTextColor(
                ContextCompat.getColor(this, R.color.text_nav_bottom_normal)
        );

        imgNavManage.setImageResource(R.drawable.ic_manage_normal);
        txtNavManage.setTextColor(
                ContextCompat.getColor(this, R.color.text_nav_bottom_normal)
        );

        imgNavOther.setImageResource(R.drawable.ic_other_active);
        txtNavOther.setTextColor(
                ContextCompat.getColor(this, R.color.text_nav_bottom_active)
        );
    }


    @Override
    public void onSelectedItemProcess(int processName) {
        Intent intent = new Intent(
                MainActivity.this,
                ManagementActivity.class
        );
        intent.putExtra("PROCESS_NAME", processName);
        startActivity(intent);
        overridePendingTransition(
                R.anim.anim_move_from_bottom,
                R.anim.anim_move_to_top);
    }
}
