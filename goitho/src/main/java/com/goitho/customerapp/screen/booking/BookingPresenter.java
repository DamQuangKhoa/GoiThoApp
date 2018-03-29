package com.goitho.customerapp.screen.booking;

import android.support.annotation.NonNull;
import android.util.Log;

import com.demo.architect.data.repository.base.local.LocalRepository;

import javax.inject.Inject;

/**
 * Created by Skull on 27/11/2017.
 */

public class BookingPresenter implements BookingContract.Presenter{

    private final String TAG = BookingPresenter.class.getName();
    private final BookingContract.View view;

    @Inject
    LocalRepository localRepository;

    @Inject
    BookingPresenter(@NonNull BookingContract.View view) {
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
