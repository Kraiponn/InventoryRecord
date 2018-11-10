package com.ksntechnology.inventoryrecord.main_packet.manage.userlist;

import android.content.Context;

import com.ksntechnology.inventoryrecord.main_packet.manage.db.entity.UserListEntity;

import java.util.List;

public interface IMainUserList {

    interface IUserListPresenter{
        void addNewUserList(Context context, int processType, UserListEntity userList);

        void getUserLists(Context context, int processType, UserListEntity userList);

        void editUserList(Context context, int processType, UserListEntity userList);

        void deleteUserList(Context context, int processType, UserListEntity userList);
    }

    interface IUserListView {
        void checkUserListStatusCallBack(boolean statusCallBack);

        void setUserListDataCallBack(List<UserListEntity> userList, boolean statusCallBack);
    }

}
