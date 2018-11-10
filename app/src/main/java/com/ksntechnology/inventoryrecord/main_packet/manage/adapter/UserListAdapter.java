package com.ksntechnology.inventoryrecord.main_packet.manage.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.ksntechnology.inventoryrecord.R;
import com.ksntechnology.inventoryrecord.main_packet.manage.adapter.view_holder.UserListViewHolder;
import com.ksntechnology.inventoryrecord.main_packet.manage.db.entity.UserListEntity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListViewHolder>{
    private Context context;
    private String mImgFilePath;
    private List<UserListEntity> dao;

    public void setDao(List<UserListEntity> dao) {
        this.dao = dao;
    }

    public UserListAdapter(Context context) {
        this.context = context;
        dao = new ArrayList<>();
        File intDir = context.getFilesDir();
        mImgFilePath = intDir.getAbsolutePath();
    }

    @NonNull
    @Override
    public UserListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(
                R.layout.custom_user_list_data_table,
                viewGroup,
                false
        );

        return new UserListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserListViewHolder vh, int i) {
        final UserListEntity item = dao.get(i);
        vh.txtUserListUserName.setText("UserName: " + item.getUserName());
        vh.txtUserListEmail.setText(item.getEmail());
        vh.txtUserListPosition.setText("Position: " + item.getPosition());

        Glide.with(context)
                .load(mImgFilePath + "/" + item.getImagePath())
                .into(vh.imgUserListProfile);

        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallBack.onItemSelect(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (dao == null) {
            return 0;
        } else if (dao.size() <= 0) {
            return 0;
        } else {
            return dao.size();
        }
        //return dao.size();
    }


    /***********************
     * Interface for Click Item
     */
    public interface onRecyclerViewItemSelected {
        void onItemSelect(UserListEntity userItem);
    }

    private onRecyclerViewItemSelected mCallBack;

    public void setOnItemSelectListener(onRecyclerViewItemSelected listener) {
        mCallBack = listener;
    }

}
