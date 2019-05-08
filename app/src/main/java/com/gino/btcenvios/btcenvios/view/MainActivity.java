package com.gino.btcenvios.btcenvios.view;

import android.os.Bundle;

import com.gino.btcenvios.R;
import com.gino.btcenvios.btcenvios.data.OperationsDataBase;
import com.gino.btcenvios.btcenvios.model.Balance;
import com.gino.btcenvios.btcenvios.model.Rates;
import com.gino.btcenvios.btcenvios.net.GetRatesServiceImpl;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity {
    private GetRatesServiceImpl service;

    private FrameLayout vMainContent;

    private static final String DATABASE_NAME = "BTCOperationsDB";
    private OperationsDataBase operationsDatabase;

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
        vMainContent = findViewById(R.id.main_content);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //Call<List<Rates>> rates = service.service.listRates();


        operationsDatabase = Room.databaseBuilder(getApplicationContext(),
                OperationsDataBase.class, DATABASE_NAME)
                .build();

        Balance balance = new Balance(100d);
        operationsDatabase.daoAccess.insertBalance(balance);
    }

    private void navigateTo(final Fragment toFragment) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

            fragmentTransaction.replace(R.id.main_content, toFragment);

            fragmentTransaction.commit();
    }
}
