package com.goitho.customerapp.screen.phone_verification;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.demo.architect.data.repository.base.local.LocalRepository;
import com.demo.architect.domain.usecase.ActiveResetPasswordUsecase;
import com.demo.architect.domain.usecase.ActiveResetPhoneNumberUsecase;
import com.demo.architect.domain.usecase.ActiveUserUsecase;
import com.demo.architect.domain.usecase.BaseUseCase;
import com.demo.architect.domain.usecase.SendActiveUsecase;
import com.goitho.customerapp.app.CoreApplication;
import com.google.gson.Gson;

import javax.inject.Inject;

/**
 * Created by MSI on 26/11/2017.
 */

public class PhoneVerificationPresenter implements PhoneVerificationContract.Presenter{

    private final String TAG = PhoneVerificationPresenter.class.getName();
    private final PhoneVerificationContract.View view;
    private final SendActiveUsecase sendActiveUsecase;
    private final ActiveUserUsecase activeUserUsecase;
    private final ActiveResetPhoneNumberUsecase activeResetPhoneNumberUsecase;
    private final ActiveResetPasswordUsecase activeResetPasswordUsecase;

    @Inject
    LocalRepository localRepository;

    @Inject
    PhoneVerificationPresenter(@NonNull PhoneVerificationContract.View view,
                               SendActiveUsecase sendActiveUsecase,
                               ActiveUserUsecase activeUserUsecase,
                               ActiveResetPhoneNumberUsecase activeResetPhoneNumberUsecase,
                               ActiveResetPasswordUsecase activeResetPasswordUsecase) {
        this.view = view;
        this.sendActiveUsecase = sendActiveUsecase;
        this.activeUserUsecase = activeUserUsecase;
        this.activeResetPhoneNumberUsecase = activeResetPhoneNumberUsecase;
        this.activeResetPasswordUsecase = activeResetPasswordUsecase;
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
    public void sendActive(int userId) {
        sendActiveUsecase.executeIO(new SendActiveUsecase.RequestValue(userId),
                new BaseUseCase.UseCaseCallback<SendActiveUsecase.ResponseValue,
                        SendActiveUsecase.ErrorValue>() {
                    @Override
                    public void onSuccess(SendActiveUsecase.ResponseValue successResponse) {
                        Log.d(TAG, new Gson().toJson(successResponse.getEntity()));
                        Toast.makeText(CoreApplication.getInstance(),
                                successResponse.getEntity().getKey()+"", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(SendActiveUsecase.ErrorValue errorResponse) {
                        Toast.makeText(CoreApplication.getInstance(), "SendActive failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void activeRegisterUser(int userId, String authCode) {
        activeUserUsecase.executeIO(new ActiveUserUsecase.RequestValue(userId, authCode),
                new BaseUseCase.UseCaseCallback<ActiveUserUsecase.ResponseValue,
                        ActiveUserUsecase.ErrorValue>() {
                    @Override
                    public void onSuccess(ActiveUserUsecase.ResponseValue successResponse) {
                        Log.d(TAG, successResponse.getDescription());
                        Toast.makeText(CoreApplication.getInstance(),
                                successResponse.getDescription(), Toast.LENGTH_SHORT).show();
                        view.startEditAddressActivity();
                    }

                    @Override
                    public void onError(ActiveUserUsecase.ErrorValue errorResponse) {
                        view.showError();
                    }
                });
    }

    @Override
    public void activeResetPassword(int userId, String authCode, String newPassword) {
        activeResetPasswordUsecase.executeIO(new ActiveResetPasswordUsecase.RequestValue(userId, authCode, newPassword),
                new BaseUseCase.UseCaseCallback<ActiveResetPasswordUsecase.ResponseValue,
                        ActiveResetPasswordUsecase.ErrorValue>() {
                    @Override
                    public void onSuccess(ActiveResetPasswordUsecase.ResponseValue successResponse) {
                        view.showSuccessAndFinishActivity();
                    }

                    @Override
                    public void onError(ActiveResetPasswordUsecase.ErrorValue errorResponse) {
                        view.showError();
                    }
                });
    }

    @Override
    public void activeResetPhone(int userId, String authCode, String newPhone) {

    }
}
