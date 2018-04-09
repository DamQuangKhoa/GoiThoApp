package com.goitho.customerapp.screen.dashboard;

import android.support.annotation.NonNull;
import android.util.Log;

import javax.inject.Inject;

/**
 * Created by Skull on 14/12/2017.
 */

public class DashboardPresenter implements DashboardContract.Presenter{

    private final String TAG = DashboardPresenter.class.getName();
    private final DashboardContract.View view;


    @Inject
    DashboardPresenter(@NonNull DashboardContract.View view) {
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

    @Override
    public void checkLogin() {

    }
}
