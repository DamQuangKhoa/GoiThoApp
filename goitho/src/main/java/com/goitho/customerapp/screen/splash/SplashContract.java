package com.goitho.customerapp.screen.splash;

import com.goitho.customerapp.app.base.BasePresenter;
import com.goitho.customerapp.app.base.BaseView;

/**
 * Created by MSI on 26/11/2017.
 */

public interface SplashContract {
    interface View extends BaseView<Presenter> {
        void startDiaryActivity(String id);
        void  startFarmerActivity();

        void startLoginPermission();
    }

    interface Presenter extends BasePresenter {
    }
}
