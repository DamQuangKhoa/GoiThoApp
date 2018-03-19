package com.goitho.customerapp.screen.login;

import com.goitho.customerapp.app.base.BasePresenter;
import com.goitho.customerapp.app.base.BaseView;

/**
 * Created by MSI on 26/11/2017.
 */

public interface LoginContract {
    interface View extends BaseView<Presenter> {
        void startRegisterActivity();

        void showError();
    }

    interface Presenter extends BasePresenter {

    }
}
