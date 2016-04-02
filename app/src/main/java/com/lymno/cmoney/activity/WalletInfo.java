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
import android.widget.TextView;
import android.widget.Toast;

import com.lymno.cmoney.R;
import com.lymno.cmoney.model.Wallet;
import com.lymno.cmoney.model.export.WalletID;
import com.lymno.cmoney.network.RestClient;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class WalletInfo extends AppCompatActivity {
    @Bind(R.id.wallet_info_wallet_number)
    protected TextView number;

    @Bind(R.id.wallet_info_selfdelete)
    protected Button delete;

    @Bind(R.id.wallet_info_friends)
    protected TextView friends;

    @Bind(R.id.wallet_info_start_day)
    protected TextView startDay;

    private Wallet wallet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_info);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", -666);
        wallet = Wallet.getByID(id);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        number.setText(wallet.getWalletID() + "");
        if(wallet.getFriends().size() > 0){
            friends.setText("");
            for(String s : wallet.getFriends()){
                friends.append(s + "\n");
            }
        }

        startDay.setText(wallet.getStartDay() + "");
    }

    @OnClick(R.id.wallet_info_selfdelete)
    public void selfDelete(){
        String tokenKey = "com.lymno.cmoney.activity.token";
        SharedPreferences settings;
        settings = this.getSharedPreferences(
                "com.lymno.cmoney.activity", Context.MODE_PRIVATE);

        String token = settings.getString(tokenKey, "");
        RestClient.get().removeWallet(token, new WalletID(wallet.getWalletID()), new Callback<Void>() {
            @Override
            public void success(Void aVoid, Response response) {
                wallet.delete();
                Intent intent = new Intent(WalletInfo.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(WalletInfo.this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
