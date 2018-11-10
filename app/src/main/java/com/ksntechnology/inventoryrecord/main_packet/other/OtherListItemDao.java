package com.ksntechnology.inventoryrecord.main_packet.other;

public class OtherListItemDao {
    private int id;
    private int imgIcon;
    private String title;

    public OtherListItemDao(int id, int imgIcon, String title) {
        this.id = id;
        this.imgIcon = imgIcon;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImgIcon() {
        return imgIcon;
    }

    public void setImgIcon(int imgIcon) {
        this.imgIcon = imgIcon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
