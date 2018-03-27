package com.goitho.customerapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.demo.architect.data.model.ExhibitionEntity;
import com.goitho.customerapp.R;

import java.util.List;

/**
 * Created by Skull on 11/12/2017.
 */

public class ExhibitionDoneAdapter extends BaseAdapter {
    Context context;
    List<ExhibitionEntity> list;
    LayoutInflater inflter;
    private OnNextItemListener listener;

    public ExhibitionDoneAdapter(Context applicationContext, List<ExhibitionEntity> list, OnNextItemListener listener) {
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
        view = inflter.inflate(R.layout.item_exhibition_done, null);
//        LinearLayout layout = view.findViewById(R.id.layout_main);
//        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
//                86);
//        layout.setLayoutParams(lp);
        TextView txtContent = (TextView) view.findViewById(R.id.txt_content);
        txtContent.setText(list.get(i).getContent());
        View view1 = view.findViewById(R.id.view);
        LinearLayout llEvaluate = view.findViewById(R.id.layout_evaluate);
        LinearLayout llEvaluated = view.findViewById(R.id.layout_evaluated);
        if (list.get(i).getPoint() == 0) {
            view1.setVisibility(View.GONE);
            llEvaluated.setVisibility(View.GONE);
        } else {
            llEvaluate.setVisibility(View.GONE);
        }
        return view;
    }


    public interface OnNextItemListener {
        void onNextItem(int position);
    }


}