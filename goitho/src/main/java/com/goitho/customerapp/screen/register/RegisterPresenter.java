package com.goitho.customerapp.screen.register;

import android.support.annotation.NonNull;
import android.util.Log;

import com.demo.architect.data.helper.SharedPreferenceHelper;
import com.demo.architect.data.repository.base.local.LocalRepository;
import com.demo.architect.domain.usecase.BaseUseCase;
import com.demo.architect.domain.usecase.GetListEmployeeUseCase;
import com.demo.architect.domain.usecase.GetListFarmerUsecase;
import com.demo.architect.domain.usecase.GetProductUsecase;
import com.demo.architect.domain.usecase.LoginEmployeeUsecase;
import com.demo.architect.domain.usecase.LoginFarmerUsecase;
import com.goitho.customerapp.app.CoreApplication;
import com.goitho.customerapp.constants.Constants;
import com.goitho.customerapp.manager.FarmerManager;
import com.goitho.customerapp.manager.ProductManager;
import com.goitho.customerapp.manager.UserManager;

import javax.inject.Inject;

/**
 * Created by MSI on 26/11/2017.
 */

public class RegisterPresenter implements RegisterContract.Presenter{

    private final String TAG = RegisterPresenter.class.getName();
    private final RegisterContract.View view;

    @Inject
    LocalRepository localRepository;

    @Inject
    RegisterPresenter(@NonNull RegisterContract.View view) {
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
