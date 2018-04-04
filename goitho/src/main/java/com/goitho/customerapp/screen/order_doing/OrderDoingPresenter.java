package com.goitho.customerapp.screen.order_doing;

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

public class OrderDoingPresenter implements OrderDoingContract.Presenter{

    private final String TAG = OrderDoingPresenter.class.getName();
    private final OrderDoingContract.View view;

    @Inject
    LocalRepository localRepository;

    @Inject
    OrderDoingPresenter(@NonNull OrderDoingContract.View view) {
        this.view = view;

    }

    @Inject
    public void setupPresenter() {
        view.setPresenter(this);
    }


    @Override
    public void start() {
        Log.d(TAG, TAG + ".start() called");
        view.showOrderDoingList(list());
    }

    @Override
    public void stop() {
        Log.d(TAG, TAG + ".stop() called");
    }


    @Override
    public List<OrderEntity> list() {
        List<OrderEntity> list = new ArrayList<>();
        for(int i =0; i< 10; i++){
            list.add(new OrderEntity("Lắp hai điều hoà tầng ba, bốn. " +
                    "Bảo dưỡng một điều hoà tầng 1", "3:30pm, \n 24 th12 2017", 1,
                    0, ""));
        }
        return list;
    }
}
