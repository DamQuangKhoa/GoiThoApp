package com.goitho.customerapp.screen.splash;

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

public class SplashPresenter implements SplashContract.Presenter{

    private final String TAG = SplashPresenter.class.getName();
    private final SplashContract.View view;

    @Inject
    LocalRepository localRepository;

    @Inject
    SplashPresenter(@NonNull SplashContract.View view) {
        this.view = view;
    }

    @Inject
    public void setupPresenter() {
        view.setPresenter(this);
    }


    @Override
    public void start() {
        Log.d(TAG, TAG + ".start() called");
        if( SharedPreferenceHelper.getInstance(CoreApplication.getInstance())
                .getFarmerObject("")!= null) {
            UserManager.getInstance().setFarmerUser(SharedPreferenceHelper.getInstance(CoreApplication.getInstance())
                    .getFarmerObject(""));
            view.startDiaryActivity(UserManager.getInstance().getFarmerUser().getId());
            return;
        }

        if( SharedPreferenceHelper.getInstance(CoreApplication.getInstance())
                .getEmployeeObject("")!= null) {
            UserManager.getInstance().setEmployeeUser(SharedPreferenceHelper.getInstance(CoreApplication.getInstance())
                    .getEmployeeObject(""));
            view.startFarmerActivity();
            return;
        }

        if(SharedPreferenceHelper.getInstance(CoreApplication.getInstance())
                .wasStartedBoolean(false)) {
            view.startLoginPermission();
        }
    }

    @Override
    public void stop() {
        Log.d(TAG, TAG + ".stop() called");
    }
}
