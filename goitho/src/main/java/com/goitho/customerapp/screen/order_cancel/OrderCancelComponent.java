package com.goitho.customerapp.screen.order_cancel;

import com.goitho.customerapp.app.di.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Skull on 27/11/2017.
 */

@ActivityScope
@Subcomponent(modules = {OrderCancelModule.class})
public interface OrderCancelComponent {
    void inject(OrderCancelFragment fragment);

}
