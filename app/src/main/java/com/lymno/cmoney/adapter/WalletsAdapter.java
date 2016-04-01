package com.lymno.cmoney.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lymno.cmoney.R;
import com.lymno.cmoney.model.Wallet;

import java.util.ArrayList;

public class WalletsAdapter extends RecyclerView.Adapter<WalletsAdapter.ViewHolder> {

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
        Wallet walletsData = this.walletsData.get(position);
        //TODO исправить отображение продукта
        viewHolder.FoodName.setText(walletsData.getName());
    }

    //    TODO use this method
    public void updateItems(ArrayList<Wallet> items) {
        this.walletsData = items;
        notifyDataSetChanged();
    }


    // inner class to hold a reference to each item of RecyclerView
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView FoodName;
        public TextView FoodEatLetfTime;
        public TextView tvDate;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            FoodName = (TextView) itemLayoutView.findViewById(R.id.wallet_adapter_name);
            itemLayoutView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Context context = view.getContext();
//            Intent questInfoIntent = new Intent(context, FoodInfoActivity.class);
//            Wallet product = walletsData.get(getAdapterPosition());
//
//            questInfoIntent.putExtra(FoodInfoActivity.INTENT_PRODUCT_ID, product.getId());
//            context.startActivity(questInfoIntent);
        }
    }

    // Return the size of your itemsData (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return walletsData.size();
    }
}
