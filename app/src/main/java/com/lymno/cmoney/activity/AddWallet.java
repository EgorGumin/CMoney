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
import android.widget.TextView;
import android.widget.Toast;

import com.lymno.cmoney.App;
import com.lymno.cmoney.R;
import com.lymno.cmoney.adapter.FriendsAdapter;
import com.lymno.cmoney.adapter.WalletOperationsAdapter;
import com.lymno.cmoney.model.Wallet;
import com.lymno.cmoney.model.export.BaseWalletInfo;
import com.lymno.cmoney.model.export.WalletID;
import com.lymno.cmoney.network.RestClient;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class AddWallet extends AppCompatActivity {
    @Bind(R.id.add_wallet_wallet_name)
    protected TextView walletName;

    @Bind(R.id.add_wallet_goal)
    protected TextView walletGoal;

    @Bind(R.id.add_wallet_btn_new_wallet)
    protected Button addWalletBtn;

    @Bind(R.id.add_wallet_btn_join)
    protected Button join;

    @Bind(R.id.add_wallet_friend_number)
    protected EditText friendNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_wallet);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @OnClick(R.id.add_wallet_btn_new_wallet)
    public void newWallet(){
        String tokenKey = "com.lymno.cmoney.activity.token";
        SharedPreferences settings;
        settings = this.getSharedPreferences(
                "com.lymno.cmoney.activity", Context.MODE_PRIVATE);

        String token = settings.getString(tokenKey, "");


        BaseWalletInfo walletInfo = new BaseWalletInfo(walletName.getText().toString(),
                100 * Integer.parseInt(walletGoal.getText().toString()));
        RestClient.get().addWallet(token, walletInfo, new Callback<Wallet>() {
            @Override
            public void success(Wallet wallet, Response response) {
//                todo redirect to wallet info
                wallet.saveFull();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(AddWallet.this, error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @OnClick(R.id.add_wallet_btn_join)
    public void joinWallet(){
        String tokenKey = "com.lymno.cmoney.activity.token";
        SharedPreferences settings;
        settings = this.getSharedPreferences(
                "com.lymno.cmoney.activity", Context.MODE_PRIVATE);

        String token = settings.getString(tokenKey, "");


        WalletID walletID = new WalletID(Integer.parseInt(friendNumber.getText().toString()));
        RestClient.get().addUserToWallet(token, walletID, new Callback<Wallet>() {
            @Override
            public void success(Wallet wallet, Response response) {
//                todo redirect to wallet info
                wallet.saveFull();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(AddWallet.this, error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}
