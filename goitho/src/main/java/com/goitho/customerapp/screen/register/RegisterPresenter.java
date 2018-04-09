package com.goitho.customerapp.screen.register;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.demo.architect.data.repository.base.local.LocalRepository;
import com.demo.architect.domain.usecase.BaseUseCase;
import com.demo.architect.domain.usecase.RegisterUsecase;
import com.demo.architect.domain.usecase.SendActiveUsecase;
import com.goitho.customerapp.app.CoreApplication;
import com.google.gson.Gson;

import javax.inject.Inject;

/**
 * Created by MSI on 26/11/2017.
 */

public class RegisterPresenter implements RegisterContract.Presenter {

    private final String TAG = RegisterPresenter.class.getName();
    private final RegisterContract.View view;
    private final RegisterUsecase registerUsecase;
    private final SendActiveUsecase sendActiveUsecase;

    @Inject
    LocalRepository localRepository;

    @Inject
    RegisterPresenter(@NonNull RegisterContract.View view, RegisterUsecase registerUsecase,
                      SendActiveUsecase sendActiveUsecase) {
        this.view = view;
        this.registerUsecase = registerUsecase;

        this.sendActiveUsecase = sendActiveUsecase;
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
    public void register(String username, String password, String phone) {
        view.showProgressBar();
        registerUsecase.executeIO(new RegisterUsecase.RequestValue(username, password, phone), new BaseUseCase.UseCaseCallback
                <RegisterUsecase.ResponseValue, RegisterUsecase.ErrorValue>() {
            @Override
            public void onSuccess(final RegisterUsecase.ResponseValue registerSuccessResponse) {
                Log.d(TAG, new Gson().toJson(registerSuccessResponse.getEntity()));

                if (registerSuccessResponse.getEntity().getAuth_code().equals("")) {
                    sendActiveUsecase.executeIO(new SendActiveUsecase.RequestValue(registerSuccessResponse.getEntity().getUserId()),
                            new BaseUseCase.UseCaseCallback<SendActiveUsecase.ResponseValue,
                                    SendActiveUsecase.ErrorValue>() {
                                @Override
                                public void onSuccess(SendActiveUsecase.ResponseValue successResponse) {
                                    Toast.makeText(CoreApplication.getInstance(), "Code: " + successResponse.getEntity().getKey(), Toast.LENGTH_SHORT).show();
                                    view.startPhoneVerificationActivity(registerSuccessResponse.getEntity().getUserId());
                                    view.hideProgressBar();
                                }

                                @Override
                                public void onError(SendActiveUsecase.ErrorValue errorResponse) {
                                    Toast.makeText(CoreApplication.getInstance(), "SendActive failed", Toast.LENGTH_SHORT).show();
                                    view.hideProgressBar();
                                }
                            });
                }else {
                    Toast.makeText(CoreApplication.getInstance(), "Code: " + registerSuccessResponse.getEntity().getAuth_code(), Toast.LENGTH_SHORT).show();
                    view.startPhoneVerificationActivity(registerSuccessResponse.getEntity().getUserId());
                    view.hideProgressBar();
                }
            }

            @Override
            public void onError(RegisterUsecase.ErrorValue errorResponse) {
                if(errorResponse.getErrorCode() == 201){
                    view.showErrorAccountExists();
                }else {
                    Toast.makeText(CoreApplication.getInstance(), "Register failed", Toast.LENGTH_SHORT).show();
                }
                view.hideProgressBar();
            }
        });
    }
}
