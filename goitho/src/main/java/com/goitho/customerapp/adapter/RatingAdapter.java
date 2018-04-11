package com.goitho.customerapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.demo.architect.data.model.RatingEntity;
import com.goitho.customerapp.R;
import com.goitho.customerapp.widgets.CircleTransform;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Skull on 30/11/2017.
 */

public class RatingAdapter extends RecyclerView.Adapter<RatingAdapter.RatingHolder> {

    private ArrayList<RatingEntity> list;
    private Context context;

    public RatingAdapter(ArrayList<RatingEntity> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setData(ArrayList<RatingEntity> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public RatingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rating, parent, false);
        RatingHolder holder = new RatingHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RatingHolder holder, int position) {
        if (list != null && 0 <= position && position < list.size()) {
            setDataToViews(holder, position);
        }

    }

    private void setDataToViews(RatingHolder holder, int position) {
        Picasso.with(context).load(R.drawable.image).transform(new CircleTransform()).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RatingHolder extends RecyclerView.ViewHolder {

        private ImageView image;

        private RatingHolder(View v) {
            super(v);
            image = (ImageView) v.findViewById(R.id.img_avatar);
        }


    }


}
