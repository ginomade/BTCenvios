package com.gino.btcenvios.btcenvios.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.gino.btcenvios.btcenvios.model.Balance;
import com.gino.btcenvios.btcenvios.model.SavedOperations;

/**
 * @author gino.ghiotto
 */
@Database(entities = {SavedOperations.class, Balance.class}, version = 1, exportSchema = false)
public abstract class OperationsDataBase extends RoomDatabase {
    public abstract DaoAccess daoAccess();

    private static OperationsDataBase INSTANCE;

    private static final String DATABASE_NAME = "BTCOperationsDB";

    public static OperationsDataBase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), OperationsDataBase.class, DATABASE_NAME)
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

}
