package com.goitho.customerapp.screen.detail_promotion;

import com.demo.architect.data.model.PromotionEntity;
import com.goitho.customerapp.app.base.BasePresenter;
import com.goitho.customerapp.app.base.BaseView;

/**
 * Created by Skull on 11/12/2017.
 */

public interface DetailPromotionContract {
    interface View extends BaseView<Presenter> {
        void showError();

        void showPromotion(PromotionEntity entity);
    }

    interface Presenter extends BasePresenter {
        void getDetailPromotion(String promotionId);
    }
}