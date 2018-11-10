package com.ksntechnology.inventoryrecord.main_packet.manage.db.entity;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "Products")
public class ProductEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "pid")
    private int productId;

    @ColumnInfo(name = "barcode")
    private String barcode;

    @ColumnInfo(name = "model_id")
    private int modelId;

    @ColumnInfo(name = "product_name")
    private String productName;

    @ColumnInfo(name = "date")
    private Date date;

    @ColumnInfo(name = "price")
    private double price;

    @ColumnInfo(name = "user_record")
    private String userRecord;

    @ColumnInfo(name = "image_path")
    private String imagePath;


    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUserRecord() {
        return userRecord;
    }

    public void setUserRecord(String userRecord) {
        this.userRecord = userRecord;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
