package com.lymno.cmoney.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.lymno.cmoney.R;
import com.lymno.cmoney.model.WalletOperation;

import java.util.ArrayList;

import butterknife.OnClick;

public class FriendsAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<String> names;

    public FriendsAdapter(Context context, ArrayList<String> names) {
        ctx = context;
        this.names = names;
        lInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // кол-во элементов
    @Override
    public int getCount() {
        return names.size();
    }

    // элемент по позиции
    @Override
    public Object getItem(int position) {
        return names.get(position);
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
            view = lInflater.inflate(R.layout.adapter_friend, parent, false);
        }

        String p = getOperation(position);

        ((TextView) view.findViewById(R.id.friends_adapter_name)).setText(p);
        Button delete = (Button)view.findViewById(R.id.friends_adapter_btn_delete);

        return view;
    }

    @OnClick(R.id.friends_adapter_btn_delete)
    public void delete(){

    }

    // товар по позиции
    String getOperation(int position) {
        return ((String) getItem(position));
    }


}
