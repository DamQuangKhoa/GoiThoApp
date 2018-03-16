package com.goitho.customerapp.screen.delete_action;

import android.support.annotation.NonNull;
import android.util.Log;

import com.demo.architect.data.helper.SharedPreferenceHelper;
import com.demo.architect.data.repository.base.local.LocalRepository;
import com.demo.architect.domain.usecase.BaseUseCase;
import com.demo.architect.domain.usecase.DeleteActionProductUsecase;
import com.demo.architect.domain.usecase.LoginEmployeeUsecase;
import com.demo.architect.domain.usecase.LoginFarmerUsecase;
import com.goitho.customerapp.app.CoreApplication;
import com.goitho.customerapp.constants.Constants;
import com.goitho.customerapp.manager.UserManager;

import javax.inject.Inject;

/**
 * Created by Skull on 11/12/2017.
 */

public class DeleteActionPresenter implements DeleteActionContract.Presenter{

    private final String TAG = DeleteActionPresenter.class.getName();
    private final DeleteActionContract.View view;
    private DeleteActionProductUsecase deleteActionProductUsecase;
    private LoginFarmerUsecase loginFarmerUsecase;
    private LoginEmployeeUsecase loginEmployeeUsecase;

    @Inject
    LocalRepository localRepository;

    @Inject
    DeleteActionPresenter(@NonNull DeleteActionContract.View view,
                          DeleteActionProductUsecase deleteActionProductUsecase,
                          LoginFarmerUsecase loginFarmerUsecase,
                          LoginEmployeeUsecase loginEmployeeUsecase) {
        this.view = view;
        this.deleteActionProductUsecase = deleteActionProductUsecase;
        this.loginFarmerUsecase = loginFarmerUsecase;
        this.loginEmployeeUsecase = loginEmployeeUsecase;
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

    @Override
    public void deleteAction(String action) {
        view.showProgressBar();
        String[] id = action.split(" - ");
        deleteActionProductUsecase.executeIO(new DeleteActionProductUsecase.RequestValue(
                        SharedPreferenceHelper.getInstance(CoreApplication.getInstance())
                                .getString(Constants.KEY_TOKEN,""),
                        id[1] ),
                new BaseUseCase.UseCaseCallback<DeleteActionProductUsecase.ResponseValue, DeleteActionProductUsecase.ErrorValue>() {
                    @Override
                    public void onSuccess(DeleteActionProductUsecase.ResponseValue successResponse) {
                        view.finishActivity();
                        view.hideProgressBar();
                    }

                    @Override
                    public void onError(DeleteActionProductUsecase.ErrorValue errorResponse) {
                        view.hideProgressBar();
                        view.showError();
                    }
                });
    }

    @Override
    public void checkPassword(String password, String action) {
        view.showProgressBar();
        if(UserManager.getInstance().getEmployeeUser() != null){
            loginEmployeeUsecase.executeIO(new LoginEmployeeUsecase.RequestValue(
                            UserManager.getInstance().getEmployeeUser().getUsername(), password),
                    new BaseUseCase.UseCaseCallback<LoginEmployeeUsecase.ResponseValue, LoginEmployeeUsecase.ErrorValue>() {
                        @Override
                        public void onSuccess(LoginEmployeeUsecase.ResponseValue successResponse) {
                            deleteAction(action);
                        }

                        @Override
                        public void onError(LoginEmployeeUsecase.ErrorValue errorResponse) {
                            view.hideProgressBar();
                            view.showError();
                        }
                    });
        } else {
            loginFarmerUsecase.executeIO(new LoginFarmerUsecase.RequestValue(
                            UserManager.getInstance().getFarmerUser().getUsername(), password),
                    new BaseUseCase.UseCaseCallback<LoginFarmerUsecase.ResponseValue, LoginFarmerUsecase.ErrorValue>() {
                        @Override
                        public void onSuccess(LoginFarmerUsecase.ResponseValue successResponse) {
                            deleteAction(action);
                        }

                        @Override
                        public void onError(LoginFarmerUsecase.ErrorValue errorResponse) {
                            view.hideProgressBar();
                            view.showError();
                        }
                    });
        }
    }
}
