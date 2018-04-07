package com.goitho.customerapp.screen.support_center;

import android.support.annotation.NonNull;
import android.util.Log;

import com.demo.architect.data.repository.base.local.LocalRepository;

import javax.inject.Inject;

/**
 * Created by MSI on 26/11/2017.
 */

public class SupportCenterPresenter implements SupportCenterContract.Presenter{

    private final String TAG = SupportCenterPresenter.class.getName();
    private final SupportCenterContract.View view;

    @Inject
    LocalRepository localRepository;

    @Inject
    SupportCenterPresenter(@NonNull SupportCenterContract.View view) {
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
