package com.goitho.customerapp.screen.order_cancel;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Skull on 27/11/2017.
 */


@Module
public class OrderCancelModule {
    private final OrderCancelContract.View OrderCancelContractView;

    public OrderCancelModule(OrderCancelContract.View OrderCancelContractView) {
        this.OrderCancelContractView = OrderCancelContractView;
    }

    @Provides
    @NonNull
    OrderCancelContract.View provideOrderCancelContractView() {
        return this.OrderCancelContractView;
    }
}

