package com.goitho.customerapp.screen.order;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Skull on 27/11/2017.
 */


@Module
public class OrderModule {
    private final OrderContract.View DiaryContractView;

    public OrderModule(OrderContract.View DiaryContractView) {
        this.DiaryContractView = DiaryContractView;
    }

    @Provides
    @NonNull
    OrderContract.View provideDiaryContractView() {
        return this.DiaryContractView;
    }
}

