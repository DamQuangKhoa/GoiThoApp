package com.goitho.customerapp.screen.login;

import com.goitho.customerapp.app.base.BasePresenter;
import com.goitho.customerapp.app.base.BaseView;

/**
 * Created by MSI on 26/11/2017.
 */

public interface LoginContract {
    interface View extends BaseView<Presenter> {
        void startRegisterActivity();
        void startDashboardActivity();
        void startDialogForgetPass();
        void startPhoneVerificationActivity(int userId, String newPassword);
        void showError();
        void showErrorResetPassword();

    }

    interface Presenter extends BasePresenter {
        void login(String phone, String password);
        void resetPassword(String username, String phone, String newPassword);
    }
}
