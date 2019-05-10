package com.gino.btcenvios.btcenvios.view;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gino.btcenvios.btcenvios.data.OperationsDataBase;
import com.gino.btcenvios.btcenvios.model.Balance;
import com.gino.btcenvios.btcenvios.model.Rate;
import com.gino.btcenvios.btcenvios.net.GetRatesService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author gino.ghiotto
 */
public class BalanceViewModel extends AndroidViewModel {
    private LiveData<Balance> mBalance;
    OperationsDataBase dataBase;
    private MutableLiveData<Rate> mRates;

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

    public LiveData<Rate> getRates() {
        //if the list is null
        if (mRates == null) {
            mRates = new MutableLiveData<Rate>();
            loadRates();
        }

        return mRates;
    }

    private void loadRates() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GetRatesService.RATES_SERVICE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetRatesService api = retrofit.create(GetRatesService.class);
        Call<Rate> call = api.listRates();

        call.enqueue(new Callback<Rate>() {
            @Override
            public void onResponse(Call<Rate> call, Response<Rate> response) {
                mRates.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Rate> call, Throwable t) {
                Log.w("btc ", "error");
            }
        });
    }
}
