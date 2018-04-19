package com.goitho.customerapp.screen.order;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.demo.architect.data.model.OrderEntity;
import com.goitho.customerapp.R;
import com.goitho.customerapp.adapter.OrderDoingAdapter;
import com.goitho.customerapp.adapter.OrderDoneAdapter;
import com.goitho.customerapp.app.base.BaseFragment;
import com.goitho.customerapp.screen.detail_order.DetailOrderActivity;
import com.goitho.customerapp.util.Precondition;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Skull on 27/11/2017.
 */

public class OrderFragment extends BaseFragment implements OrderContract.View {
    private static final String TAG = OrderFragment.class.getName();
    private OrderContract.Presenter mPresenter;
    private OrderDoingAdapter doingAdapter;
    private OrderDoneAdapter doneAdapter;

    @Bind(R.id.lv_done)
    ListView lvDone;

    @Bind(R.id.lv_doing)
    ListView lvDoing;

    public OrderFragment() {
        // Required empty public constructor
    }


    public static OrderFragment newInstance() {
        OrderFragment fragment = new OrderFragment();
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
        View view = inflater.inflate(R.layout.fragment_order_main, container, false);
        ButterKnife.bind(this, view);
        initRecyclerView();
        return view;
    }

    private void initRecyclerView() {
        doingAdapter = new OrderDoingAdapter(getContext(), new ArrayList<OrderEntity>(),
                new OrderDoingAdapter.OnNextItemListener() {
                    @Override
                    public void onNextItem(OrderEntity item) {
                        DetailOrderActivity.start(getContext(), item);
                    }
                });
        lvDoing.setAdapter(doingAdapter);

        doneAdapter = new OrderDoneAdapter(getContext(), new ArrayList<OrderEntity>(),
                new OrderDoneAdapter.OnNextItemListener() {
                    @Override
                    public void onNextItem(OrderEntity item) {
                        DetailOrderActivity.start(getContext(), item);
                    }
                });
        lvDone.setAdapter(doneAdapter);

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
    public void setPresenter(OrderContract.Presenter presenter) {
        this.mPresenter = Precondition.checkNotNull(presenter);
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }


    @Override
    public void showOrderDoingList(List<OrderEntity> list) {
        doingAdapter.setData(list);
    }

    @Override
    public void showOrderDoneList(List<OrderEntity> list) {
        doneAdapter.setData(list);
    }
}
