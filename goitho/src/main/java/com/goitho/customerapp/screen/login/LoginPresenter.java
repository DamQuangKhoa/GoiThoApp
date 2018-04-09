package com.goitho.customerapp.screen.login;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.demo.architect.data.helper.SharedPreferenceHelper;
import com.demo.architect.data.repository.base.local.LocalRepository;
import com.demo.architect.domain.usecase.BaseUseCase;
import com.demo.architect.domain.usecase.LoginUsecase;
import com.demo.architect.domain.usecase.ResetPasswordUsecase;
import com.goitho.customerapp.app.CoreApplication;
import com.google.gson.Gson;

import javax.inject.Inject;

/**
 * Created by MSI on 26/11/2017.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private final String TAG = LoginPresenter.class.getName();
    private final LoginContract.View view;
    private final ResetPasswordUsecase resetPasswordUsecase;
    private final LoginUsecase loginUsecase;

    @Inject
    LocalRepository localRepository;

    @Inject
    LoginPresenter(@NonNull LoginContract.View view, ResetPasswordUsecase resetPasswordUsecase,
                   LoginUsecase loginUsecase) {
        this.view = view;
        this.resetPasswordUsecase = resetPasswordUsecase;

        this.loginUsecase = loginUsecase;
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
    public void login(String phone, String password) {
        view.showProgressBar();
        loginUsecase.executeIO(new LoginUsecase.RequestValue(phone, password), new BaseUseCase.UseCaseCallback
                <LoginUsecase.ResponseValue, LoginUsecase.ErrorValue>() {
            @Override
            public void onSuccess(LoginUsecase.ResponseValue successResponse) {
                Log.d(TAG, new Gson().toJson(successResponse.getEntity()));

                //Save user entity to shared preferences
                SharedPreferenceHelper.getInstance(CoreApplication.getInstance()).pushUserObject(successResponse.getEntity());

                //Go to dashboard activity
                view.startDashboardActivity();
                view.hideProgressBar();
            }

            @Override
            public void onError(LoginUsecase.ErrorValue errorResponse) {
                view.showError();
                view.hideProgressBar();
            }
        });
    }

    @Override
    public void resetPassword(String username, String phone, String newPassword) {
        view.showProgressBar();
        resetPasswordUsecase.executeIO(new ResetPasswordUsecase.RequestValue(username, phone), new BaseUseCase.UseCaseCallback
                <ResetPasswordUsecase.ResponseValue, ResetPasswordUsecase.ErrorValue>() {
            @Override
            public void onSuccess(ResetPasswordUsecase.ResponseValue successResponse) {
                Log.d(TAG, new Gson().toJson(successResponse.getEntity()));
                Toast.makeText(CoreApplication.getInstance(), successResponse.getEntity().getData().getAuth_code() + "", Toast.LENGTH_SHORT).show();
                view.startPhoneVerificationActivity(successResponse.getEntity().getData().getUserId(), newPassword);
                view.hideProgressBar();
            }

            @Override
            public void onError(ResetPasswordUsecase.ErrorValue errorResponse) {
                view.showErrorResetPassword();
                view.hideProgressBar();
            }
        });
    }
}
