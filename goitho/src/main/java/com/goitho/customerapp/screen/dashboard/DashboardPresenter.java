package com.goitho.customerapp.screen.dashboard;

import android.support.annotation.NonNull;
import android.util.Log;

import com.demo.architect.data.helper.SharedPreferenceHelper;
import com.demo.architect.data.model.ActivityEntity;
import com.demo.architect.data.model.EmployeeEntity;
import com.demo.architect.data.model.offline.DetailDiaryEntity;
import com.demo.architect.data.repository.base.local.LocalRepository;
import com.demo.architect.domain.usecase.BaseUseCase;
import com.demo.architect.domain.usecase.GetHistoryActivityUsecase;
import com.demo.architect.domain.usecase.GetListEmployeeUseCase;
import com.demo.architect.domain.usecase.GetListFarmerUsecase;
import com.goitho.customerapp.app.CoreApplication;
import com.goitho.customerapp.constants.Constants;
import com.goitho.customerapp.manager.EmployeeManager;
import com.goitho.customerapp.manager.FarmerManager;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Skull on 14/12/2017.
 */

public class DashboardPresenter implements DashboardContract.Presenter{

    private final String TAG = DashboardPresenter.class.getName();
    private final DashboardContract.View view;


    @Inject
    DashboardPresenter(@NonNull DashboardContract.View view) {
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
