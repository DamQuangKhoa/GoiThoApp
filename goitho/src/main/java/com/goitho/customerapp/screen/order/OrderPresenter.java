package com.goitho.customerapp.screen.order;

import android.support.annotation.NonNull;
import android.util.Log;

import com.demo.architect.data.repository.base.local.LocalRepository;
import com.goitho.customerapp.screen.notification.NotificationContract;
import com.goitho.customerapp.screen.notification.NotificationPresenter;

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

    }

    @Override
    public void stop() {
        Log.d(TAG, TAG + ".stop() called");
    }


}
