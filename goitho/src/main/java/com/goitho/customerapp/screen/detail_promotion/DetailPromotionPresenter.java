package com.goitho.customerapp.screen.detail_promotion;

import android.support.annotation.NonNull;
import android.util.Log;

import com.demo.architect.data.repository.base.local.LocalRepository;
import com.goitho.customerapp.manager.ListPromotionsManager;

import javax.inject.Inject;

/**
 * Created by Skull on 11/12/2017.
 */

public class DetailPromotionPresenter implements DetailPromotionContract.Presenter{

    private final String TAG = DetailPromotionPresenter.class.getName();
    private final DetailPromotionContract.View view;

    @Inject
    LocalRepository localRepository;

    @Inject
    DetailPromotionPresenter(@NonNull DetailPromotionContract.View view) {
        this.view = view;
    }

    @Inject
    public void setupPresenter() {
        view.setPresenter(this);
    }


    @Override
    public void start() {
        Log.d(TAG, TAG + ".start() called");
    }

    @Override
    public void stop() {
        Log.d(TAG, TAG + ".stop() called");
    }


    @Override
    public void getDetailPromotion(String promotionId) {
        view.showPromotion(ListPromotionsManager.getInstance().getPromotionEntityById(promotionId));
    }
}
