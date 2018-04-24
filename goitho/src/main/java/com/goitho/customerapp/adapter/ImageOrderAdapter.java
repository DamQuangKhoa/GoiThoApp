package com.goitho.customerapp.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.goitho.customerapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Skull on 27/11/2017.
 */

public class ImageOrderAdapter extends RecyclerView.Adapter<ImageOrderAdapter.ImageRealViewHolder> {

    private List<Bitmap> list = new ArrayList<>();
    private final OnItemClickListener listener;
    private Context context;

    public ImageOrderAdapter(List<Bitmap> list, Context context, OnItemClickListener listener) {
        this.list.addAll(list);
        this.listener = listener;
        this.context = context;
    }

    public void addData(Bitmap bitmap) {
        list.add(bitmap);
        notifyDataSetChanged();
    }

    public void removeData(Bitmap object) {
        list.remove(object);
        notifyDataSetChanged();
    }

    @Override
    public ImageRealViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_image_order, parent, false);
        ImageRealViewHolder holder = new ImageRealViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ImageRealViewHolder holder, int position) {
        if (list != null && 0 <= position && position < list.size()) {
            setDataToViews(holder, position);
            holder.bind(list.get(position), listener);
        }

    }

    private void setDataToViews(ImageRealViewHolder holder, int position) {

        holder.imgReal.setImageBitmap(list.get(position));


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ImageRealViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgReal;
        private TextView txtClose;

        private ImageRealViewHolder(View v) {
            super(v);
            imgReal = (ImageView) v.findViewById(R.id.img_item);
            txtClose = (TextView) v.findViewById(R.id.txt_close);


        }
        private void bind(final Object item, final OnItemClickListener listener) {

            txtClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        listener.onItemClick( (Bitmap) item);

                }
            });
        }
    }
    public interface OnItemClickListener {
        void onItemClick(Bitmap itemAsABitmap);
    }
    public List<Bitmap> getListAddedBitmaps(){
        List<Bitmap> results = new ArrayList<>();
        for(Object obj : list) {
            if(obj instanceof Bitmap){
                results.add((Bitmap) obj);
            }
        }
        return results;
    }

}
