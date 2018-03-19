package com.goitho.customerapp.screen.register;

import com.goitho.customerapp.app.base.BasePresenter;
import com.goitho.customerapp.app.base.BaseView;

/**
 * Created by MSI on 26/11/2017.
 */

public interface RegisterContract {
    interface View extends BaseView<Presenter> {
        void startLoginActivity();
        void startVerificationActivity();
        void showError();
    }

    interface Presenter extends BasePresenter {
    }
}
