package com.gino.btcenvios.btcenvios.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.gino.btcenvios.R;
import com.gino.btcenvios.btcenvios.model.SavedOperations;

import java.util.List;

public class HistoryFragment extends Fragment {

    HistoryViewModel viewModel;

    public static HistoryFragment newInstance() {
        HistoryFragment fragment = new HistoryFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(HistoryViewModel.class);
        viewModel.getOperations().observe(this, new Observer<List<SavedOperations>>() {
            @Override
            public void onChanged(List<SavedOperations> savedOperations) {

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_history, container, false);
    }


}
