package com.lymno.cmoney.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lymno.cmoney.R;
import com.lymno.cmoney.model.export.LoginData;
import com.lymno.cmoney.model.imported.Token;
import com.lymno.cmoney.network.RestClient;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LoginActivity extends AppCompatActivity {
    @Bind(R.id.login_login_manual_btn)
    Button login_btn;
    @Bind(R.id.login_email)
    EditText email;
    @Bind(R.id.login_password)
    EditText password;

    String tokenKey = "com.lymno.cmoney.activity.token";
    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        settings = this.getSharedPreferences(
                "com.lymno.cmoney.activity", Context.MODE_PRIVATE);

        String token = settings.getString(tokenKey, "");
        if (!token.isEmpty()) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }

    }

    @OnClick(R.id.login_login_manual_btn)
    public void manualLogin(){
        LoginData user = new LoginData(email.getText().toString(), password.getText().toString());
        RestClient.get().entrance(user, new Callback<Token>() {
            @Override
            public void success(Token token, Response response) {
                settings.edit().putString(tokenKey, token.getAccessToken()).apply();
                settings.edit().putString("login", email.getText().toString()).apply();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(LoginActivity.this, error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
