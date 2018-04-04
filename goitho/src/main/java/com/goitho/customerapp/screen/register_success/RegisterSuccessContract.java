package com.goitho.customerapp.screen.register_success;


import com.goitho.customerapp.app.base.BasePresenter;
import com.goitho.customerapp.app.base.BaseView;

/**
 * Created by MSI on 26/11/2017.
 */

public interface RegisterSuccessContract {
    interface View extends BaseView<Presenter> {
        void startDashboardActivity();
    }

    interface Presenter extends BasePresenter {
    }
}
