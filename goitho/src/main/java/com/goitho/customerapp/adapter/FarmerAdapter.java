package com.goitho.customerapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.architect.data.model.FarmerEntity;
import com.goitho.customerapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Skull on 27/11/2017.
 */

public class FarmerAdapter extends RecyclerView.Adapter<FarmerAdapter.FarmerViewHolder>{

    private List<FarmerEntity> list;
    private final OnItemClickListener listener;
    private Context context;

    public FarmerAdapter(List<FarmerEntity> list, Context context, OnItemClickListener listener) {
        this.list = list;
        this.listener = listener;
        this.context = context;
    }

    public void setData(List<FarmerEntity> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public FarmerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_farmer, parent, false);
        FarmerViewHolder holder = new FarmerViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(FarmerAdapter.FarmerViewHolder holder, int position) {
        if (list != null && 0 <= position && position < list.size()) {
            setDataToViews(holder, position);
            holder.bind(list.get(position), listener);
        }

    }

    private void setDataToViews(FarmerViewHolder holder, int position) {
        holder.txtName.setText(list.get(position).getName());
        holder.txtAddress.setText(context.getResources().getString(R.string.text_address) +
                " " + list.get(position).getAddress());
        holder.txtId.setText(list.get(position).getPhone());

        if(!TextUtils.isEmpty(list.get(position).getAvatar())){
            Picasso.with(context).load(list.get(position).getAvatar()).into(holder.imgFarmer);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class FarmerViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName;
        private TextView txtAddress;
        private TextView txtId;
        private CircleImageView imgFarmer;

        private FarmerViewHolder(View v) {
            super(v);
            txtName = (TextView) v.findViewById(R.id.txt_name);
            txtAddress = (TextView) v.findViewById(R.id.txt_address);
            txtId = (TextView) v.findViewById(R.id.txt_id);
            imgFarmer = (CircleImageView) v.findViewById(R.id.img_farmer);


        }

        private void bind(final FarmerEntity item, final OnItemClickListener  listener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(FarmerEntity item);
    }
}
