package com.goitho.customerapp.screen.order_repair;

import com.goitho.customerapp.app.di.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Skull on 27/11/2017.
 */

@ActivityScope
@Subcomponent(modules = {OrderRepairModule.class})
public interface OrderRepairComponent {
    void inject(OrderRepairFragment fragment);

}
