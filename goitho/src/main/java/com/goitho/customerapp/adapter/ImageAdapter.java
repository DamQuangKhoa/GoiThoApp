package com.goitho.customerapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.architect.data.model.offline.ImageEntity;
import com.goitho.customerapp.R;
import com.goitho.customerapp.widgets.RoundishImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Skull on 30/11/2017.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private ArrayList<ImageEntity> list;
    private Context context;

    public ImageAdapter(ArrayList<ImageEntity> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setData(ArrayList<ImageEntity> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_imageview, parent, false);
        ImageViewHolder holder = new ImageViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ImageAdapter.ImageViewHolder holder, int position) {
        if (list != null && 0 <= position && position < list.size()) {
            setDataToViews(holder, position);
        }

    }

    private void setDataToViews(ImageViewHolder holder, int position) {
        Picasso.with(context).load(R.drawable.image).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {

        private RoundishImageView image;

        private ImageViewHolder(View v) {
            super(v);
            image = (RoundishImageView) v.findViewById(R.id.image);
        }

        private void bind(final ImageEntity item, final ImageAdapter.OnItemClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(ImageEntity item);
    }
}
