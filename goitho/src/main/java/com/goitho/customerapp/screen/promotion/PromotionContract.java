package com.goitho.customerapp.screen.promotion;


import com.demo.architect.data.model.PromotionEntity;
import com.goitho.customerapp.app.base.BasePresenter;
import com.goitho.customerapp.app.base.BaseView;

import java.util.List;

/**
 * Created by MSI on 26/11/2017.
 */

public interface PromotionContract {
    interface View extends BaseView<Presenter> {
        void showPromotionList(List<PromotionEntity> list);
    }

    interface Presenter extends BasePresenter {
        List<PromotionEntity> promotionList();
    }
}
