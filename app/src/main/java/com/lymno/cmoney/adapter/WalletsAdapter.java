package com.lymno.cmoney.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.lymno.cmoney.R;
import com.lymno.cmoney.activity.MainActivity;
import com.lymno.cmoney.activity.WalletInfo;
import com.lymno.cmoney.activity.WalletView;
import com.lymno.cmoney.model.Wallet;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class WalletsAdapter extends RecyclerView.Adapter<WalletsAdapter.ViewHolder> {
    Context context;
    private ArrayList<Wallet> walletsData; // these are the things we want to display

    public WalletsAdapter(ArrayList<Wallet> wallets) {
        this.walletsData = wallets; //sorry for my English)))000))
    }


    // Create new views (invoked by the layout manager)
    @Override
    public WalletsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.wallets_adapter, parent, false);
        // create ViewHolder
        return new ViewHolder(itemLayoutView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Wallet wallet = this.walletsData.get(position);
        viewHolder.name.setText(wallet.getName());
        viewHolder.balance.setText("Мой баланс: " + Wallet.moneyFormat(wallet.getBalance()) + " \u20BD");
        viewHolder.goal.setText("Цель: " + wallet.getGoalStatusText());
        viewHolder.daily.setText("Рекомендуемый расход в день: " + Wallet.moneyFormat(wallet.getDaily()) + " \u20BD");
//        viewHolder.goal.setText("Цель: не реализовано");
//        viewHolder.daily.setText("Расход в день: не реализовано");

    }

    //    TODO use this method
    public void updateItems(ArrayList<Wallet> items) {
        this.walletsData = items;
        notifyDataSetChanged();
    }


    // inner class to hold a reference to each item of RecyclerView
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name;
        TextView balance;
        TextView goal;
        TextView daily;
        Button info;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            context = itemLayoutView.getContext();
            name = (TextView) itemLayoutView.findViewById(R.id.wallet_adapter_name);
            balance = (TextView) itemLayoutView.findViewById(R.id.wallet_adapter_balance);
            goal = (TextView) itemLayoutView.findViewById(R.id.wallet_adapter_goal);
            daily = (TextView) itemLayoutView.findViewById(R.id.wallet_adapter_daily);
            info = (Button) itemLayoutView.findViewById(R.id.wallets_adapter_btn_info);
            info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), WalletInfo.class);
                    Wallet wallet = walletsData.get(getAdapterPosition());
                    intent.putExtra("id", wallet.getWalletID());
//        intent.putExtra(WalletView.WALLET_ID, wallet.getWalletID());
                    context.startActivity(intent);
                }
            });

            itemLayoutView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
//            todo add redirecdtion
            Context context = view.getContext();
            Intent intent = new Intent(context, WalletView.class);
            Wallet wallet = walletsData.get(getAdapterPosition());
//
            intent.putExtra(WalletView.WALLET_ID, wallet.getWalletID());
            context.startActivity(intent);
        }
    }




    // Return the size of your itemsData (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return walletsData.size();
    }
}
