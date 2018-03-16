package com.goitho.customerapp.screen.history;

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

public class HistoryPresenter implements HistoryContract.Presenter{

    private final String TAG = HistoryPresenter.class.getName();
    private final HistoryContract.View view;

    DetailDiaryEntity detailDiaryEntity;
    GetHistoryActivityUsecase getHistoryActivityUsecase;
    private GetListFarmerUsecase getListFarmerUseCase;
    private GetListEmployeeUseCase getListEmployeeUseCase;

    @Inject
    LocalRepository localRepository;

    @Inject
    HistoryPresenter(@NonNull HistoryContract.View view,
                     GetHistoryActivityUsecase getHistoryActivityUsecase,
                     GetListFarmerUsecase getListFarmerUseCase,
                     GetListEmployeeUseCase getListEmployeeUseCase) {
        this.view = view;
        this.getHistoryActivityUsecase = getHistoryActivityUsecase;
        this.getListFarmerUseCase = getListFarmerUseCase;
        this.getListEmployeeUseCase = getListEmployeeUseCase;
    }

    @Inject
    public void setupPresenter() {
        view.setPresenter(this);
    }

    @Override
    public void start() {
        Log.d(TAG, TAG + ".start() called");
        loadListFarmerAndEmployee();
    }

    private void loadListFarmerAndEmployee() {
        getListEmployeeUseCase.executeIO(new GetListEmployeeUseCase.RequestValue(
                SharedPreferenceHelper.getInstance(CoreApplication.getInstance()).getString(Constants.KEY_TOKEN, "")),
                                new BaseUseCase.UseCaseCallback<GetListEmployeeUseCase.ResponseValue, GetListEmployeeUseCase.ErrorValue>() {
                                    @Override
                                    public void onSuccess(GetListEmployeeUseCase.ResponseValue successResponse) {
                                        List<EmployeeEntity> employeeEntities =successResponse.getListEmployee();
                                        EmployeeManager.getInstance().setList(employeeEntities);
                                    }

                                    @Override
                                    public void onError(GetListEmployeeUseCase.ErrorValue errorResponse) {
                                        view.showError();
                                    }

                                });

        getListFarmerUseCase.executeIO(new GetListFarmerUsecase.RequestValue(
                        SharedPreferenceHelper.getInstance(CoreApplication.getInstance()).getString(Constants.KEY_TOKEN, "")
                ),
                new BaseUseCase.UseCaseCallback<GetListFarmerUsecase.ResponseValue, GetListFarmerUsecase.ErrorValue>() {
                    @Override
                    public void onSuccess(GetListFarmerUsecase.ResponseValue successResponse) {
                        FarmerManager.getInstance().setList(successResponse.getListFarmer());
                    }

                    @Override
                    public void onError(GetListFarmerUsecase.ErrorValue errorResponse) {

                    }
                });
    }

    @Override
    public void stop() {
        Log.d(TAG, TAG + ".stop() called");
    }



    @Override
    public void loadHistory(String id) {
        view.showProgressBar();
        getHistoryActivityUsecase.executeIO(new GetHistoryActivityUsecase.RequestValue(
                        SharedPreferenceHelper.getInstance(CoreApplication.getInstance())
                                .getString(Constants.KEY_TOKEN, ""),id),
                new BaseUseCase.UseCaseCallback<GetHistoryActivityUsecase.ResponseValue, GetHistoryActivityUsecase.ErrorValue>() {
                    @Override
                    public void onSuccess(GetHistoryActivityUsecase.ResponseValue successResponse) {
                        view.showHistory(successResponse.getActivityEntities().getActivities());
                        for (ActivityEntity entity : successResponse.getActivityEntities().getActivities()) {
                            loadHistory(entity.getId());
                        }
                        view.hideProgressBar();
                    }

                    @Override
                    public void onError(GetHistoryActivityUsecase.ErrorValue errorResponse) {
                        view.hideProgressBar();
                        view.showError();
                    }
                });

    }
}
