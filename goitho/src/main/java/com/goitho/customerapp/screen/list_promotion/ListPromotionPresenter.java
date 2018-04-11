package com.goitho.customerapp.screen.list_promotion;

import android.support.annotation.NonNull;
import android.util.Log;

import com.demo.architect.data.repository.base.local.LocalRepository;

import javax.inject.Inject;

/**
 * Created by Skull on 11/12/2017.
 */

public class ListPromotionPresenter implements ListPromotionContract.Presenter{

    private final String TAG = ListPromotionPresenter.class.getName();
    private final ListPromotionContract.View view;

    @Inject
    LocalRepository localRepository;

    @Inject
    ListPromotionPresenter(@NonNull ListPromotionContract.View view) {
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
