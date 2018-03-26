package com.goitho.customerapp.screen.dashboard;

import com.demo.architect.data.model.ActivityEntity;
import com.goitho.customerapp.app.base.BasePresenter;
import com.goitho.customerapp.app.base.BaseView;

import java.util.List;

/**
 * Created by Skull on 14/12/2017.
 */

public interface DashboardContract {
    interface View extends BaseView<Presenter> {
    }

    interface Presenter extends BasePresenter {
    }
}
