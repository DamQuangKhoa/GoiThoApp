package com.goitho.customerapp.screen.list_promotion.add_action;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Skull on 11/12/2017.
 */

@Module
public class ListPromotionModule {
    private final ListPromotionContract.View ListPromotionContractView;

    public ListPromotionModule(ListPromotionContract.View ListPromotionContractView) {
        this.ListPromotionContractView = ListPromotionContractView;
    }

    @Provides
    @NonNull
    ListPromotionContract.View provideListPromotionContractView() {
        return this.ListPromotionContractView;
    }
}
