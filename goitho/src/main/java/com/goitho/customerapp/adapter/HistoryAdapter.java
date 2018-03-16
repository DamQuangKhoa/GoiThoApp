package com.goitho.customerapp.adapter;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.demo.architect.data.model.ActivityEntity;
import com.demo.architect.data.model.offline.ImageEntity;
import com.goitho.customerapp.R;
import com.goitho.customerapp.manager.ActionManager;
import com.goitho.customerapp.manager.EmployeeManager;
import com.goitho.customerapp.manager.FarmerManager;
import com.goitho.customerapp.manager.ProductManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Skull on 14/12/2017.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private List<ActivityEntity> list;
    //private final OnItemClickListener listener;
    private Context context;

    public HistoryAdapter(List<ActivityEntity> list, Context context) {
        this.list = list;
        this.context = context;
    }

//    public HistoryAdapter(ArrayList<ActivityEntity> list, Context context, OnItemClickListener listener) {
//        this.list = list;
//        this.listener = listener;
//        this.context = context;
//    }

    public void setData(List<ActivityEntity> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void addData(List<ActivityEntity> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void addData(ActivityEntity entity) {
        this.list.add(entity);
        notifyDataSetChanged();
    }

    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_history, parent, false);
        HistoryViewHolder holder = new HistoryViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(HistoryAdapter.HistoryViewHolder holder, int position) {
        if (list != null && 0 <= position && position < list.size()) {
            setDataToViews(holder, position);
            //holder.bind(list.get(position), listener,  position);
            holder.bind(list.get(position), position);
        }

    }

    private void setDataToViews(HistoryViewHolder holder, int position) {
        if (position + 1 == list.size())
            holder.llRoot.setVisibility(View.GONE);
        if (list.size() > 1) {
            if (position + 1 < list.size()) {
                holder.llRoot.setVisibility(View.VISIBLE);
                SimpleDateFormat dt = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");
                holder.txtDate.setText(dt.format(list.get(position).getUpdatedDate()));

                if (!TextUtils.isEmpty(list.get(position).getEditor())) {
                    if (!TextUtils.isEmpty(EmployeeManager.getInstance()
                            .getNameEmployeeById(list.get(position).getEditor()))) {
                        holder.txtUser.setText(EmployeeManager.getInstance()
                                .getNameEmployeeById(list.get(position).getEditor()) + " " +
                                context.getResources().getString(R.string.text_updated));
                    }


                    if (!TextUtils.isEmpty(FarmerManager.getInstance()
                            .getNameFarmerById(list.get(position).getEditor()))) {
                        holder.txtUser.setText(FarmerManager.getInstance()
                                .getNameFarmerById(list.get(position).getEditor()) + " " +
                                context.getResources().getString(R.string.text_updated));
                    }

                } else {
                    holder.txtUser.setText(" " + context.getResources().getString(R.string.text_updated));
                }

                if (!list.get(position).getProductId().equals(list.get(position + 1).getProductId())) {
                    holder.txtProductTitle.setVisibility(View.VISIBLE);
                    holder.rlProduct.setVisibility(View.VISIBLE);
                    holder.txtProductOld.setText(ProductManager.getInstance().getNameProductById(list.get(position + 1)
                            .getProductId()));

                    holder.txtProductNew.setText(ProductManager.getInstance().getNameProductById(list.get(position)
                            .getProductId()));
                } else {
                    holder.txtProductTitle.setVisibility(View.GONE);
                    holder.rlProduct.setVisibility(View.GONE);
                }

                if (!list.get(position).getProductActionId().equals(list.get(position + 1).getProductActionId())) {
                    holder.txtActionTitle.setVisibility(View.VISIBLE);
                    holder.rlAction.setVisibility(View.VISIBLE);
                    holder.txtActionOld.setText(ActionManager.getInstance().getNameProductById(
                            list.get(position + 1).getProductActionId()));

                    holder.txtActionNew.setText(ActionManager.getInstance().getNameProductById(
                            list.get(position).getProductActionId()));
                } else {
                    holder.txtActionTitle.setVisibility(View.GONE);
                    holder.rlAction.setVisibility(View.GONE);
                }

                if (!list.get(position).getNote().equals(list.get(position + 1).getNote())) {
                    holder.txtNoteTitle.setVisibility(View.VISIBLE);
                    holder.rlNote.setVisibility(View.VISIBLE);
                    if (TextUtils.isEmpty(list.get(position + 1).getNote())) {
                        holder.txtNoteOld.setText(context.getResources().getString(R.string.text_note_empty));
                    } else {
                        holder.txtNoteOld.setText(list.get(position + 1).getNote());
                    }
                    if (TextUtils.isEmpty(list.get(position).getNote())) {
                        holder.txtNoteNew.setText(context.getResources().getString(R.string.text_note_empty));
                    } else {
                        holder.txtNoteNew.setText(list.get(position).getNote());
                    }
                } else {
                    holder.txtNoteTitle.setVisibility(View.GONE);
                    holder.rlNote.setVisibility(View.GONE);
                }

                if (!list.get(position).getImages().equals(list.get(position + 1).getImages())) {
                    holder.txtImageTitle.setVisibility(View.VISIBLE);
                    holder.rlImage.setVisibility(View.VISIBLE);
                    if (TextUtils.isEmpty(list.get(position + 1).getImages())) {
                        holder.txtImageOld.setVisibility(View.VISIBLE);
                        holder.rvOld.setVisibility(View.GONE);
                    } else {
                        holder.txtImageOld.setVisibility(View.GONE);
                        holder.rvOld.setVisibility(View.VISIBLE);
                        configRecyclerView(holder.rvOld, list.get(position + 1).getImages());
                    }
                    if (TextUtils.isEmpty(list.get(position).getImages())) {
                        holder.txtImageNew.setVisibility(View.VISIBLE);
                        holder.rvNew.setVisibility(View.GONE);
                    } else {
                        holder.txtImageNew.setVisibility(View.GONE);
                        holder.rvNew.setVisibility(View.VISIBLE);
                        configRecyclerView(holder.rvNew, list.get(position).getImages());
                    }
                } else {
                    holder.txtImageTitle.setVisibility(View.GONE);
                    holder.rlImage.setVisibility(View.GONE);
                }

            }
        }
    }

    private void configRecyclerView(RecyclerView rv, String images) {
        ArrayList<ImageEntity> listImage = new ArrayList<>();
        for (String string : images.split(",")) {
            listImage.add(new ImageEntity(string));
        }
        ImageSmallAdapter adapter = new ImageSmallAdapter(listImage, context,
                new ImageSmallAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(ImageEntity item) {
                    }
                });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL, false);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(layoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder {
        private TextView txtProductOld;
        private TextView txtProductNew;
        private TextView txtProductTitle;
        private RelativeLayout rlProduct;

        private TextView txtActionOld;
        private TextView txtActionNew;
        private TextView txtActionTitle;
        private RelativeLayout rlAction;

        private TextView txtNoteOld;
        private TextView txtNoteNew;
        private TextView txtNoteTitle;
        private RelativeLayout rlNote;

        private TextView txtImageOld;
        private TextView txtImageNew;
        private RecyclerView rvOld;
        private RecyclerView rvNew;
        private TextView txtImageTitle;
        private RelativeLayout rlImage;

        private TextView txtUser;
        private TextView txtDate;

        private LinearLayout llRoot;

        private HistoryViewHolder(View v) {
            super(v);
            txtProductOld = (TextView) v.findViewById(R.id.txt_product_old);
            txtProductNew = (TextView) v.findViewById(R.id.txt_product_new);
            txtProductTitle = (TextView) v.findViewById(R.id.txt_title_product);
            rlProduct = (RelativeLayout) v.findViewById(R.id.rl_product);

            txtActionOld = (TextView) v.findViewById(R.id.txt_action_old);
            txtActionNew = (TextView) v.findViewById(R.id.txt_action_new);
            txtActionTitle = (TextView) v.findViewById(R.id.txt_title_action);
            rlAction = (RelativeLayout) v.findViewById(R.id.rl_action);

            txtNoteOld = (TextView) v.findViewById(R.id.txt_note_old);
            txtNoteNew = (TextView) v.findViewById(R.id.txt_note_new);
            txtNoteTitle = (TextView) v.findViewById(R.id.txt_title_note);
            rlNote = (RelativeLayout) v.findViewById(R.id.rl_note);

            txtImageOld = (TextView) v.findViewById(R.id.txt_image_old);
            txtImageNew = (TextView) v.findViewById(R.id.txt_image_new);
            rvOld = (RecyclerView) v.findViewById(R.id.rv_old);
            rvNew = (RecyclerView) v.findViewById(R.id.rv_new);
            txtImageTitle = (TextView) v.findViewById(R.id.txt_title_image);
            rlImage = (RelativeLayout) v.findViewById(R.id.rl_image);

            txtUser = (TextView) v.findViewById(R.id.txt_user);
            txtDate = (TextView) v.findViewById(R.id.txt_date);

            llRoot = (LinearLayout) v.findViewById(R.id.ll_root);

        }

//        private void bind(final ActivityEntity item, final OnItemClickListener  listener, int position) {
//
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override public void onClick(View v) {
//                    listener.onItemClick(item, position);
//                }
//            });
//        }

        private void bind(final ActivityEntity item, int position) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(ActivityEntity item, int position);
    }
}
