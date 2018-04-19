package com.goitho.customerapp.screen.order_cancel;

import android.support.annotation.NonNull;
import android.util.Log;

import com.demo.architect.data.model.OrderEntity;
import com.demo.architect.data.repository.base.local.LocalRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Skull on 27/11/2017.
 */

public class OrderCancelPresenter implements OrderCancelContract.Presenter{

    private final String TAG = OrderCancelPresenter.class.getName();
    private final OrderCancelContract.View view;

    @Inject
    LocalRepository localRepository;

    @Inject
    OrderCancelPresenter(@NonNull OrderCancelContract.View view) {
        this.view = view;

    }

    @Inject
    public void setupPresenter() {
        view.setPresenter(this);
    }


    @Override
    public void start() {
        Log.d(TAG, TAG + ".start() called");
        view.showOrderCancelList(list());
    }

    @Override
    public void stop() {
        Log.d(TAG, TAG + ".stop() called");
    }


    @Override
    public List<OrderEntity> list() {
        List<OrderEntity> list = new ArrayList<>();
        for(int i =0; i< 10; i++){
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setOrderContent("Lắp hai điều hoà tầng ba, bốn. " +
                    "Bảo dưỡng một điều hoà tầng 1");
            orderEntity.setAcceptanceTime("\"3:30pm, \\n 24 th12 2017\"");
            orderEntity.setStatus(2);
            orderEntity.setRatePoint(3);

            list.add(orderEntity);
        }
        return list;
    }
}
