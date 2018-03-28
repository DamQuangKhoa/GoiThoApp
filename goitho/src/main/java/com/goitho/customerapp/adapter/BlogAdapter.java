package com.goitho.customerapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.architect.data.model.BlogEntity;
import com.goitho.customerapp.R;

import java.util.List;

/**
 * Created by Skull on 11/12/2017.
 */

public class BlogAdapter extends BaseAdapter {
    Context context;
    List<BlogEntity> list;
    LayoutInflater inflter;
    private OnItemClickListener listener;

    public BlogAdapter(Context applicationContext, List<BlogEntity> list, OnItemClickListener listener) {
        this.context = applicationContext;
        this.list = list;
        inflter = (LayoutInflater.from(applicationContext));
        this.listener = listener;
    }

    public void setData(List<BlogEntity> list) {
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
        view = inflter.inflate(R.layout.item_stack_blog, null);
        ImageView imgCover = (ImageView) view.findViewById(R.id.img_cover_blog);
        TextView txtTitle = (TextView) view.findViewById(R.id.txt_title);
        txtTitle.setText(list.get(i).getTitle());
        TextView txtDate = (TextView) view.findViewById(R.id.txt_datetime);
        txtDate.setText(list.get(i).getDateCreate());
        TextView txtView = (TextView) view.findViewById(R.id.txt_view);
        txtView.setText(list.get(i).getView() + "");
        TextView txtLike = (TextView) view.findViewById(R.id.txt_like);
        txtView.setText(list.get(i).getLike() + "");
        return view;
    }


    public interface OnItemClickListener {
        void onItemClick(int position);
    }


}