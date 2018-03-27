package com.goitho.customerapp.screen.order_cancel;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.demo.architect.data.model.OrderEntity;
import com.goitho.customerapp.R;
import com.goitho.customerapp.adapter.OrderCancelAdapter;
import com.goitho.customerapp.app.CoreApplication;
import com.goitho.customerapp.app.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Skull on 27/11/2017.
 */

public class OrderCancelFragment extends BaseFragment implements OrderCancelContract.View {

    @Inject
    OrderCancelPresenter mPresenter;

    @Bind(R.id.lv_order)
    ListView lvOrder;

    private OrderCancelAdapter adapter;
    public OrderCancelFragment() {
        // Required empty public constructor
    }


    public static OrderCancelFragment newInstance() {
        OrderCancelFragment fragment = new OrderCancelFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        configFragments();
        ButterKnife.bind(this, view);
        initListView();
        return view;
    }

    private void configFragments() {
        CoreApplication.getInstance().getApplicationComponent()
                .plus(new OrderCancelModule(this))
                .inject(this);

    }
    private void initListView() {
        adapter = new OrderCancelAdapter(getContext(), new ArrayList<OrderEntity>());
        lvOrder.setAdapter(adapter);
        lvOrder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }


    @Override
    public void setPresenter(OrderCancelContract.Presenter presenter) {
    }

    @Override
    public void showProgressBar() {
        showProgressDialog();
    }

    @Override
    public void hideProgressBar() {
        hideProgressDialog();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.stop();
    }

    @Override
    public void showOrderCancelList(List<OrderEntity> list) {
        adapter.setData(list);
    }
}
