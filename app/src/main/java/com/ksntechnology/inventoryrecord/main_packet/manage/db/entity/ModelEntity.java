package com.ksntechnology.inventoryrecord.main_packet.manage.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Models")
public class ModelEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "mid")
    private int modelId;

    @ColumnInfo(name = "model_name")
    private String modelName;


    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
}
