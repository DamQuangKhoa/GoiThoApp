package com.goitho.customerapp.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.demo.architect.data.model.NotificationEntity;
import com.goitho.customerapp.R;

import java.util.List;

/**
 * Created by Skull on 11/12/2017.
 */

public class NotificationAdapter extends BaseAdapter {
    Context context;
    List<NotificationEntity> list;
    LayoutInflater inflter;
    private OnNextItemListener listener;

    public NotificationAdapter(Context applicationContext, List<NotificationEntity> list, OnNextItemListener listener) {
        this.context = applicationContext;
        this.list = list;
        inflter = (LayoutInflater.from(applicationContext));
        this.listener = listener;
    }

    public void setData(List<NotificationEntity> list) {
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
        view = inflter.inflate(R.layout.item_notification, null);
        LinearLayout layout = view.findViewById(R.id.layout_main);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                79);
        layout.setLayoutParams(lp);

        TextView txtTitle = (TextView) view.findViewById(R.id.txt_title);
        txtTitle.setText(list.get(i).getTitle());
        TextView txtContent = (TextView) view.findViewById(R.id.txt_content);
        txtContent.setText(list.get(i).getContent());
        ImageView imgNoti = (ImageView) view.findViewById(R.id.img_noti);
        if (list.get(i).getType() == 1) {
            imgNoti.setVisibility(View.GONE);
        }
        if (list.get(i).getTitle() == null) {
            txtTitle.setVisibility(View.GONE);
        }
        return view;
    }


    public interface OnNextItemListener {
        void onNextItem(int position);
    }


}