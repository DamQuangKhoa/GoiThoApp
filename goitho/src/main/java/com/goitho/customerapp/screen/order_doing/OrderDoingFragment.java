package com.goitho.customerapp.screen.order_doing;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.demo.architect.data.model.OrderEntity;
import com.goitho.customerapp.R;
import com.goitho.customerapp.adapter.OrderDoingAdapter;
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

public class OrderDoingFragment extends BaseFragment implements OrderDoingContract.View {

    @Inject
    OrderDoingPresenter mPresenter;

    private OrderDoingAdapter adapter;

    @Bind(R.id.lv_order)
    ListView lvOrder;

    public OrderDoingFragment() {
        // Required empty public constructor
    }


    public static OrderDoingFragment newInstance() {
        OrderDoingFragment fragment = new OrderDoingFragment();
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

    private void initListView() {
        adapter = new OrderDoingAdapter(getContext(), new ArrayList<OrderEntity>(),
                new OrderDoingAdapter.OnNextItemListener() {
                    @Override
                    public void onNextItem(int position) {

                    }
                });
        lvOrder.setAdapter(adapter);
        lvOrder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }

    private void configFragments() {
        CoreApplication.getInstance().getApplicationComponent()
                .plus(new OrderDoingModule(this))
                .inject(this);

    }

    @Override
    public void setPresenter(OrderDoingContract.Presenter presenter) {

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
    public void showOrderDoingList(List<OrderEntity> list) {
        adapter.setData(list);
    }
}
