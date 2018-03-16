package com.goitho.customerapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.goitho.customerapp.R;

import java.util.ArrayList;

/**
 * Created by Skull on 26/12/2017.
 */

public class ProductSpinnerAdapter extends BaseAdapter {
    LayoutInflater inflator;
    ArrayList<String> list;
    Context context;

    public ProductSpinnerAdapter(Context context , ArrayList<String> counting)
    {
        this.context = context;
        inflator = LayoutInflater.from(context);
        list =counting;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        convertView = inflator.inflate(R.layout.spinner_item, null);
        TextView tv = (TextView) convertView.findViewById(R.id.text);
        tv.setText(list.get(position));
        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        convertView = inflator.inflate(R.layout.spinner_item_dropdown, null);
        TextView tv = (TextView) convertView.findViewById(R.id.text);
        tv.setText(list.get(position));
        return convertView;
    }
}