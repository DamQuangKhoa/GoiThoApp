package com.goitho.customerapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.demo.architect.data.model.ActivityEntity;
import com.demo.architect.data.model.offline.DiaryEntity;
import com.goitho.customerapp.R;
import com.goitho.customerapp.manager.ProductManager;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Skull on 28/11/2017.
 */

public class DiaryAdapter extends RecyclerView.Adapter<DiaryAdapter.DiaryViewHolder>{

    private List<DiaryEntity> list;
    private final OnItemClickListener listener;
    private Context context;
    boolean isShowProduct = false;

    public DiaryAdapter(List<DiaryEntity> list, Context context, OnItemClickListener listener) {
        this.list = list;
        this.listener = listener;
        this.context = context;
    }



    @Override
    public DiaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_diary, parent, false);
        DiaryViewHolder holder = new DiaryViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(DiaryAdapter.DiaryViewHolder holder, int position) {
        if (list != null && 0 <= position && position < list.size()) {
            setDataToViews(holder, position);
            holder.bind(list.get(position), listener,  position);
        }

    }

    private void setDataToViews(DiaryViewHolder holder, int position) {
        SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
        holder.txtDate.setText( context.getResources().getString(R.string.day) + " " +
                dt.format(list.get(position).getDetail().get(0).getDate()));

        List<ActivityEntity> listActivityEntities = list.get(position).getDetail();
        Map<String, Integer> listAction = new HashMap<>();
        for (ActivityEntity activityEntity : listActivityEntities) {
            if (activityEntity.getActionEntity() != null) {
                if (listAction.containsKey(activityEntity.getActionEntity().getName())) {
                    int numberOfAction = listAction.get(activityEntity.getActionEntity().getName());
                    listAction.remove(activityEntity.getActionEntity().getName());
                    listAction.put(activityEntity.getActionEntity().getName(), numberOfAction + 1);
                } else {
                    listAction.put(activityEntity.getActionEntity().getName(), 1);
                }
            }
        }
        int i = 0;
        holder.llAction1.setVisibility(View.GONE);
        holder.LlAction2.setVisibility(View.GONE);
        for (Map.Entry<String, Integer> action : listAction.entrySet()) {
            if(i == 0){
                holder.txtAction.setText(action.getKey() + " " + action.getValue() + " lần");
            }
            if(i == 1){
                holder.txtAction1.setText(action.getKey() + " " + action.getValue() + " lần");
                holder.llAction1.setVisibility(View.VISIBLE);
            }
            if(i == 2){
                holder.txtAction2.setText(action.getKey() + " " + action.getValue() + " lần");
                holder.LlAction2.setVisibility(View.VISIBLE);
            }
            i++;
        }

        if(!TextUtils.isEmpty(list.get(position).getDetail().get(0).getImages())){
            String[] splitedStrings = list.get(position).getDetail().get(0).getImages().split(",");
            if (splitedStrings.length == 1) {
                Picasso.with(context).load(list.get(position).getDetail().get(0).getImages()).into(holder.imgDiary);
            } else{
                Picasso.with(context).load(splitedStrings[0]).into(holder.imgDiary);
            }
        }

        if(isShowProduct) {
            holder.txtProduct.setText(ProductManager.getInstance().getNameProductById(
                    list.get(position).getDetail().get(0).getProductId()));
            holder.txtProduct.setVisibility(View.VISIBLE);
        } else {
            holder.txtProduct.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public void setData(List<DiaryEntity> list) {
        this.list = list;
        isShowProduct = false;
        notifyDataSetChanged();
    }

    public void setDataByProduct(List<DiaryEntity> list) {
        this.list = list;
        isShowProduct = true;
        notifyDataSetChanged();
    }

    public class DiaryViewHolder extends RecyclerView.ViewHolder {
        private TextView txtDate;
        private TextView txtAction;
        private TextView txtAction1;
        private TextView txtAction2;
        private LinearLayout llAction1;
        private LinearLayout LlAction2;
        private ImageView imgDiary;
        private TextView txtProduct;

        private DiaryViewHolder(View v) {
            super(v);
            txtDate = (TextView) v.findViewById(R.id.txt_date);
            txtAction = (TextView) v.findViewById(R.id.txt_action);
            txtAction1 = (TextView) v.findViewById(R.id.txt_action1);
            txtAction2 = (TextView) v.findViewById(R.id.txt_action2);
            llAction1 = (LinearLayout) v.findViewById(R.id.ll_action1) ;
            LlAction2 = (LinearLayout) v.findViewById(R.id.ll_action2);
            imgDiary = (ImageView) v.findViewById(R.id.img_diary);
            txtProduct = (TextView) v.findViewById(R.id.txt_product);
        }

        private void bind(final DiaryEntity item, final OnItemClickListener  listener, int position) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item, position);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(DiaryEntity item, int position);
    }
}
