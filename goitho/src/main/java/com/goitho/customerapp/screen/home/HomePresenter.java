package com.goitho.customerapp.screen.home;

import android.support.annotation.NonNull;
import android.util.Log;

import com.demo.architect.data.model.NotificationEntity;
import com.demo.architect.data.repository.base.local.LocalRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by MSI on 26/11/2017.
 */

public class HomePresenter implements HomeContract.Presenter {

    private final String TAG = HomePresenter.class.getName();
    private final HomeContract.View view;

    @Inject
    LocalRepository localRepository;

    @Inject
    HomePresenter(@NonNull HomeContract.View view) {
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
