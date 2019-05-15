package com.gino.btcenvios.btcenvios.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gino.btcenvios.R;
import com.gino.btcenvios.btcenvios.data.OperationsDataBase;
import com.gino.btcenvios.btcenvios.model.SavedOperations;
import com.gino.btcenvios.btcenvios.view.OperationsAdapter;

import java.util.List;

/**
 * @author gino.ghiotto
 */
public class HistoryViewModel extends AndroidViewModel {
    OperationsDataBase dataBase;
    private LiveData<List<SavedOperations>> mOperations;
    private OperationsAdapter adapter;

    public HistoryViewModel(@NonNull Application application) {
        super(application);

    }

    public void init() {
        dataBase = OperationsDataBase.getAppDatabase(getApplication());
        adapter = new OperationsAdapter(R.layout.operations_item_view, this);
        mOperations = new MutableLiveData<>();
    }

    public OperationsAdapter getAdapter() {
        return adapter;
    }

    public void setDataInAdapter(List<SavedOperations> ops) {
        adapter.setList(ops);
    }

    public LiveData<List<SavedOperations>> getOperations() {
        mOperations = dataBase.daoAccess().fetchOperations();
        return mOperations;
    }

    public SavedOperations getOperationAt(Integer index) {
        return mOperations.getValue().get(index);
    }
}
