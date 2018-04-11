package com.goitho.customerapp.screen.dashboard;

import com.goitho.customerapp.app.base.BasePresenter;
import com.goitho.customerapp.app.base.BaseView;

/**
 * Created by Skull on 14/12/2017.
 */

public interface DashboardContract {
    interface View extends BaseView<Presenter> {
        void setupView(boolean login);
    }

    interface Presenter extends BasePresenter {
        void checkLogin();
    }
}
