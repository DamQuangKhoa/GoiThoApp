package com.goitho.customerapp.screen.user;

import android.support.annotation.NonNull;
import android.util.Log;

import com.demo.architect.data.repository.base.local.LocalRepository;

import javax.inject.Inject;

/**
 * Created by MSI on 26/11/2017.
 */

public class UserPresenter implements UserContract.Presenter {

    private final String TAG = UserPresenter.class.getName();
    private final UserContract.View view;

    @Inject
    LocalRepository localRepository;

    @Inject
    UserPresenter(@NonNull UserContract.View view) {
        this.view = view;
    }

    @Inject
    public void setupPresenter() {
        view.setPresenter(this);
    }


    @Override
    public void start() {
        Log.d(TAG, TAG + ".start() called");
        view.showContent();

    }

    @Override
    public void stop() {
        Log.d(TAG, TAG + ".stop() called");
    }


}
