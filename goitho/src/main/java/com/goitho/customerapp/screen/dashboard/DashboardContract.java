package com.goitho.customerapp.screen.dashboard;

import com.demo.architect.data.model.ActivityEntity;
import com.goitho.customerapp.app.base.BasePresenter;
import com.goitho.customerapp.app.base.BaseView;
import com.goitho.customerapp.widgets.CustomViewPager;

import java.util.List;

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
