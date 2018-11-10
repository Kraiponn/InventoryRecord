package com.ksntechnology.inventoryrecord.main_packet.manage.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.ksntechnology.inventoryrecord.main_packet.manage.db.entity.CustomerEntity;
import com.ksntechnology.inventoryrecord.main_packet.manage.db.entity.ModelEntity;
import com.ksntechnology.inventoryrecord.main_packet.manage.db.entity.ProductEntity;
import com.ksntechnology.inventoryrecord.main_packet.manage.db.entity.UserListEntity;

import java.util.List;

@Dao
public interface InventoryDao {

    @Insert
    void insertUserList(UserListEntity userListEntity);

    @Insert
    void insertModel(ModelEntity modelEntity);

    @Insert
    void insertCustomer(CustomerEntity customerEntity);

    @Insert
    void insertProduct(ProductEntity productEntity);


    /*************************
     * SELECT TABLE
     */
    @Query("SELECT * FROM Models")
    List<ModelEntity> loadAllModels();

    @Query("SELECT * FROM Models WHERE model_name LIKE :modelName")
    List<ModelEntity> loadModelByModelName(String modelName);



    @Query("SELECT * FROM Customers")
    List<CustomerEntity> loadAllCustomers();

    @Query("SELECT * FROM Customers WHERE model_id = :modelId")
    List<CustomerEntity> loadCustomerByModelId(String modelId);

    @Query("SELECT * FROM Customers WHERE customer_name LIKE :customerName")
    List<CustomerEntity> loadCustomerByCustomerName(String customerName);



    @Query("SELECT * FROM UserList")
    List<UserListEntity> loadAllUserLists();

    @Query("SELECT * FROM UserList WHERE user_name LIKE :userName")
    List<UserListEntity> loadUserListsByUserName(String userName);



    @Query("SELECT * FROM Products")
    List<ProductEntity> loadAllProducts();

    @Query("SELECT * FROM Products WHERE product_name LIKE :productName")
    List<ProductEntity> loadProductsByProductName(String productName);

    @Query("SELECT * FROM Products WHERE barcode LIKE :barcode")
    List<ProductEntity> loadProductsByBarcode(String barcode);

    @Query("SELECT * FROM Products WHERE date = :date")
    List<ProductEntity> loadProductsByDate(String date);

    @Query("SELECT * FROM Products WHERE date >= :fromDate AND date <= :toDate")
    List<ProductEntity> loadProductFromDateToDate(String fromDate, String toDate);



    /*************************
     * UPDATE TABLE
     */
    @Query("UPDATE Models SET model_name = :modelName " +
            " WHERE mid = :modelId")
    void updateModelByModelId(int modelId, String modelName);

    @Query("UPDATE Customers SET customer_name = :customerName, " +
            "model_id = :modelId, " +
            "tel = :tel, " +
            "email = :email " +
            " WHERE cid = :customerId")
    void updateCustomerByCustomerId(int customerId, String customerName,
                                    int modelId, String tel, String email);

    @Query("UPDATE UserList SET user_name = :userName, " +
            "password = :password, " +
            "email = :email, " +
            "image_path = :imagePath, " +
            "position = :position " +
            " WHERE uid = :userId")
    void updateUserListByUserId(int userId, String userName,
                                String password, String email,
                                String imagePath, String position);

    @Query("UPDATE Products SET barcode = :barcode, " +
            "product_name = :productName, " +
            "model_id = :modelId, " +
            "date = :date, " +
            "price = :price, " +
            "user_record = :userRecord, " +
            "image_path = :imagePath " +
            " WHERE pid = :productId")
    void updateProductByProductId(int productId, String barcode,
                                  int modelId, String productName,
                                  String date, double price,
                                  String userRecord, String imagePath);


    /*************************
     * DELETE TABLE
     */
    @Query("DELETE FROM UserList")
    void deleteAllUserLists();

    @Query("DELETE FROM UserList WHERE uid = :userId")
    void deleteUserListByUserId(int userId);

    @Query("DELETE FROM Models")
    void deleteAllModels();

    @Query("DELETE FROM Models WHERE mid = :modelId")
    void deleteModelByModelId(int modelId);

    @Query("DELETE FROM Customers")
    void deleteAllCustomers();

    @Query("DELETE FROM Customers WHERE cid = :customerId")
    void deleteCustomerByCustomerId(int customerId);

    @Query("DELETE FROM Products")
    void deleteAllProducts();

    @Query("DELETE FROM Products WHERE pid = :productId")
    void deleteProductByProductId(int productId);

}
