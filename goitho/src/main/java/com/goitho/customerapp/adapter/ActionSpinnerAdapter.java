package com.goitho.customerapp.adapter;

import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.goitho.customerapp.R;

import java.util.ArrayList;

/**
 * Created by Skull on 11/12/2017.
 */

public class ActionSpinnerAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> list;
    LayoutInflater inflter;
    private OnEditItemListener listener;
    private OnAddItemListener addListener;

    public ActionSpinnerAdapter(Context applicationContext, ArrayList<String> list, OnEditItemListener listener,
                                OnAddItemListener addListener) {
        this.context = applicationContext;
        this.list = list;
        inflter = (LayoutInflater.from(applicationContext));
        this.listener = listener;
        this.addListener = addListener;
//        if(this.list.get(list.size()-1) != null){
//            list.add(null);
//        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.spinner_item, null);
//        ImageView icon = (ImageView) view.findViewById(R.id.image);
        TextView names = (TextView) view.findViewById(R.id.text);
        names.setText(list.get(i));


//        icon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                listener.onEditItem(list.get(i));
//                View root = viewGroup.getRootView();
//                root.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
//                root.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_BACK));
//            }
//        });
        return view;
    }


    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (list.get(position) == null) {
            Button b = new Button(context);
            b.setText(context.getResources().getString(R.string.text_add_product));
            b.setTextColor(context.getResources().getColor(R.color.brown));
            b.setBackgroundColor(context.getResources().getColor(R.color.background));
            b.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
            b.setAllCaps(false);
            b.setLayoutParams (new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            b.setGravity(Gravity.CENTER);
            b.setBackgroundColor(context.getResources().getColor(R.color.transparent));
            b.setCompoundDrawablesWithIntrinsicBounds( R.drawable.drawable_plus, 0, 0, 0);
            b.setCompoundDrawablePadding(10);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addListener.onAddItem();
                    View root = parent.getRootView();
                    root.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
                    root.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_BACK));
                }
            });
            return b;
        } else {
            convertView = inflter.inflate(R.layout.spinner_item_dropdown, null);
            ImageView icon = (ImageView) convertView.findViewById(R.id.image);
            TextView names = (TextView) convertView.findViewById(R.id.text);
            names.setText(list.get(position));


            icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onEditItem(list.get(position));
                    View root = parent.getRootView();
                    root.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
                    root.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_BACK));
                }
            });
            return convertView;
        }
        //return super.getDropDownView(position, null, parent);
    }


    public interface OnEditItemListener {
        void onEditItem(String item);
    }

    public interface OnAddItemListener {
        void onAddItem();
    }
    class ItemOnClickListener implements View.OnClickListener {
        private View _parent;

        public ItemOnClickListener(ViewGroup parent) {
            _parent = parent;
        }

        @Override
        public void onClick(View view) {
            //.......
            // close the dropdown
            View root = _parent.getRootView();
            root.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
            root.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_BACK));
        }
    }
}