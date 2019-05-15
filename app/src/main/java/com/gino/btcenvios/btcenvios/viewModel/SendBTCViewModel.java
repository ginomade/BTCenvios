package com.gino.btcenvios.btcenvios.viewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gino.btcenvios.btcenvios.data.OperationsDataBase;
import com.gino.btcenvios.btcenvios.model.Balance;
import com.gino.btcenvios.btcenvios.model.Fees;
import com.gino.btcenvios.btcenvios.model.Operation;
import com.gino.btcenvios.btcenvios.model.Rate;
import com.gino.btcenvios.btcenvios.model.SavedOperations;
import com.gino.btcenvios.btcenvios.net.GetFeesService;
import com.gino.btcenvios.btcenvios.net.GetRatesService;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author gino.ghiotto
 */
public class SendBTCViewModel extends AndroidViewModel {
    private LiveData<Balance> mBalance;
    OperationsDataBase dataBase;

    private MutableLiveData<Rate> mRates;
    private MutableLiveData<Fees> mFees;

    public SendBTCViewModel(@NonNull Application application) {
        super(application);
        dataBase = OperationsDataBase.getAppDatabase(application);
        mBalance = dataBase.daoAccess().fetchBalance();
    }

    public LiveData<Balance> getBalance() {
        return mBalance;
    }

    public void insertBalance(Balance balance) {
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

    public LiveData<Fees> getFees() {
        //if the list is null
        if (mFees == null) {
            mFees = new MutableLiveData<Fees>();
            loadFees();
        }

        return mFees;
    }

    private void loadFees() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GetFeesService.FEES_SERVICE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetFeesService api = retrofit.create(GetFeesService.class);
        Call<Fees> call = api.listFees();

        call.enqueue(new Callback<Fees>() {
            @Override
            public void onResponse(Call<Fees> call, Response<Fees> response) {
                mFees.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Fees> call, Throwable t) {
                Log.w("btc fees", "error");
            }
        });
    }

    public String setData(Operation operation, Balance balance) {
        //mocked send operation
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return isBalance(operation, balance) ? "COMPLETED" : "NO_BALANCE";
    }

    private boolean isBalance(Operation operation, Balance balance) {
        return Double.parseDouble(operation.getTotal()) *
                Double.parseDouble(mRates.getValue().getRates().getARS_SELL()) <
                balance.getCurrent();
    }

    public void insertOperation(Operation operation) {
        Random r = new Random();
        SavedOperations savedOp = new SavedOperations();
        savedOp.setAmount(operation.getAmount());
        savedOp.setDate(operation.getDate());
        savedOp.setDestAddress(operation.getDestAddress());
        savedOp.setFee(operation.getFee());
        savedOp.setRate(operation.getRate());
        savedOp.setState(operation.getState());
        savedOp.setTotal(operation.getTotal());
        savedOp.setOperationID(r.nextInt(1000));
        dataBase.daoAccess().insertOperation(savedOp);
    }
}
