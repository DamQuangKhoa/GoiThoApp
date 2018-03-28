package com.goitho.customerapp.screen.order_repair;

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

public class OrderRepairPresenter implements OrderRepairContract.Presenter{

    private final String TAG = OrderRepairPresenter.class.getName();
    private final OrderRepairContract.View view;

    @Inject
    LocalRepository localRepository;

    @Inject
    OrderRepairPresenter(@NonNull OrderRepairContract.View view) {
        this.view = view;

    }

    @Inject
    public void setupPresenter() {
        view.setPresenter(this);
    }


    @Override
    public void start() {
    }

    @Override
    public void stop() {
        Log.d(TAG, TAG + ".stop() called");
    }


}
