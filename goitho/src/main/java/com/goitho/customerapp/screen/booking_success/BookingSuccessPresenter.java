package com.goitho.customerapp.screen.booking_success;

import android.support.annotation.NonNull;
import android.util.Log;

import com.demo.architect.data.helper.SharedPreferenceHelper;
import com.demo.architect.data.repository.base.local.LocalRepository;
import com.goitho.customerapp.app.CoreApplication;
import com.goitho.customerapp.constants.Constants;

import javax.inject.Inject;

/**
 * Created by MSI on 26/11/2017.
 */

public class BookingSuccessPresenter implements BookingSuccessContract.Presenter{

    private final String TAG = BookingSuccessPresenter.class.getName();
    private final BookingSuccessContract.View view;


    @Inject
    LocalRepository localRepository;

    @Inject
    BookingSuccessPresenter(@NonNull BookingSuccessContract.View view) {
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
