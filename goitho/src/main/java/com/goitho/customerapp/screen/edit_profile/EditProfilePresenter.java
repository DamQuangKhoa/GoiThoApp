package com.goitho.customerapp.screen.edit_profile;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.demo.architect.data.helper.SharedPreferenceHelper;
import com.demo.architect.data.model.EmployeeEntity;
import com.demo.architect.data.model.FarmerEntity;
import com.demo.architect.data.repository.base.local.LocalRepository;
import com.demo.architect.domain.usecase.BaseUseCase;
import com.demo.architect.domain.usecase.PostImageUsecase;
import com.demo.architect.domain.usecase.PutFarmerUsecase;
import com.goitho.customerapp.app.CoreApplication;
import com.goitho.customerapp.constants.Constants;
import com.goitho.customerapp.manager.UserManager;

import java.io.File;

import javax.inject.Inject;

/**
 * Created by Skull on 29/11/2017.
 */

public class EditProfilePresenter implements EditProfileContract.Presenter {

    private final String TAG = EditProfilePresenter.class.getName();
    private final EditProfileContract.View view;

    @Inject
    LocalRepository localRepository;

    @Inject
    EditProfilePresenter(@NonNull EditProfileContract.View view) {
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
