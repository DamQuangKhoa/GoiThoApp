package com.goitho.customerapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.demo.architect.data.model.BlogEntity;
import com.goitho.customerapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Skull on 11/12/2017.
 */

public class BlogAdapter extends PagerAdapter {
    Context context;
    List<BlogEntity> list;
    LayoutInflater inflter;
    int[] mResources = {
            R.drawable.img_blog,
            R.drawable.img_blog,
            R.drawable.img_blog
    };
    private final OnItemClickListener listener;

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
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
       View view = inflter.inflate(R.layout.item_stack_blog, null);
        ImageView imgCover = (ImageView) view.findViewById(R.id.img_cover_blog);
        Picasso.with(context).load(mResources[position]).into(imgCover);
        TextView txtTitle = (TextView) view.findViewById(R.id.txt_title);
        txtTitle.setText(list.get(position).getTitle());
        TextView txtDate = (TextView) view.findViewById(R.id.txt_datetime);
        txtDate.setText(list.get(position).getDateCreate());
        TextView txtView = (TextView) view.findViewById(R.id.txt_view);
        txtView.setText(list.get(position).getView() + "");
        TextView txtLike = (TextView) view.findViewById(R.id.txt_like);
        txtLike.setText(list.get(position).getLike() + "");
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //container.removeView((LinearLayout) object);
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }


}