package com.goitho.customerapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.demo.architect.data.model.ExhibitionEntity;
import com.demo.architect.data.model.NotificationEntity;
import com.goitho.customerapp.R;

import java.util.List;

/**
 * Created by Skull on 11/12/2017.
 */

public class ExhibitionDoingAdapter extends BaseAdapter {
    Context context;
    List<ExhibitionEntity> list;
    LayoutInflater inflter;
    private OnNextItemListener listener;

    public ExhibitionDoingAdapter(Context applicationContext, List<ExhibitionEntity> list, OnNextItemListener listener) {
        this.context = applicationContext;
        this.list = list;
        inflter = (LayoutInflater.from(applicationContext));
        this.listener = listener;
    }

    public void setData(List<ExhibitionEntity> list) {
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
        view = inflter.inflate(R.layout.item_exhibition_doing, null);
//        LinearLayout layout = view.findViewById(R.id.layout_main);
//        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
//                86);
//        layout.setLayoutParams(lp);

        TextView txtContent = (TextView) view.findViewById(R.id.txt_content);
        txtContent.setText(list.get(i).getContent());
        TextView txtTime = (TextView) view.findViewById(R.id.txt_time);
        txtTime.setText(list.get(i).getTimeEdit());
        ImageView imgNext = (ImageView) view.findViewById(R.id.img_next);
        imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onNextItem(i);
            }
        });
        return view;
    }


    public interface OnNextItemListener {
        void onNextItem(int position);
    }


}