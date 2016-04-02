package com.lymno.cmoney.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.lymno.cmoney.R;
import com.lymno.cmoney.Server;
import com.lymno.cmoney.adapter.WalletOperationsAdapter;
import com.lymno.cmoney.adapter.WalletsAdapter;
import com.lymno.cmoney.model.Wallet;
import com.lymno.cmoney.model.WalletOperation;
import com.lymno.cmoney.model.export.BaseWalletOperation;
import com.lymno.cmoney.network.RestClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

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
        add.setText("Сохранение...");
        add.setEnabled(false);

        String tokenKey = "com.lymno.cmoney.activity.token";
        SharedPreferences settings;
        settings = this.getSharedPreferences(
                "com.lymno.cmoney.activity", Context.MODE_PRIVATE);

        String token = settings.getString(tokenKey, "");
        final String login = settings.getString("login", "");
        final int money = 100 * (int) Long.parseLong(sum.getText().toString());
        RestClient.get().addOperation(token, new BaseWalletOperation(text.getText().toString(), wallet.getWalletID(),
                money), new Callback<Void>() {
            @Override
            public void success(Void aVoid, Response response) {

                wallet.getOperations().add(0, new WalletOperation(text.getText().toString(), login,
                        new Date(), money));
                wallet.setBalance((int) wallet.recalculateBalance(login));
                wallet.saveFull();
                add.setText("Добавить");
                add.setEnabled(true);
                sum.setText("");
                text.setText("");
                setAdapter();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(WalletView.this, "fail", Toast.LENGTH_SHORT).show();
                add.setText("Добавить");
                add.setEnabled(true);
            }
        });

    }

    private void setAdapter(){
        lvMain.setAdapter(new WalletOperationsAdapter(this, wallet.getOperations()));
    }
}


