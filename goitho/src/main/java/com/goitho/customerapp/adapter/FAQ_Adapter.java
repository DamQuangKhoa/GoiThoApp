package com.goitho.customerapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.goitho.customerapp.R;

import java.util.List;

/**
 * Created by Admin on 4/1/2018.
 */

public class FAQ_Adapter extends BaseAdapter {
	Context context=null;
	List myArray=null;
	int layoutId;
	LayoutInflater inflater;

	public FAQ_Adapter(Context applicationContext, List list) {

		this.context = applicationContext;
		this.myArray = list;
		inflater = (LayoutInflater.from(applicationContext));
		makeLog(myArray.size()+" ");
	}
	public void makeLog(String message){
		Log.e("ListFaqFragment",message);
	}
	@Override
	public int getCount() {
		return myArray.size();
	}

	@Override
	public Object getItem(int i) {
		return myArray.get(i);
	}

	@Override
	public long getItemId(int i) {
		return 0;
	}

	@Override
	public View getView(int i, View view, ViewGroup viewGroup) {
		
		view=inflater.inflate(R.layout.item_faq, null);


		ImageButton btnAdd = view.findViewById(R.id.btnLeft);
		btnAdd.setOnClickListener(p -> {
			Toast.makeText(context, "Hello world", Toast.LENGTH_SHORT).show();
		});
		return view;
	}


}
