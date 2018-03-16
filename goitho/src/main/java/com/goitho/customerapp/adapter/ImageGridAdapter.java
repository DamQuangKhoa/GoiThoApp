package com.goitho.customerapp.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.demo.architect.data.model.offline.ImageEntity;
import com.goitho.customerapp.R;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Skull on 26/12/2017.
 */

public class ImageGridAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ImageEntity> list;
    private OnItemClickListener listener;

    public ImageGridAdapter(Context context, ArrayList<ImageEntity> list, OnItemClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
        if(list.size() == 0) {
            list.add(null);
        } else {
            if (list.get(list.size() - 1) != null) {
                list.add(null);
            }
        }
    }

    public void setData(ArrayList<ImageEntity> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View grid;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (list.get(position) != null) {
            if (null == convertView) {
                convertView = inflater.inflate(R.layout.item_grid_image, parent, false);
            }
            if (!TextUtils.isEmpty(list.get(position).getUri())) {
                RoundedImageView image = convertView.findViewById(R.id.image);
                Picasso.with(context)
                        .load(list.get(position).getUri())
                        .fit()
                        .into(image);
            } else {
                if (list.get(position).getBitmap() != null) {
                    RoundedImageView image = convertView.findViewById(R.id.image);
                    image.setImageBitmap(list.get(position).getBitmap());
                }
            }

        } else {
//                ((RoundedImageView)convertView).setImageResource(R.drawable.ic_add_green);
//                ((RoundedImageView)convertView).setPadding(20, 20, 20, 20);
            convertView = inflater.inflate(R.layout.item_grid_last, parent, false);
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(list.get(position));
            }
        });

        return convertView;
    }

    public interface OnItemClickListener {
        void onItemClick(ImageEntity item);
    }
}