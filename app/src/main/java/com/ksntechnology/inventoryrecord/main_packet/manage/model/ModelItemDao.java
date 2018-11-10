package com.ksntechnology.inventoryrecord.main_packet.manage.model;

public class ModelItemDao {
    private int id;
    private String modelName;

    public ModelItemDao(int id, String modelName) {
        this.id = id;
        this.modelName = modelName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
}
