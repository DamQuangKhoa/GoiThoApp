package com.goitho.customerapp.screen.list_faq;

import android.support.annotation.NonNull;

import com.goitho.customerapp.screen.list_faq.ListFaqContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Skull on 11/12/2017.
 */

@Module
public class ListFaqModule {
    private final ListFaqContract.View ListPromotionContractView;

    public ListFaqModule(ListFaqContract.View ListPromotionContractView) {
        this.ListPromotionContractView = ListPromotionContractView;
    }

    @Provides
    @NonNull
    ListFaqContract.View provideListPromotionContractView() {
        return this.ListPromotionContractView;
    }
}
