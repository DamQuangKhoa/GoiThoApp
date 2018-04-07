package com.goitho.customerapp.screen.detail_promotion;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Skull on 11/12/2017.
 */

@Module
public class DetailPromotionModule {
    private final DetailPromotionContract.View AddActionContractView;

    public DetailPromotionModule(DetailPromotionContract.View AddActionContractView) {
        this.AddActionContractView = AddActionContractView;
    }

    @Provides
    @NonNull
    DetailPromotionContract.View provideAddActionContractView() {
        return this.AddActionContractView;
    }
}
