package com.gino.btcenvios.btcenvios.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gino.btcenvios.R;
import com.gino.btcenvios.btcenvios.model.SavedOperations;
import com.gino.btcenvios.btcenvios.viewModel.HistoryViewModel;

import java.util.List;

public class HistoryFragment extends Fragment {

    HistoryViewModel mViewModel;
    private RecyclerView mRecyclerView;

    public static HistoryFragment newInstance() {
        HistoryFragment fragment = new HistoryFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        mRecyclerView = view.findViewById(R.id.list_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = ViewModelProviders.of(getActivity()).get(HistoryViewModel.class);
        mViewModel.init();
        mRecyclerView.setAdapter(mViewModel.getAdapter());
        mViewModel.getOperations()
                .observe(getActivity(), new Observer<List<SavedOperations>>() {
                    @Override
                    public void onChanged(List<SavedOperations> savedOperations) {
                        mViewModel.setDataInAdapter(savedOperations);
                    }
                });
    }
}
