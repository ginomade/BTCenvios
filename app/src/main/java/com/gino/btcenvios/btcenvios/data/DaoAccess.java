package com.gino.btcenvios.btcenvios.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.gino.btcenvios.btcenvios.model.Balance;
import com.gino.btcenvios.btcenvios.model.SavedOperations;

import java.util.List;

/**
 * @author gino.ghiotto
 */
@Dao
public interface DaoAccess {
    @Insert
    void insertOperation(SavedOperations operation);

    @Query("SELECT * FROM SavedOperations")
    List<SavedOperations> fetchOperations();

    @Query("SELECT * FROM Balance LIMIT 1")
    LiveData<Balance> fetchBalance();

    @Insert
    void insertBalance(Balance balance);

    @Query("DELETE FROM Balance")
    void deleteBalance();
}
