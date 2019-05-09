package com.gino.btcenvios.btcenvios.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.gino.btcenvios.btcenvios.data.OperationsDataBase;
import com.gino.btcenvios.btcenvios.model.Balance;

/**
 * @author gino.ghiotto
 */
public class BalanceViewModel extends AndroidViewModel {
    private LiveData<Balance> mBalance;
    OperationsDataBase dataBase;

    public BalanceViewModel(@NonNull Application application) {
        super(application);
        dataBase = OperationsDataBase.getAppDatabase(application);
        mBalance = dataBase.daoAccess().fetchBalance();
    }

    LiveData<Balance> getBalance() {
        return mBalance;
    }

    void insertBalance(Balance balance) {
        dataBase.daoAccess().deleteBalance();
        dataBase.daoAccess().insertBalance(balance);
    }
}
