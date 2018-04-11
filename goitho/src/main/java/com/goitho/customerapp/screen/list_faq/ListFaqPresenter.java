package com.goitho.customerapp.screen.list_faq;

import android.support.annotation.NonNull;
import android.util.Log;

import com.demo.architect.data.repository.base.local.LocalRepository;

import javax.inject.Inject;

/**
 * Created by Skull on 11/12/2017.
 */

public class ListFaqPresenter implements ListFaqContract.Presenter{

    private final String TAG = ListFaqPresenter.class.getName();
    private final ListFaqContract.View view;

    @Inject
    LocalRepository localRepository;

    @Inject
    ListFaqPresenter(@NonNull ListFaqContract.View view) {
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
