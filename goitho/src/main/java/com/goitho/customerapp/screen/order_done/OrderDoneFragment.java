package com.goitho.customerapp.screen.order_done;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.demo.architect.data.model.OrderEntity;
import com.goitho.customerapp.R;
import com.goitho.customerapp.adapter.OrderDoneAdapter;
import com.goitho.customerapp.app.CoreApplication;
import com.goitho.customerapp.app.base.BaseFragment;
import com.goitho.customerapp.screen.detail_order.DetailOrderActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Skull on 27/11/2017.
 */

public class OrderDoneFragment extends BaseFragment implements OrderDoneContract.View {

    @Inject
    OrderDonePresenter mPresenter;

    @Bind(R.id.lv_order)
    ListView lvOrder;

    private OrderDoneAdapter adapter;

    public OrderDoneFragment() {
        // Required empty public constructor
    }


    public static OrderDoneFragment newInstance() {
        OrderDoneFragment fragment = new OrderDoneFragment();
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
        adapter = new OrderDoneAdapter(getActivity(), new ArrayList<OrderEntity>(),
                new OrderDoneAdapter.OnNextItemListener() {
                    @Override
                    public void onNextItem(OrderEntity item) {
                        DetailOrderActivity.start(getContext(), item);
                    }
                });

        lvOrder.setAdapter(adapter);


    }

    private void configFragments() {
        CoreApplication.getInstance().getApplicationComponent()
                .plus(new OrderDoneModule(this))
                .inject(this);

    }

    @Override
    public void setPresenter(OrderDoneContract.Presenter presenter) {

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
    public void showOrderDoneList(List<OrderEntity> list) {
        adapter.setData(list);
    }
}