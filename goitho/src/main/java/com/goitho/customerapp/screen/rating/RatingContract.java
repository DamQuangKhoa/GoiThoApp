package com.goitho.customerapp.screen.rating;

import com.demo.architect.data.model.NotificationEntity;
import com.goitho.customerapp.app.base.BasePresenter;
import com.goitho.customerapp.app.base.BaseView;

import java.util.List;

/**
 * Created by MSI on 26/11/2017.
 */

public interface RatingContract {
    interface View extends BaseView<Presenter> {
        void startDialogRating();
        void showProfileTho();
    }

    interface Presenter extends BasePresenter {

    }
}
