package com.goitho.customerapp.screen.phone_verification;

import com.goitho.customerapp.app.base.BasePresenter;
import com.goitho.customerapp.app.base.BaseView;

/**
 * Created by MSI on 26/11/2017.
 */

public interface PhoneVerificationContract {
    interface View extends BaseView<Presenter> {
        void startEditAddressActivity();

        void showSuccessAndFinishActivity();

        void showDialog(String content);

        void showError();
    }

    interface Presenter extends BasePresenter {
        void sendActive(String userId);

        void activeRegisterUser(String userId, String authCode);

        void activeResetPassword(String userId, String authCode, String newPassword);

        void activeResetPhone(String userId, String authCode, String newPhone);
    }
}
