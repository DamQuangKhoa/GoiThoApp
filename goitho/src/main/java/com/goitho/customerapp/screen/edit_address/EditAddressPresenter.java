package com.goitho.customerapp.screen.edit_address;

import android.support.annotation.NonNull;
import android.util.Log;

import com.demo.architect.data.repository.base.local.LocalRepository;

import javax.inject.Inject;

/**
 * Created by Skull on 29/11/2017.
 */

public class EditAddressPresenter implements EditAddressContract.Presenter {

    private final String TAG = EditAddressPresenter.class.getName();
    private final EditAddressContract.View view;

    @Inject
    LocalRepository localRepository;

    @Inject
    EditAddressPresenter(@NonNull EditAddressContract.View view) {
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
