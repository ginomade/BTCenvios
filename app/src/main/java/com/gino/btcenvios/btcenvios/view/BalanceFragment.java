package com.gino.btcenvios.btcenvios.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Room;

import com.gino.btcenvios.R;
import com.gino.btcenvios.btcenvios.data.OperationsDataBase;
import com.gino.btcenvios.btcenvios.model.Balance;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BalanceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BalanceFragment extends Fragment {

    private static final String DATABASE_NAME = "BTCOperationsDB";
    private OperationsDataBase operationsDatabase;

    TextView vBalance;

    BalanceViewModel viewModel;

    public BalanceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment BalanceFragment.
     */
    public static BalanceFragment newInstance() {
        BalanceFragment fragment = new BalanceFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        operationsDatabase = Room.databaseBuilder(getContext(),
                OperationsDataBase.class, DATABASE_NAME)
                .build();

        viewModel = ViewModelProviders.of(getActivity()).get(BalanceViewModel.class);
        viewModel.getBalance().observe(this, new Observer<Balance>() {
            @Override
            public void onChanged(Balance balance) {
                vBalance.setText(balance.getCurrent().toString());
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_balance, container, false);
        vBalance = (TextView) view.findViewById(R.id.tv_value);

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
    }
}
