package com.goitho.customerapp.screen.promotion;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MSI on 26/11/2017.
 */

@Module
public class PromotionModule {
    private final PromotionContract.View PostContractView;

    public PromotionModule(PromotionContract.View PostContractView) {
        this.PostContractView = PostContractView;
    }

    @Provides
    @NonNull
    PromotionContract.View providePostContractView() {
        return this.PostContractView;
    }
}

