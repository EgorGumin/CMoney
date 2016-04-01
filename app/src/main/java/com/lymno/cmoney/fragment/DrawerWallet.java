package com.lymno.cmoney.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lymno.cmoney.R;
import com.lymno.cmoney.adapter.WalletsAdapter;
import com.lymno.cmoney.model.Wallet;
import com.lymno.cmoney.model.WalletOperation;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DrawerWallet extends Fragment {
    @Bind(R.id.swipe_refresh_layout)
    protected SwipeRefreshLayout refreshLayout;

    @Bind(R.id.fab)
    FloatingActionButton fab;

//    private SharedPreferences settings;
//    private String tokenKey = "com.lymno.myfridge.activity.token";
//    private String token;
//    private final Api api = RestClient.get();

    @Bind(R.id.wallets_list_recycler_list)
    protected RecyclerView recyclerView;



    public DrawerWallet() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
//        ArrayList<Wallet> Wallets = Wallet.getAll();
        ArrayList<Wallet> wallets = new ArrayList<>();
        wallets.add(new Wallet(1,"Семья", 30, new ArrayList<String>(), new ArrayList<WalletOperation>()));
        if (wallets != null) {
            WalletsAdapter walletsAdapter = new WalletsAdapter(wallets);
            recyclerView.setAdapter(walletsAdapter);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO: 15.02.2016 проверить корректность работы этого великолепия
        View view = inflater.inflate(R.layout.fragment_wallets, container, false);
        ButterKnife.bind(this, view);
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), ScannerFragmentActivity.class);
//                startActivity(intent);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        settings = getActivity().getSharedPreferences("com.lymno.myfridge.activity", Context.MODE_PRIVATE);
//        token = settings.getString(tokenKey, "");
        //refreshLayout.setColorSchemeColors(R.color.primary);
//        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                api.syncProducts(token, new Callback<ArrayList<Wallet>>() {
//                    @Override
//                    public void success(ArrayList<Wallet> Wallets, Response response) {
//                        refreshLayout.setRefreshing(false);
////                      Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
//                        if (Wallets != null) {
//                            Wallet.recreate(Wallets);
//                            FoodAdapter newAdapter = new FoodAdapter(Wallets);
//                            recyclerView.setAdapter(newAdapter);
//                        } else {
//                            Toast.makeText(getActivity(), "Неправильный тип кода или такого продукта еще нет в базе.", Toast.LENGTH_LONG).show();
//                        }
//                    }
//
//                    @Override
//                    public void failure(RetrofitError error) {
//                        Toast.makeText(getActivity(), error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
//                        refreshLayout.setRefreshing(false);
//                    }
//                });
//            }
//        });

        return view;
    }
}
