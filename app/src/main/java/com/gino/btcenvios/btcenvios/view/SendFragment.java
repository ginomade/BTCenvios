package com.gino.btcenvios.btcenvios.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.gino.btcenvios.R;
import com.gino.btcenvios.btcenvios.model.Balance;
import com.gino.btcenvios.btcenvios.model.Fees;
import com.gino.btcenvios.btcenvios.model.Operation;
import com.gino.btcenvios.btcenvios.model.Rate;
import com.gino.btcenvios.btcenvios.viewModel.SendBTCViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SendFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SendFragment extends Fragment {

    EditText vDestinationAdd;
    EditText vAmount;
    EditText vFee;
    EditText vTotal;
    EditText vRate;
    TextView vStatus;
    Button vBtnSend;

    SendBTCViewModel sendViewModel;
    Balance mBalance;

    private Operation sendOperationData = new Operation();

    public SendFragment() {
        // Required empty public constructor
    }

    public static SendFragment newInstance() {
        SendFragment fragment = new SendFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sendViewModel = ViewModelProviders.of(getActivity()).get(SendBTCViewModel.class);
        sendViewModel.getRates().observe(this, new Observer<Rate>() {
            @Override
            public void onChanged(Rate rates) {
                if (vRate != null) {
                    vRate.setText(rates.getRates().getARS_SELL());
                    sendOperationData.setRate(rates.getRates().getARS_SELL());
                }
            }
        });
        sendViewModel.getFees().observe(this, new Observer<Fees>() {
            @Override
            public void onChanged(Fees fees) {
                if (vFee != null) {
                    vFee.setText(fees.getFastestFee());
                    sendOperationData.setFee(fees.getFastestFee());
                }
            }
        });
        sendViewModel.getBalance().observe(this, new Observer<Balance>() {
            @Override
            public void onChanged(Balance balance) {
                mBalance = balance;
            }
        });


    }

    private boolean validateFormInfo() {
        boolean allowSend = true;
        if (vDestinationAdd.getText().toString().equals("")) {
            allowSend = false;
            setStatusText("Destino requerido.");
        } else if (vAmount.getText().toString().equals("")) {
            allowSend = false;
            setStatusText("Monto a transferir requerido.");
        }

        return allowSend;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_send, container, false);
        vDestinationAdd = view.findViewById(R.id.editDest);
        vAmount = view.findViewById(R.id.editAmount);
        vFee = view.findViewById(R.id.editFee);
        vRate = view.findViewById(R.id.editRate);
        vTotal = view.findViewById(R.id.editTotal);
        vBtnSend = view.findViewById(R.id.btnSend);
        vStatus = view.findViewById(R.id.textStatus);
        vRate.setEnabled(false);
        vFee.setEnabled(false);

        vBtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateFormInfo()) {
                    sendOperationData.setDestAddress(vDestinationAdd.getText().toString());
                    sendOperationData.setAmount(vAmount.getText().toString());
                    sendOperationData.setDate(getCurrentDate());
                    sendOperationData.setState(getContext().getString(R.string.waiting_state));

                    String result = sendViewModel.setData(sendOperationData, mBalance);
                    if (result.equals(getContext().getString(R.string.completed_status))) {
                        Balance newBalance = new Balance(mBalance.getCurrent() -
                                Double.parseDouble(sendOperationData.getTotal()) *
                                        Double.parseDouble(sendOperationData.getRate()));
                        sendViewModel.insertBalance(newBalance);
                        sendOperationData.setState(getContext().getString(R.string.completed_status));
                        sendViewModel.insertOperation(sendOperationData);
                        setStatusText("Operacion exitosa");
                    } else {
                        setStatusText("Balance Insuficiente");
                        sendOperationData.setState(getContext().getString(R.string.no_balance));
                        sendViewModel.insertOperation(sendOperationData);
                    }
                }
            }
        });

        vAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                setStatusText("");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!vFee.getText().toString().equals("")
                        && !vAmount.getText().toString().equals("")
                        && sendOperationData.getRate() != null) {
                    Double total = Double.parseDouble(vFee.getText().toString()) /
                            Double.parseDouble(sendOperationData.getRate())
                            + Double.parseDouble(vAmount.getText().toString());
                    vTotal.setText(String.valueOf(total));
                    sendOperationData.setTotal(String.valueOf(total));
                }
            }
        });

        return view;
    }

    private void setStatusText(String text) {
        vStatus.setText(text);
        vStatus.setVisibility(text.equals("") ? View.GONE : View.VISIBLE);
    }

    private String getCurrentDate() {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        return df.format(c);
    }
}
