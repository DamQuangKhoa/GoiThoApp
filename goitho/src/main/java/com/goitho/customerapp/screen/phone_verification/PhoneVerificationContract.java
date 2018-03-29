package com.goitho.customerapp.screen.phone_verification;

import com.goitho.customerapp.app.base.BasePresenter;
import com.goitho.customerapp.app.base.BaseView;

/**
 * Created by MSI on 26/11/2017.
 */

public interface PhoneVerificationContract {
    interface View extends BaseView<Presenter> {
        void startEditAddressActivity();

        void showError();
    }

    interface Presenter extends BasePresenter {
    }
}
