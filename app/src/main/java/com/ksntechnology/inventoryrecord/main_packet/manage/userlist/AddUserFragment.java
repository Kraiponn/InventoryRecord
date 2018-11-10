package com.ksntechnology.inventoryrecord.main_packet.manage.userlist;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ksntechnology.inventoryrecord.R;
import com.ksntechnology.inventoryrecord.dialog.MyCustomToast;
import com.ksntechnology.inventoryrecord.dialog.SingleChoiceDialog;
import com.ksntechnology.inventoryrecord.main_packet.global.EmailValidation;
import com.ksntechnology.inventoryrecord.main_packet.global.ViewNotify;
import com.ksntechnology.inventoryrecord.main_packet.manage.ProcessDestinationType;
import com.ksntechnology.inventoryrecord.main_packet.manage.adapter.UserListAdapter;
import com.ksntechnology.inventoryrecord.main_packet.manage.db.database.InventoryDatabase;
import com.ksntechnology.inventoryrecord.main_packet.manage.db.entity.UserListEntity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddUserFragment extends Fragment implements View.OnClickListener, IMainUserList.IUserListView{
    private ImageView imgProfile;
    private TextView txtSelectImage;
    private EditText edtUserName;
    private EditText edtEmail;
    private EditText edtPassword;
    private EditText edtPosition;
    private Button btnDialogPosition;
    private Button btnConfirm;
    private RadioButton radAdd;
    private RadioButton radEdit;
    private RadioButton radDelete;
    private RadioButton radSearch;
    private RecyclerView rcv;
    private LinearLayout layoutInputContainer;

    private final int REQUEST_IMAGE_ID = 99;
    private String[] mPosItem;
    private String mImgFileName;
    private Bitmap mImgBitmap;
    private UserListPresenter mPresenter;
    private UserListAdapter mAdapter;
    private List<UserListEntity> mUserList;
    //Star wars : The Force Awakens

    public static AddUserFragment newInstance() {
        AddUserFragment fm = new AddUserFragment();
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
                R.layout.fragment_add_user,
                container, false
        );

        initInstance(view);
        return view;
    }

    private void initInstance(View view) {
        imgProfile = view.findViewById(R.id.imgUserList_profile);
        txtSelectImage = view.findViewById(R.id.txtUserList_selectImage);
        edtUserName = view.findViewById(R.id.edtUserList_userName);
        edtEmail = view.findViewById(R.id.edtUserList_email);
        edtPassword = view.findViewById(R.id.edtUserList_password);
        edtPosition = view.findViewById(R.id.edtUserList_position);
        btnDialogPosition = view.findViewById(R.id.btnUserList_openPositionDialog);
        btnConfirm = view.findViewById(R.id.btnUserList_confirm);
        radAdd = view.findViewById(R.id.radUser_add);
        radEdit = view.findViewById(R.id.radUser_edit);
        radDelete = view.findViewById(R.id.radUser_delete);
        radSearch = view.findViewById(R.id.radUser_searchUser);
        rcv = view.findViewById(R.id.recyclerViewUserList);
        layoutInputContainer = view.findViewById(R.id.layoutUserList_inputView);

        txtSelectImage.setOnClickListener(this);
        btnConfirm.setOnClickListener(this);
        btnConfirm.setOnClickListener(this);
        btnDialogPosition.setOnClickListener(this);
        radAdd.setOnClickListener(this);
        radEdit.setOnClickListener(this);
        radDelete.setOnClickListener(this);
        radSearch.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtUserList_selectImage:
                OpenImageGallery();
                break;
            case R.id.btnUserList_openPositionDialog:
                openPositionDialog();
                break;
            case R.id.btnUserList_confirm:
                executeProcess();
                break;
            case R.id.radUser_add:
                displayInputMode();
                btnConfirm.setText("Add");
                break;
            case R.id.radUser_edit:
                hideInputMode();
                btnConfirm.setText("Edit");
                break;
            case R.id.radUser_delete:
                hideInputMode();
                btnConfirm.setText("Delete");
                break;
            case R.id.radUser_searchUser:
                hideInputMode();
                btnConfirm.setText("Search");
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_ID && resultCode == getActivity().RESULT_OK) {
            if (data == null) {
                return;
            }

            Uri imgUri = data.getData();
            String[] projection = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContext().getContentResolver().query(
                    imgUri,
                    projection,
                    null, null, null
            );

            if (cursor != null) {
                cursor.moveToNext();
                int columnIndex = cursor.getColumnIndex(projection[0]);
                String path = cursor.getString(columnIndex);

                mImgBitmap = BitmapFactory.decodeFile(path);
                imgProfile.setImageBitmap(mImgBitmap);
                displayInputMode();
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        initView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        InventoryDatabase.destroyInstance();
    }

    private void initView() {
       hideInputMode();
        mUserList = new ArrayList<>();
        mPresenter = new UserListPresenter(this);
        mPosItem = getResources().getStringArray(
                R.array.array_position
        );

        mAdapter = new UserListAdapter(getContext());
        rcv.setAdapter(mAdapter);
        rcv.setLayoutManager(new LinearLayoutManager(getContext()));

        loadAllUserListToView();
    }

    private void displayInputMode() {
        layoutInputContainer.setVisibility(View.VISIBLE);
    }

    private void hideInputMode() {
        layoutInputContainer.setVisibility(View.GONE);
    }

    private void loadAllUserListToView() {
        mPresenter.getUserLists(
                getContext(),
                UserListProcessType.PROCESS_GET_USER,
                new UserListEntity()
        );
    }

    private void openPositionDialog() {
        FragmentManager fm = getFragmentManager();
        SingleChoiceDialog dialog = SingleChoiceDialog.getDialog(
                mPosItem, getString(R.string.text_dialog_select_position)
        );
        dialog.show(fm, null);
        dialog.setOnDialogItemSelectListener(new SingleChoiceDialog.OnDialogFinishListener() {
            @Override
            public void onDialogFinished(int position) {
                if (position != -1) {
                    edtPosition.setText(mPosItem[position]);
                } else {
                    edtPosition.setText("");
                }
            }
        });
    }

    private void OpenImageGallery() {
        Intent intGallery = new Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        );
        startActivityForResult(intGallery, REQUEST_IMAGE_ID);
    }

    private String getImageFileName() {
        Date date = new Date();
        SimpleDateFormat dateFormat =
                new SimpleDateFormat("yyyyMMdd_HHmmss");
        return dateFormat.format(date) + ".png";
    }

    private String getImageFilePath(String fileName) {
        File intDir = getActivity().getFilesDir();
        return intDir.getAbsolutePath() + "/" + fileName;
    }

    private void clearAllView() {
        edtUserName.setText("");
        edtEmail.setText("");
        edtPosition.setText("");
        edtPassword.setText("");
        mImgBitmap = null;
        imgProfile.setImageResource(0);
    }

    private void saveImageToInternalDir() {
        if (imgProfile.getDrawable() == null) {
            return;
        }

        /*mImgFileName = getImageFileName();
        String imgPath = getImageFilePath(mImgFileName);*/

        try {
            FileOutputStream stream = getContext().openFileOutput(
                    mImgFileName,
                    Context.MODE_PRIVATE
            );
            mImgBitmap.compress(
                    Bitmap.CompressFormat.PNG,
                    50,
                    stream);
            stream.flush();
            stream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*MyCustomToast.toastSuccessMessage(
                getContext(), "Image path: " + imgPath);*/
    }

    private void executeProcess() {
        if (!isSelectProcessType()) {
            return;
        }

        String userName = edtUserName.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        String position = edtPosition.getText().toString().trim();

        if (isInvalidSomeView(userName, email, password, mImgBitmap, position)) {
            return;
        }

        mImgFileName = getImageFileName();
        //String imgFilePath = getImageFilePath(mImgFileName);

        if (radAdd.isChecked()) {
            requestProcessDB(
                    UserListProcessType.PROCESS_ADD_USER,
                    userName,
                    email,
                    password,
                    mImgFileName,
                    position
            );
        } else if (radEdit.isChecked()) {
            requestProcessDB(
                    UserListProcessType.PROCESS_EDIT_USER,
                    userName,
                    email,
                    password,
                    mImgFileName,
                    position
            );
        }else if (radDelete.isChecked()) {
            requestProcessDB(
                    UserListProcessType.PROCESS_DELETE_USER_BY_ID,
                    userName,
                    email,
                    password,
                    mImgFileName,
                    position
            );
        }
    }

    private boolean isSelectProcessType() {
        if (!radAdd.isChecked() && !radEdit.isChecked() &&
                !radDelete.isChecked() && !radSearch.isChecked()) {
            ViewNotify.setRadioButtonNotify(
                    getContext(),
                    getResources().getString(R.string.text_alert_radio_button_no_select),
                    radAdd
            );
            return false;
        }

        return true;
    }

    private void requestProcessDB(int processType, String userName, String email, String password, String imgFileName, String position) {
        UserListEntity uItem = new UserListEntity();
        uItem.setUserName(userName);
        uItem.setEmail(email);
        uItem.setPassword(password);
        uItem.setImagePath(imgFileName);
        uItem.setPosition(position);

        mPresenter.addNewUserList(
                getContext(),
                processType,
                uItem
        );
    }

    private boolean isInvalidSomeView(String userName, String email, String password, Bitmap imgBitmap, String position) {
        if (imgBitmap == null) {
            ViewNotify.setImageViewNotify(
                    getContext(),
                    getString(R.string.text_alert_image_is_null),
                    imgProfile
            );
            return true;
        }else if (userName.isEmpty()) {
            ViewNotify.setEdittextNotify(
                    getContext(),
                    getString(R.string.text_alert_view_is_null),
                    edtUserName
            );
            return true;
        }else if (email.isEmpty()) {
            ViewNotify.setEdittextNotify(
                    getContext(),
                    getString(R.string.text_alert_view_is_null),
                    edtEmail
            );
            return true;
        }else if (!EmailValidation.isEmailValid(email)) {
            ViewNotify.setEdittextNotify(
                    getContext(),
                    getString(R.string.text_alert_email_format),
                    edtEmail
            );
            return true;
        }else if (password.isEmpty()) {
            ViewNotify.setEdittextNotify(
                    getContext(),
                    getString(R.string.text_alert_view_is_null),
                    edtPassword
            );
            return true;
        }else if (position.isEmpty()) {
            ViewNotify.setEdittextNotify(
                    getContext(),
                    getString(R.string.text_alert_view_is_null),
                    edtPosition
            );
            return true;
        }

        return false;
    }


    @Override
    public void checkUserListStatusCallBack(boolean statusCallBack) {
        if (statusCallBack) {
            saveImageToInternalDir();
            clearAllView();

            MyCustomToast.toastSuccessMessage(
                    getContext(),
                    getString(R.string.text_alert_added_item)
            );

            mPresenter.getUserLists(
                    getContext(),
                    UserListProcessType.PROCESS_GET_USER,
                    new UserListEntity()
            );
        } else {
            MyCustomToast.toastErrorMessage(
                    getContext(),
                    getString(R.string.text_alert_added_item_fail)
            );
        }
    }

    @Override
    public void setUserListDataCallBack(List<UserListEntity> userList, boolean statusCallBack) {
        if (statusCallBack) {
            mAdapter.setDao(userList);
            mAdapter.notifyDataSetChanged();
            mAdapter.setOnItemSelectListener(recyclerViewItemSelect);
        }
    }


    /******************************
     *  Listener Zone
     */
    UserListAdapter.onRecyclerViewItemSelected recyclerViewItemSelect = new UserListAdapter.onRecyclerViewItemSelected() {
        @Override
        public void onItemSelect(UserListEntity userItem) {
            if (radSearch.isChecked()) {
                hideInputMode();
                btnConfirm.setText("Add");
                clearAllView();
                return;
            }else if (radAdd.isChecked()) {
                btnConfirm.setText("Add");
                clearAllView();
                return;
            }else if (!radAdd.isChecked() && !radSearch.isChecked() &&
                    !radEdit.isChecked() && !radDelete.isChecked()) {
                btnConfirm.setText("Add");
                clearAllView();
                return;
            }else if (radEdit.isChecked()) {
                btnConfirm.setText("Update");
            } else if (radDelete.isChecked()) {
                btnConfirm.setText("Delete");
            }

            mImgBitmap = BitmapFactory
                    .decodeFile(getImageFilePath(userItem.getImagePath()));
            imgProfile.setImageBitmap(mImgBitmap);
            edtUserName.setText(userItem.getUserName());
            edtEmail.setText(userItem.getEmail());
            edtPassword.setText(userItem.getPassword());
            edtPosition.setText(userItem.getPosition());
            displayInputMode();
        }
    };

}
