package com.goitho.customerapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.demo.architect.data.model.OrderEntity;
import com.goitho.customerapp.R;

import java.util.List;

/**
 * Created by Skull on 11/12/2017.
 */

public class OrderDoingAdapter extends BaseAdapter {
    Context context;
    List<OrderEntity> list;
    LayoutInflater inflter;
    private OnNextItemListener listener;

    public OrderDoingAdapter(Context applicationContext, List<OrderEntity> list, OnNextItemListener listener) {
        this.context = applicationContext;
        this.list = list;
        inflter = (LayoutInflater.from(applicationContext));
        this.listener = listener;
    }

    public void setData(List<OrderEntity> list) {
        this.list = list;
        notifyDataSetChanged();
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.item_order_doing, null);
        LinearLayout layout = view.findViewById(R.id.layout_main);

        TextView txtContent = (TextView) view.findViewById(R.id.txt_content);
        txtContent.setText(list.get(i).getOrderContent());
        TextView txtTime = (TextView) view.findViewById(R.id.txt_time);
        txtTime.setText(list.get(i).getAcceptanceTime());
        ImageView imgNext = (ImageView) view.findViewById(R.id.img_next);
        imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onNextItem(list.get(i));
            }
        });
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onNextItem(list.get(i));
            }
        });
        return view;
    }


    public interface OnNextItemListener {
        void onNextItem(OrderEntity item);
    }


}