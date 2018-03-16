package com.goitho.customerapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.demo.architect.data.model.ActivityEntity;
import com.demo.architect.data.model.offline.DetailDiaryEntity;
import com.goitho.customerapp.R;
import com.goitho.customerapp.manager.ActionManager;
import com.goitho.customerapp.manager.ProductManager;

import java.text.SimpleDateFormat;
import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by Skull on 29/11/2017.
 */

public class DetailDiaryAdapter extends RecyclerView.Adapter<DetailDiaryAdapter.DetailDiaryViewHolder> {

    private List<ActivityEntity> list;
    private final OnItemClickListener listener;
    //private final OnDeleteClickListener delete;
    private Context context;


    public DetailDiaryAdapter(List<ActivityEntity> list, OnItemClickListener listener,
                              Context context) {
        this.list = list;
        this.listener = listener;
        //this.delete = delete;
        this.context = context;
    }

    public void setData(List<ActivityEntity> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public DetailDiaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_detail_diary, parent, false);
        DetailDiaryViewHolder holder = new DetailDiaryViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(DetailDiaryAdapter.DetailDiaryViewHolder holder, int position) {
        if (list != null && 0 <= position && position < list.size()) {
            setDataToViews(holder, position);
            holder.bind(list.get(position), listener, position);
        }

    }

    private void setDataToViews(DetailDiaryViewHolder holder, int position) {
        holder.txtProduct.setText(ProductManager.getInstance()
                .getNameProductById(list.get(position).getProductId()));

        holder.txtAction.setText(ActionManager.getInstance()
                .getNameProductById(list.get(position).getProductActionId()));

        holder.txtNote.setText(context.getString(R.string.text_note) + ": " + (!TextUtils.isEmpty(list.get(position).getNote())
                ? list.get(position).getNote() : context.getString(R.string.text_no_data)));

        SimpleDateFormat dt = new SimpleDateFormat("hh:mm:ss");
        holder.txtDate.setText(context.getString(R.string.text_time) + ": " + dt.format(list.get(position).getDate()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class DetailDiaryViewHolder extends RecyclerView.ViewHolder {
        private TextView txtProduct;
        private TextView txtAction;
        private TextView txtNote;
        private ImageView imgEdit;
        private ImageView imgDelete;
        private TextView txtHistory;
        private ImageView btnMore;
        private TextView txtDate;
        PopupWindow popupWindow;
        View popupView;

        private DetailDiaryViewHolder(View v) {
            super(v);
            txtProduct = (TextView) v.findViewById(R.id.txt_product);
            txtAction = (TextView) v.findViewById(R.id.txt_action);
            txtNote = (TextView) v.findViewById(R.id.txt_note);
            imgEdit = (ImageView) v.findViewById(R.id.img_edit);
            imgDelete = (ImageView) v.findViewById(R.id.img_delete);
            txtHistory = (TextView) v.findViewById(R.id.txt_history);
            btnMore = (ImageView) v.findViewById(R.id.img_more);
            txtDate = (TextView) v.findViewById(R.id.txt_time);
            LayoutInflater layoutInflater
                    = (LayoutInflater) context.getApplicationContext()
                    .getSystemService(LAYOUT_INFLATER_SERVICE);
            popupView = layoutInflater.inflate(R.layout.popup, null);
            popupWindow = new PopupWindow(
                    popupView,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    true);
        }

        private void bind(final ActivityEntity item, final OnItemClickListener listener, int position) {

//            imgEdit.setOnClickListener(new View.OnClickListener() {
//                @Override public void onClick(View v) {
//                    listener.onItemClick(item, position);
//                }
//            });
//            imgDelete.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    listener.onDeleteClick(item, position);
//                }
//            });
//            txtHistory.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    listener.onHistoryClick(item, position);
//                }
//            });

            btnMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //showPopup(popupWindow, item, listener, position, btnMore);

                    TextView txtDelete = (TextView) popupView.findViewById(R.id.txt_delete);
                    TextView txtEdit = (TextView) popupView.findViewById(R.id.txt_edit);
                    TextView txtHistory = (TextView) popupView.findViewById(R.id.txt_history);
                    txtDelete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            listener.onDeleteClick(item, position);
                            popupWindow.dismiss();
                        }
                    });
                    PopupWindow finalPopupWindow1 = popupWindow;
                    txtEdit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            listener.onItemClick(item, position);
                            popupWindow.dismiss();
                        }
                    });
                    PopupWindow finalPopupWindow2 = popupWindow;
                    txtHistory.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            listener.onHistoryClick(item, position);
                            popupWindow.dismiss();
                        }
                    });

                    if (popupWindow.isShowing()) {
                        popupWindow.dismiss();
                    } else {

                        popupWindow.showAsDropDown(btnMore, 0, -400);
                    }
                }
            });
        }

        private void showPopup(PopupWindow popupWindow, final DetailDiaryEntity item, final OnItemClickListener listener,
                               int position, ImageView btnMore) {

        }
    }

    public interface OnItemClickListener {
        void onItemClick(ActivityEntity item, int position);

        void onHistoryClick(ActivityEntity item, int position);

        void onDeleteClick(ActivityEntity item, int position);
    }

//    public interface OnDeleteClickListener {
//        void onItemClick(DetailDiaryEntity item, int position);
//    }
}
