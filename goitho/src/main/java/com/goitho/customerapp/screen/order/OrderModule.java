package com.goitho.customerapp.screen.order;

import android.support.annotation.NonNull;

import com.goitho.customerapp.screen.notification.NotificationContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Skull on 27/11/2017.
 */

@Module
public class OrderModule {
    private final OrderContract.View OrderContractView;

    public OrderModule(OrderContract.View OrderContractView) {
        this.OrderContractView = OrderContractView;
    }

    @Provides
    @NonNull
    OrderContract.View provideOrderContractView() {
        return this.OrderContractView;
    }
}

