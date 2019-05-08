package com.gino.btcenvios.btcenvios.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.gino.btcenvios.btcenvios.model.Balance;
import com.gino.btcenvios.btcenvios.model.SavedOperations;

/**
 * @author gino.ghiotto
 */
@Database(entities = {SavedOperations.class, Balance.class}, version = 1, exportSchema = false)
public abstract class OperationsDataBase extends RoomDatabase {
    public DaoAccess daoAccess;

}
