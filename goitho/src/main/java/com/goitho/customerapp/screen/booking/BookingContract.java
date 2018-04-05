package com.goitho.customerapp.screen.booking;

import com.goitho.customerapp.app.base.BasePresenter;
import com.goitho.customerapp.app.base.BaseView;

/**
 * Created by Skull on 27/11/2017.
 */

public interface BookingContract {
    interface View extends BaseView<Presenter> {
        void startBookingSuccess();
        void startListPromotionActivity();
    }

    interface Presenter extends BasePresenter {
    }
}

