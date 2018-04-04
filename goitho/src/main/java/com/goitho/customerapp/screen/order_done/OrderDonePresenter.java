package com.goitho.customerapp.screen.order_done;

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

public class OrderDonePresenter implements com.goitho.customerapp.screen.order_done.OrderDoneContract.Presenter{

    private final String TAG = OrderDonePresenter.class.getName();
    private final com.goitho.customerapp.screen.order_done.OrderDoneContract.View view;

    @Inject
    LocalRepository localRepository;

    @Inject
    OrderDonePresenter(@NonNull com.goitho.customerapp.screen.order_done.OrderDoneContract.View view) {
        this.view = view;

    }

    @Inject
    public void setupPresenter() {
        view.setPresenter(this);
    }


    @Override
    public void start() {
        Log.d(TAG, TAG + ".start() called");
        view.showOrderDoneList(list());
    }

    @Override
    public void stop() {
        Log.d(TAG, TAG + ".stop() called");
    }


    @Override
    public List<OrderEntity> list() {
        List<OrderEntity> list = new ArrayList<>();
        for(int i =0; i< 2; i++){
            list.add(new OrderEntity("Lắp hai điều hoà tầng ba, bốn. " +
                    "Bảo dưỡng một điều hoà tầng 1", "3:30pm, \n 24 th12 2017", 2,
                    3, ""));
        }
        for(int i =0; i< 10; i++){
            list.add(new OrderEntity("Lắp hai điều hoà tầng ba, bốn. " +
                    "Bảo dưỡng một điều hoà tầng 1", "3:30pm, \n 24 th12 2017", 2,
                    0, ""));
        }
        return list;
    }
}
