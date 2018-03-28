package com.goitho.customerapp.screen.order_repair;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Skull on 27/11/2017.
 */


@Module
public class OrderRepairModule {
    private final OrderRepairContract.View OrderRepairContractView;

    public OrderRepairModule(OrderRepairContract.View OrderRepairContractView) {
        this.OrderRepairContractView = OrderRepairContractView;
    }

    @Provides
    @NonNull
    OrderRepairContract.View provideOrderRepairContractView() {
        return this.OrderRepairContractView;
    }
}

