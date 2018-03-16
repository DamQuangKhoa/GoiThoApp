package com.goitho.customerapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.demo.architect.data.model.offline.ImageEntity;
import com.goitho.customerapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Skull on 30/11/2017.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder>{

    private ArrayList<ImageEntity> list;
    private final ImageAdapter.OnItemClickListener listener;
    private Context context;

    public ImageAdapter(ArrayList<ImageEntity> list, Context context, ImageAdapter.OnItemClickListener listener) {
        this.list = list;
        this.listener = listener;
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
            holder.bind(list.get(position), listener);
        }

    }

    private void setDataToViews(ImageViewHolder holder, int position) {

        if(!TextUtils.isEmpty(list.get(position).getUri())){
            Picasso.with(context).load(list.get(position).getUri()).into(holder.image);
        }else {
            if(list.get(position).getBitmap() != null){
                holder.image.setImageBitmap(list.get(position).getBitmap());
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;

        private ImageViewHolder(View v) {
            super(v);
            image = (ImageView) v.findViewById(R.id.image);
        }

        private void bind(final ImageEntity item, final ImageAdapter.OnItemClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(ImageEntity item);
    }
}
