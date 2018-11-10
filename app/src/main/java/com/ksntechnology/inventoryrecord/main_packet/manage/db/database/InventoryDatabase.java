package com.ksntechnology.inventoryrecord.main_packet.manage.db.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.ksntechnology.inventoryrecord.main_packet.manage.db.DateConverter;
import com.ksntechnology.inventoryrecord.main_packet.manage.db.dao.InventoryDao;
import com.ksntechnology.inventoryrecord.main_packet.manage.db.entity.CustomerEntity;
import com.ksntechnology.inventoryrecord.main_packet.manage.db.entity.ModelEntity;
import com.ksntechnology.inventoryrecord.main_packet.manage.db.entity.ProductEntity;
import com.ksntechnology.inventoryrecord.main_packet.manage.db.entity.UserListEntity;

@Database(entities = {UserListEntity.class, ModelEntity.class, CustomerEntity.class, ProductEntity.class}, version = 1)
@TypeConverters(DateConverter.class)
public abstract class InventoryDatabase extends RoomDatabase {
    private static InventoryDatabase INSTANCE;
    public abstract InventoryDao inventoryDao();

    public static InventoryDatabase getDataBase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                    context,
                    InventoryDatabase.class,
                    "InventoryDB"
            ).build();
        }

        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

}
