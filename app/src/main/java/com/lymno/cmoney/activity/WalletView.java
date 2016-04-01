package com.lymno.cmoney.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.lymno.cmoney.R;
import com.lymno.cmoney.Server;
import com.lymno.cmoney.adapter.WalletOperationsAdapter;
import com.lymno.cmoney.adapter.WalletsAdapter;
import com.lymno.cmoney.model.Wallet;
import com.lymno.cmoney.model.WalletOperation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WalletView extends AppCompatActivity {
    public static final String WALLET_ID = "id";
    private Wallet wallet;

    @Bind(R.id.wallet_view_sum)
    protected EditText sum;

    @Bind(R.id.wallet_view_text)
    protected EditText text;

    @Bind(R.id.wallet_view_btn_add)
    protected Button add;

    private ListView lvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_view);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.wallet_view_toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        int id = intent.getIntExtra(WALLET_ID, -666);
        long test = intent.getLongExtra("test", 11L);
        wallet = Wallet.getByID(id);
        WalletOperationsAdapter adapter = new WalletOperationsAdapter(this, wallet.getOperations());

        // настраиваем список
        lvMain = (ListView) findViewById(R.id.wallet_view_lv);
        lvMain.setAdapter(adapter);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @OnClick(R.id.wallet_view_btn_add)
    public void addOperation(){
//        todo set login
        wallet.getOperations().add(0, new WalletOperation(text.getText().toString(), "LOGIN",
        new Date(), 100*Integer.parseInt(sum.getText().toString())));
        wallet.saveFull();
        setAdapter();
    }

    private void setAdapter(){
        lvMain.setAdapter(new WalletOperationsAdapter(this, wallet.getOperations()));
    }
}


