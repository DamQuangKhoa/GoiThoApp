package com.goitho.customerapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.architect.data.model.PostEntity;
import com.goitho.customerapp.R;
import com.goitho.customerapp.widgets.CircleTransform;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Skull on 30/11/2017.
 */

public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.BlogHolder> {

    private List<PostEntity> list;
    private Context context;
    private final OnItemClickListener listener;

    public BlogAdapter(List<PostEntity> list, Context context, OnItemClickListener listener) {
        this.list = list;
        this.context = context;
        this.listener = listener;
    }

    public void setData(List<PostEntity> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public BlogHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_blog, parent, false);
        BlogHolder holder = new BlogHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(BlogHolder holder, int position) {
        if (list != null && 0 <= position && position < list.size()) {
            setDataToViews(holder, position);
            holder.bind(list.get(position), listener);
        }

    }

    private void setDataToViews(BlogHolder holder, int position) {
        Picasso.with(context).load(R.drawable.img_item_blog).into(holder.imgCover);
        holder.txtTitle.setText(list.get(position).getTitle());
        holder.txtDate.setText(list.get(position).getDateCreate());
        holder.txtLike.setText(list.get(position).getLike()+"");
        holder.txtView.setText(list.get(position).getView()+"");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class BlogHolder extends RecyclerView.ViewHolder {

        private ImageView imgCover;
        TextView txtTitle;
        TextView txtDate;
        TextView txtView;
        TextView txtLike;

        private BlogHolder(View v) {
            super(v);
            imgCover = (ImageView) v.findViewById(R.id.img_cover);
            txtTitle =  (TextView) v.findViewById(R.id.txt_title);
            txtView = (TextView) v.findViewById(R.id.txt_view);
            txtDate = (TextView) v.findViewById(R.id.txt_datetime);
            txtLike = (TextView) v.findViewById(R.id.txt_like);
        }

        private void bind(final PostEntity item, final BlogAdapter.OnItemClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(PostEntity item);
    }
}
