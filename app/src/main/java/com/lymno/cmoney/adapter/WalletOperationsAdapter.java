package com.lymno.cmoney.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lymno.cmoney.R;
import com.lymno.cmoney.model.WalletOperation;

import java.util.ArrayList;

public class WalletOperationsAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<WalletOperation> operations;

    public WalletOperationsAdapter(Context context, ArrayList<WalletOperation> operations) {
        ctx = context;
        this.operations = operations;
        lInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // кол-во элементов
    @Override
    public int getCount() {
        return operations.size();
    }

    // элемент по позиции
    @Override
    public Object getItem(int position) {
        return operations.get(position);
    }

    // id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }

    // пункт списка
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // используем созданные, но не используемые view
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.wallet_operation, parent, false);
        }

        WalletOperation p = getOperation(position);

        ((TextView) view.findViewById(R.id.tvDescr)).setText(p.getName());
        ((TextView) view.findViewById(R.id.wallet_view_name_date))
                .setText(p.getLogin() + ", " + p.getTimeText() + " " + p.getDateText());
        ((TextView) view.findViewById(R.id.wallet_view_sum)).setText(p.getSumText());


        return view;
    }

    // товар по позиции
    WalletOperation getOperation(int position) {
        return ((WalletOperation) getItem(position));
    }


}
