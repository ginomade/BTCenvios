package com.gino.btcenvios.btcenvios.view;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.gino.btcenvios.R;
import com.gino.btcenvios.btcenvios.model.Balance;
import com.gino.btcenvios.btcenvios.viewModel.BalanceViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {


    BalanceViewModel viewModel;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_balance:
                    navigateTo(BalanceFragment.newInstance());
                    return true;
                case R.id.navigation_envios:
                    navigateTo(SendFragment.newInstance());
                    return true;
                case R.id.navigation_historial:
                    navigateTo(HistoryFragment.newInstance());
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        viewModel = ViewModelProviders.of(this).get(BalanceViewModel.class);
        Balance balance = new Balance(200000d);
        viewModel.insertBalance(balance);

        navigateTo(BalanceFragment.newInstance());
    }

    private void navigateTo(final Fragment toFragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.main_content, toFragment);

        fragmentTransaction.commit();
    }

}
