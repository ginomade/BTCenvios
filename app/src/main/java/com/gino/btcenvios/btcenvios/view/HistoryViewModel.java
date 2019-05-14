package com.gino.btcenvios.btcenvios.view;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gino.btcenvios.R;
import com.gino.btcenvios.btcenvios.data.OperationsDataBase;
import com.gino.btcenvios.btcenvios.model.Balance;
import com.gino.btcenvios.btcenvios.model.Rate;
import com.gino.btcenvios.btcenvios.model.SavedOperations;
import com.gino.btcenvios.btcenvios.net.GetRatesService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author gino.ghiotto
 */
public class HistoryViewModel extends AndroidViewModel {
    private LiveData<Balance> mBalance;
    OperationsDataBase dataBase;
    private MutableLiveData<Rate> mRates;
    private LiveData<List<SavedOperations>> mOperations;
    private OperationsAdapter adapter;

    public HistoryViewModel(@NonNull Application application) {
        super(application);
        dataBase = OperationsDataBase.getAppDatabase(application);
        mBalance = dataBase.daoAccess().fetchBalance();
        adapter = new OperationsAdapter(R.layout.operations_item_view, this);
    }

    public OperationsAdapter getAdapter() {
        return adapter;
    }

    LiveData<Balance> getBalance() {
        return mBalance;
    }


    void setDataInAdapter(List<SavedOperations> ops){
        adapter.setList(ops);
    }

    void insertBalance(Balance balance) {
        dataBase.daoAccess().deleteBalance();
        dataBase.daoAccess().insertBalance(balance);
    }

    public LiveData<List<SavedOperations>> getOperations() {
        mOperations = dataBase.daoAccess().fetchOperations();
        return mOperations;
    }

    public SavedOperations getOperationAt(Integer index){
        return mOperations.getValue().get(index);
    }
}
