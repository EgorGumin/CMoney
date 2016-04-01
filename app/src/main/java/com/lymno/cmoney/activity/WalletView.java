package com.lymno.cmoney.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.lymno.cmoney.R;
import com.lymno.cmoney.Server;
import com.lymno.cmoney.adapter.WalletOperationsAdapter;
import com.lymno.cmoney.adapter.WalletsAdapter;
import com.lymno.cmoney.model.WalletOperation;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.Bind;

public class WalletView extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.wallet_view_toolbar);
        setSupportActionBar(toolbar);

        ArrayList<WalletOperation> walletOperations = Server.getWallets().get(0).getOperations();
        WalletOperationsAdapter adapter = new WalletOperationsAdapter(this, walletOperations);

        // настраиваем список
        ListView lvMain = (ListView) findViewById(R.id.wallet_view_lv);
        lvMain.setAdapter(adapter);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}


