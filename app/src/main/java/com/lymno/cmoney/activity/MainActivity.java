package com.lymno.cmoney.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.lymno.cmoney.R;
import com.lymno.cmoney.fragment.DrawerWallet;
import com.lymno.cmoney.model.MyModel;
import com.lymno.cmoney.model.Wallet;
import com.lymno.cmoney.model.WalletOperation;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerWallet drawerWalletFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        drawerWalletFragment = new DrawerWallet();

        //TODO: 14.02.2016 найти способ поумнее поставить дефолтный фрагмент
        navigationView.setCheckedItem(R.id.menu_drawer_wallets);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.drawer_fragments_container, drawerWalletFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.menu_drawer_wallets) {

        } else if (id == R.id.menu_drawer_purchases) {

        } else if (id == R.id.menu_drawer_settings) {

        }  else if (id == R.id.menu_drawer_log_out) {
            String tokenKey = "com.lymno.cmoney.activity.token";
            SharedPreferences settings;
            settings = this.getSharedPreferences(
                    "com.lymno.cmoney.activity", Context.MODE_PRIVATE);
            settings.edit().putString(tokenKey, "").apply();
//          Очищаем БД
            MyModel.truncate(Wallet.class);
            MyModel.truncate(WalletOperation.class);

            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
