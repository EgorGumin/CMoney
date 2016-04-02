package com.lymno.cmoney.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.lymno.cmoney.R;
import com.lymno.cmoney.model.Wallet;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WalletInfo extends AppCompatActivity {
    @Bind(R.id.wallet_info_wallet_number)
    protected TextView number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_info);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", -666);
        Wallet wallet = Wallet.getByID(id);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        number.setText(wallet.getWalletID() + "");
    }

}
