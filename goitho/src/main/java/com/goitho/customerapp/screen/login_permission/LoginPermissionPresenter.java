package com.goitho.customerapp.screen.login_permission;

import android.support.annotation.NonNull;
import android.util.Log;

import com.demo.architect.data.repository.base.local.LocalRepository;

import javax.inject.Inject;

/**
 * Created by MSI on 26/11/2017.
 */

public class LoginPermissionPresenter implements LoginPermissionContract.Presenter{

    private final String TAG = LoginPermissionPresenter.class.getName();
    private final LoginPermissionContract.View view;

    @Inject
    LocalRepository localRepository;

    @Inject
    LoginPermissionPresenter(@NonNull LoginPermissionContract.View view) {
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
