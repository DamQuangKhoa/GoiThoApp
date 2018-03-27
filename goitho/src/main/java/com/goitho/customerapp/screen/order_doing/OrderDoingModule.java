package com.goitho.customerapp.screen.order_doing;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Skull on 27/11/2017.
 */


@Module
public class OrderDoingModule {
    private final OrderDoingContract.View OrderDoingContractView;

    public OrderDoingModule(OrderDoingContract.View OrderDoingContractView) {
        this.OrderDoingContractView = OrderDoingContractView;
    }

    @Provides
    @NonNull
    OrderDoingContract.View provideOrderDoingContractView() {
        return this.OrderDoingContractView;
    }
}

