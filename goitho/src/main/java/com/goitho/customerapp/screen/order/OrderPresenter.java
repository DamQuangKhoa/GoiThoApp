package com.goitho.customerapp.screen.order;

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

public class OrderPresenter implements OrderContract.Presenter {

    private final String TAG = OrderPresenter.class.getName();
    private final OrderContract.View view;

    @Inject
    LocalRepository localRepository;

    @Inject
    OrderPresenter(@NonNull OrderContract.View view) {
        this.view = view;

    }

    @Inject
    public void setupPresenter() {
        view.setPresenter(this);
    }


    @Override
    public void start() {
        Log.d(TAG, TAG + ".start() called");
        view.showOrderDoingList(orderDoingList());
        view.showOrderDoneList(orderDoneList());
    }

    @Override
    public void stop() {
        Log.d(TAG, TAG + ".stop() called");
    }


    @Override
    public List<OrderEntity> orderDoneList() {
        List<OrderEntity> list = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setOrderContent("Lắp hai điều hoà tầng ba, bốn. " +
                    "Bảo dưỡng một điều hoà tầng 1");
            orderEntity.setAcceptanceTime("3:30pm, \n 24 th12 2017");
            orderEntity.setStatus(2);
            orderEntity.setRatePoint(3);
            list.add(orderEntity);
        }
        for (int i = 0; i < 10; i++) {
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setOrderContent("Lắp hai điều hoà tầng ba, bốn. " +
                    "Bảo dưỡng một điều hoà tầng 1");
            orderEntity.setAcceptanceTime("3:30pm, \n 24 th12 2017");
            orderEntity.setStatus(2);
            orderEntity.setRatePoint(0);
            list.add(orderEntity);
        }
        return list;
    }

    @Override
    public List<OrderEntity> orderDoingList() {
        List<OrderEntity> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setOrderContent("Lắp hai điều hoà tầng ba, bốn. " +
                    "Bảo dưỡng một điều hoà tầng 1");
            orderEntity.setAcceptanceTime("3:30pm, \n 24 th12 2017");
            orderEntity.setStatus(1);
            list.add(orderEntity);
        }
        return list;
    }
}
