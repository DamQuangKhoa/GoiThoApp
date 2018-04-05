package com.goitho.customerapp.screen.list_promotion;

import com.goitho.customerapp.app.base.BasePresenter;
import com.goitho.customerapp.app.base.BaseView;

/**
 * Created by Skull on 11/12/2017.
 */

public interface ListPromotionContract {
    interface View extends BaseView<Presenter> {
        void showError();
        void finishActivity();
    }

    interface Presenter extends BasePresenter {
        void createActionProduct(String name);
    }
}