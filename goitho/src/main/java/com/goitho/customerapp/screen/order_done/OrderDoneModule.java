package com.goitho.customerapp.screen.order_done;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Skull on 27/11/2017.
 */


@Module
public class OrderDoneModule {
    private final OrderDoneContract.View OrderDoneContractView;

    public OrderDoneModule(OrderDoneContract.View OrderDoneContractView) {
        this.OrderDoneContractView = OrderDoneContractView;
    }

    @Provides
    @NonNull
    OrderDoneContract.View provideOrderDoneContractView() {
        return this.OrderDoneContractView;
    }
}

