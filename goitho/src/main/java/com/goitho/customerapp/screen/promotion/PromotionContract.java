package com.goitho.customerapp.screen.promotion;


import com.goitho.customerapp.app.base.BasePresenter;
import com.goitho.customerapp.app.base.BaseView;

/**
 * Created by MSI on 26/11/2017.
 */

public interface PromotionContract {
    interface View extends BaseView<Presenter> {
        void showPromotionList();
        void showError();
    }

    interface Presenter extends BasePresenter {
        void getListPromotion(int loaded, int perload);
    }
}
