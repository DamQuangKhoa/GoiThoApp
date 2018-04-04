package com.goitho.customerapp.screen.rating;

import android.support.annotation.NonNull;
import android.util.Log;

import com.demo.architect.data.repository.base.local.LocalRepository;

import javax.inject.Inject;

/**
 * Created by MSI on 26/11/2017.
 */

public class RatingPresenter implements RatingContract.Presenter {

    private final String TAG = RatingPresenter.class.getName();
    private final RatingContract.View view;

    @Inject
    LocalRepository localRepository;

    @Inject
    RatingPresenter(@NonNull RatingContract.View view) {
        this.view = view;

    }

    @Inject
    public void setupPresenter() {
        view.setPresenter(this);
    }


    @Override
    public void start() {
        Log.d(TAG, TAG + ".start() called");
        view.showProfileTho();

    }

    @Override
    public void stop() {
        Log.d(TAG, TAG + ".stop() called");
    }


}
