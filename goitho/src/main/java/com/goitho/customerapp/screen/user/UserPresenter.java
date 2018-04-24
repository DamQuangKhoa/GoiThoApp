package com.goitho.customerapp.screen.user;

import android.support.annotation.NonNull;
import android.util.Log;

import com.demo.architect.data.helper.SharedPreferenceHelper;
import com.demo.architect.data.model.UserEntity;
import com.demo.architect.data.repository.base.local.LocalRepository;
import com.demo.architect.domain.usecase.BaseUseCase;
import com.demo.architect.domain.usecase.GetInfoPointUsecase;
import com.goitho.customerapp.app.CoreApplication;
import com.goitho.customerapp.constants.Constants;
import com.goitho.customerapp.manager.UserManager;
import com.google.gson.Gson;

import javax.inject.Inject;

/**
 * Created by MSI on 26/11/2017.
 */

public class UserPresenter implements UserContract.Presenter {

    private final String TAG = UserPresenter.class.getName();
    private final UserContract.View view;
    private final GetInfoPointUsecase getInfoPointUsecase;

    @Inject
    LocalRepository localRepository;

    @Inject
    UserPresenter(@NonNull UserContract.View view, GetInfoPointUsecase getInfoPointUsecase) {
        this.view = view;
        this.getInfoPointUsecase = getInfoPointUsecase;
    }

    @Inject
    public void setupPresenter() {
        view.setPresenter(this);
    }


    @Override
    public void start() {
        Log.d(TAG, TAG + ".start() called");
        view.showInfoUser(getInfo());
        getInfoPoint();
    }

    @Override
    public void stop() {
        Log.d(TAG, TAG + ".stop() called");
    }


    @Override
    public void logout() {
        SharedPreferenceHelper.getInstance(CoreApplication.getInstance()).pushBoolean(Constants.KEY_CHECK_LOGIN, false);
        view.startDashboardActivity();
    }

    @Override
    public UserEntity getInfo() {
        return UserManager.getInstance().getUser();
    }

    @Override
    public void getInfoPoint() {
        view.showProgressBar();
        getInfoPointUsecase.executeIO(new GetInfoPointUsecase.RequestValue(UserManager.getInstance().getUser().getUserId()),
                new BaseUseCase.UseCaseCallback<GetInfoPointUsecase.ResponseValue,
                        GetInfoPointUsecase.ErrorValue>() {
                    @Override
                    public void onSuccess(GetInfoPointUsecase.ResponseValue successResponse) {
                        Log.d(TAG, new Gson().toJson(successResponse.getEntity()));
                        //Go to dashboard activity
                        view.showInfoPoint(successResponse.getEntity());
                        view.hideProgressBar();
                    }

                    @Override
                    public void onError(GetInfoPointUsecase.ErrorValue errorResponse) {

                    }
                });
    }
}
