package com.goitho.customerapp.screen.landing;

import android.support.annotation.NonNull;
import android.util.Log;

import com.demo.architect.data.helper.SharedPreferenceHelper;
import com.demo.architect.data.repository.base.local.LocalRepository;
import com.goitho.customerapp.app.CoreApplication;
import com.goitho.customerapp.manager.UserManager;

import javax.inject.Inject;

/**
 * Created by MSI on 26/11/2017.
 */

public class LandingPresenter implements LandingContract.Presenter{

    private final String TAG = LandingPresenter.class.getName();
    private final LandingContract.View view;

    @Inject
    LocalRepository localRepository;

    @Inject
    LandingPresenter(@NonNull LandingContract.View view) {
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
