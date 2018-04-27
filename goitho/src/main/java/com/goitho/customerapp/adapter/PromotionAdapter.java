package com.goitho.customerapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.architect.data.model.PromotionEntity;
import com.goitho.customerapp.R;
import com.goitho.customerapp.widgets.RoundishImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Skull on 11/12/2017.
 */

public class PromotionAdapter extends RecyclerView.Adapter<PromotionAdapter.PromotionHolder> {

    private List<PromotionEntity> list;
    private Context context;
    private final OnItemClickListener listener;

    public PromotionAdapter(List<PromotionEntity> list, Context context, OnItemClickListener listener) {
        this.list = list;
        this.context = context;
        this.listener = listener;
    }

    public void setData(List<PromotionEntity> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public PromotionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_promotion, parent, false);
        PromotionHolder holder = new PromotionHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(PromotionHolder holder, int position) {
        if (list != null && 0 <= position && position < list.size()) {
            setDataToViews(holder, position);
            holder.bind(list.get(position), listener);
        }

    }

    private void setDataToViews(PromotionHolder holder, int position) {
        Picasso.with(context).load(list.get(position).getImageUrl()).into(holder.imgCover);
        holder.txtTitle.setText(list.get(position).getPromotionName());
        holder.txtDate.setText(list.get(position).getPromotionDate());
        holder.txtIdPromotion.setText(list.get(position).getPromotionContent()+"");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PromotionHolder extends RecyclerView.ViewHolder {

        private RoundishImageView imgCover;
        private TextView txtTitle;
        private TextView txtDate;
        private TextView txtIdPromotion;

        private PromotionHolder(View v) {
            super(v);
            imgCover = (RoundishImageView) v.findViewById(R.id.img_cover);
            txtTitle =  (TextView) v.findViewById(R.id.txt_title);
            txtDate = (TextView) v.findViewById(R.id.txt_expiry_date);
            txtIdPromotion = (TextView) v.findViewById(R.id.txt_id_promotion);
        }

        private void bind(final PromotionEntity item, final OnItemClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(PromotionEntity item);
    }

}