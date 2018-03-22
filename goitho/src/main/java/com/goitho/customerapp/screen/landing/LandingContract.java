package com.goitho.customerapp.screen.landing;

import com.goitho.customerapp.app.base.BasePresenter;
import com.goitho.customerapp.app.base.BaseView;

/**
 * Created by MSI on 26/11/2017.
 */

public interface LandingContract {
    interface View extends BaseView<Presenter> {
        void startRegisterActivity();
        void startLoginActivity();

    }

    interface Presenter extends BasePresenter {
    }
}
