package com.ksntechnology.inventoryrecord.main_packet.manage.userlist;

import android.content.Context;
import android.os.AsyncTask;

import com.ksntechnology.inventoryrecord.main_packet.manage.db.database.InventoryDatabase;
import com.ksntechnology.inventoryrecord.main_packet.manage.db.entity.UserListEntity;

import java.util.List;

public class UserListPresenter implements IMainUserList.IUserListPresenter {
    private static IMainUserList.IUserListView mViewCallBack;

    public UserListPresenter(IMainUserList.IUserListView mViewCallBack) {
        this.mViewCallBack = mViewCallBack;
    }

    @Override
    public void addNewUserList(Context context, int processType, UserListEntity userList) {
        new ManageUserListDB(context, processType, userList).execute();
    }

    @Override
    public void getUserLists(Context context, int processType, UserListEntity userList) {
        new ManageUserListDB(
                context, processType, userList
        ).execute();
    }

    @Override
    public void editUserList(Context context, int processType, UserListEntity userList) {
        //
    }

    @Override
    public void deleteUserList(Context context, int processType, UserListEntity userList) {
        //
    }


    public static class ManageUserListDB extends AsyncTask<Void, Void, Void> {
        private Context mContext;
        private int mProcessType;
        private UserListEntity mDao;
        private List<UserListEntity> mUserItem;

        public ManageUserListDB(Context mContext, int mProcessType, UserListEntity mDao) {
            this.mContext = mContext;
            this.mProcessType = mProcessType;
            this.mDao = mDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            loadUserListProcess();
            return null;
        }

        private void loadUserListProcess() {
            switch (mProcessType) {
                case UserListProcessType.PROCESS_ADD_USER:
                    InventoryDatabase
                        .getDataBase(mContext)
                        .inventoryDao().insertUserList(mDao);
                    break;
                case UserListProcessType.PROCESS_GET_USER:
                    mUserItem = InventoryDatabase
                                    .getDataBase(mContext)
                                    .inventoryDao()
                                    .loadAllUserLists();
                    break;
                case UserListProcessType.PROCESS_GET_USER_BY_USER_NAME:
                    mUserItem = InventoryDatabase
                                .getDataBase(mContext)
                                .inventoryDao()
                                .loadUserListsByUserName(mDao.getUserName());
                    break;
                case UserListProcessType.PROCESS_EDIT_USER:
                    InventoryDatabase
                            .getDataBase(mContext)
                            .inventoryDao()
                            .updateUserListByUserId(
                                    mDao.getUserId(),
                                    mDao.getUserName(),
                                    mDao.getPassword(),
                                    mDao.getEmail(),
                                    mDao.getImagePath(),
                                    mDao.getPosition()
                            );
                    break;
                case UserListProcessType.PROCESS_DELETE_ALL_USER:
                    InventoryDatabase
                            .getDataBase(mContext)
                            .inventoryDao()
                            .deleteAllUserLists();
                    break;
                case UserListProcessType.PROCESS_DELETE_USER_BY_ID:
                    InventoryDatabase
                            .getDataBase(mContext)
                            .inventoryDao()
                            .deleteUserListByUserId(mDao.getUserId());
                    break;
            }

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            //super.onPostExecute(aVoid);
            switch (mProcessType) {
                case UserListProcessType.PROCESS_ADD_USER:
                    mViewCallBack.checkUserListStatusCallBack(true);
                    break;
                case UserListProcessType.PROCESS_GET_USER:
                    if (mUserItem != null) {
                        mViewCallBack.setUserListDataCallBack(
                                mUserItem, true);
                    } else {
                        mViewCallBack.setUserListDataCallBack(
                                mUserItem, false);
                    }
                    break;
                case UserListProcessType.PROCESS_GET_USER_BY_USER_NAME:
                    if (mUserItem != null) {
                        mViewCallBack.setUserListDataCallBack(
                                mUserItem, true);
                    } else {
                        mViewCallBack.setUserListDataCallBack(
                                mUserItem, false);
                    }
                    break;
                case UserListProcessType.PROCESS_EDIT_USER:
                    mViewCallBack.checkUserListStatusCallBack(true);
                    break;
                case UserListProcessType.PROCESS_DELETE_ALL_USER:
                    mViewCallBack.checkUserListStatusCallBack(true);
                    break;
                case UserListProcessType.PROCESS_DELETE_USER_BY_ID:
                    mViewCallBack.checkUserListStatusCallBack(true);
                    break;
            }
        }

    } //------ End Nest Static Class

}

