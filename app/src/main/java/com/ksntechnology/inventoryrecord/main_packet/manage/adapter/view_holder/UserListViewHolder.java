package com.ksntechnology.inventoryrecord.main_packet.manage.adapter.view_holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ksntechnology.inventoryrecord.R;

public class UserListViewHolder extends RecyclerView.ViewHolder {
    public TextView txtUserListEmail;
    public TextView txtUserListUserName;
    public TextView txtUserListPosition;
    public ImageView imgUserListProfile;

    public UserListViewHolder(@NonNull View itemView) {
        super(itemView);
        imgUserListProfile = itemView.findViewById(R.id.txtUserList_customImage);
        txtUserListUserName = itemView.findViewById(R.id.txtUserList_customUserName);
        txtUserListEmail = itemView.findViewById(R.id.txtUserList_customEmail);
        txtUserListPosition = itemView.findViewById(R.id.txtUserList_customPosition);
    }

}
